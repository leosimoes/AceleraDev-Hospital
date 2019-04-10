package gestao.services;

import gestao.models.hospital.Hospital;
import gestao.respositories.hospital.HospitalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoSolicitacaoService {
    
    @Autowired
    HospitalRepository hospitalRepository;
    
    public boolean solicitarProduto(Long id, String nome, Integer quantidade){
        Hospital hospital = hospitalRepository.findById(id).get();
        List<Hospital> hospitaisProximos = new ArrayList<>(); //=FUNCAO GEO 
        Optional<Hospital> hospitalMaisProximoDoador = hospitaisProximos.stream().filter(h -> h.hasEstoque(nome, quantidade)).findFirst();
        if(hospitalMaisProximoDoador.isPresent()){
                 hospitalMaisProximoDoador.get().decrementaProduto(nome, quantidade);
                 hospital.addProduto(nome, quantidade);
                 hospitalRepository.save(hospital);
                 hospitalRepository.save(hospitalMaisProximoDoador.get());
            return true;            
        }
        return false;
    }
    
}
