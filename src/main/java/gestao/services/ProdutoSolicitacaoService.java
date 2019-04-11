package gestao.services;

import gestao.models.hospital.Hospital;
import gestao.repositories.hospital.HospitalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoSolicitacaoService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    HospitalService hospitalService;

    public Hospital solicitarProduto(Long id, String nome, Integer quantidade){
        Hospital hospitalSolicitante = hospitalRepository.findById(id).get();
        List<Hospital> hospitaisCandidatos = hospitalService.procurarPorHospitaisProximos(hospitalSolicitante.getEndereco().getCoordenadas());

        Optional<Hospital> hospitalMaisProximoDoador = hospitaisCandidatos.stream().filter(h -> h.hasEstoque(nome, quantidade)).findFirst();
        if(hospitalMaisProximoDoador.isPresent()){
            hospitalMaisProximoDoador.get().decrementaProduto(nome, quantidade);
            hospitalSolicitante.addProduto(nome, quantidade);
            hospitalRepository.save(hospitalSolicitante);
            hospitalRepository.save(hospitalMaisProximoDoador.get());
            return hospitalMaisProximoDoador.get();
        }
        return null;
    }

}