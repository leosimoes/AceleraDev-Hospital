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

    private final HospitalRepository hospitalRepository;

    public BancoDeSangueSolicitacaoService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public boolean solicitarSangue(long id, BancoDeSangueENUM tipo, Integer quantidadeSolicitada) {

        Hospital hospitalSolicitante = hospitalRepository.findById(id).get();
        List<BancoDeSangueENUM> sanguesCompativeis = bancoDeSangueService.compatibilidadeSanguinea(tipo);

        Hospital hospitalMaisProximoDoador = null;// funcaoParaRetornarHospitalMaisProximo(hospitalSolicitante, sanguesCompativeis);

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
        return false;
    }
}
