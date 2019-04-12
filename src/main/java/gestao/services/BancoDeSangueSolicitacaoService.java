package gestao.services;


import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.hospital.Hospital;
import gestao.repositories.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela implementação dos serviços relacionados a solicitações de banco de sangue.
 *
 * @author Jardel Casteluber
 *
 */
@Service
public class BancoDeSangueSolicitacaoService {

    @Autowired
    BancoDeSangueService bancoDeSangueService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    HospitalRepository hospitalRepository;


    public Hospital solicitarSangue(long id, BancoDeSangueENUM tipo, Integer quantidadeSolicitada) {

        Hospital hospitalSolicitante = hospitalRepository.findById(id).get();
        List<BancoDeSangueENUM> sanguesCompativeis = bancoDeSangueService.compatibilidadeSanguinea(tipo);

        List<Hospital> hospitaisCandidatos = hospitalRepository.buscarMaisProximosPorGeo(hospitalSolicitante.getEndereco().getCoordenadas());

        for (Hospital hospitalMaisProximoDoador : hospitaisCandidatos) {
            if (hospitalMaisProximoDoador != hospitalSolicitante) {
                for (BancoDeSangueENUM tipoSanguineo : sanguesCompativeis) {
                    if ((hospitalMaisProximoDoador.getBancoDeSangue().get(tipoSanguineo) - quantidadeSolicitada) >= 4) {
                        Integer quantidadeRestante = hospitalMaisProximoDoador.getBancoDeSangue().get(tipoSanguineo) - quantidadeSolicitada;       //Decrementando
                        Map<BancoDeSangueENUM, Integer> sangueSet = hospitalMaisProximoDoador.getBancoDeSangue();                      //Sangue
                        sangueSet.put(tipoSanguineo, quantidadeRestante);                                                                        //do Hospital
                        hospitalMaisProximoDoador.setBancoDeSangue(sangueSet);                                                       //Doador
                        hospitalRepository.save(hospitalMaisProximoDoador);

                        sangueSet = hospitalSolicitante.getBancoDeSangue();                                                         //Incrementando
                        Integer quantidadeAtual = sangueSet.get(tipoSanguineo) + quantidadeSolicitada;                                         //Sangue
                        sangueSet.put(tipoSanguineo, quantidadeAtual);                                                                         //no Hospital
                        hospitalSolicitante.setBancoDeSangue(sangueSet);                                                            //Solicitante
                        hospitalRepository.save(hospitalSolicitante);
                        return hospitalMaisProximoDoador;
                    }
                }
            }
        }
        return null;
    }
}
