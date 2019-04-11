package gestao.services;


import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.hospital.Hospital;
import gestao.repositories.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BancoDeSangueSolicitacaoService {

    @Autowired
    BancoDeSangueService bancoDeSangueService;

    @Autowired
    HospitalService hospitalService;

    private final HospitalRepository hospitalRepository;

    public BancoDeSangueSolicitacaoService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public boolean solicitarSangue(long id, BancoDeSangueENUM tipo, Integer quantidadeSolicitada) {

        Hospital hospitalSolicitante = hospitalRepository.findById(id).get();
        List<BancoDeSangueENUM> sanguesCompativeis = bancoDeSangueService.compatibilidadeSanguinea(tipo);

        List<Hospital> hospitaisCandidatos = hospitalService.procurarPorHospitaisProximos(hospitalSolicitante.getEndereco().getCoordenadas());

        for (Hospital hospitalMaisProximoDoador : hospitaisCandidatos) {
            if (hospitalMaisProximoDoador != hospitalSolicitante) {
                for (BancoDeSangueENUM bs : sanguesCompativeis) {
                    if ((hospitalMaisProximoDoador.getBancoDeSangue().get(bs) - quantidadeSolicitada) >= 4) {
                        Integer quantidadeRestante = hospitalMaisProximoDoador.getBancoDeSangue().get(bs) - quantidadeSolicitada;       //Decrementando
                        Map<BancoDeSangueENUM, Integer> sangueSet = hospitalMaisProximoDoador.getBancoDeSangue();                      //Sangue
                        sangueSet.put(bs, quantidadeRestante);                                                                        //do Hospital
                        hospitalMaisProximoDoador.setBancoDeSangue(sangueSet);                                                       //Doador
                        hospitalRepository.save(hospitalMaisProximoDoador);

                        sangueSet = hospitalSolicitante.getBancoDeSangue();                                                         //Incrementando
                        Integer quantidadeAtual = sangueSet.get(bs) + quantidadeSolicitada;                                         //Sangue
                        sangueSet.put(bs, quantidadeAtual);                                                                         //no Hospital
                        hospitalSolicitante.setBancoDeSangue(sangueSet);                                                            //Solicitante
                        hospitalRepository.save(hospitalSolicitante);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
