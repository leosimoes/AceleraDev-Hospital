package gestao.services;

import gestao.Hospital.HospitalGeoRepository;
import gestao.models.hospital.Hospital;
import gestao.models.produto.Produto;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoSolicitacaoService {
     
    @Autowired
    HospitalService hospitalService;
    
    @Autowired
    HospitalGeoRepository hospitalGeoRepository;
    
    // produces = MediaType.APPLICATION_JSON_VALUE
    public boolean solicitarProduto(Long id, Produto produto, Integer quantidadeSolicitada){
      Hospital hospital = hospitalService.find(id).get();
      // Hospital hospitalMaisProximoDoador = null;// funcaoParaRetornarHospitalMaisProximo(hospitalSolicitante, sanguesCompativeis);
        //List<Hospital> hospitais = hospitalGeoRepository.buscarMaisProximosPorGeo(hospital.getPonto());
        //Hospital hospitalMaisProximoDoador = hospitais.stream().filter(h-> h.hasEstoque(produto, quantidadeSolicitada)).findFirst().get();
        //IMPLEMENTAR METODO PARA TRANSFERENCIA
        //hospitais.stream().min(Comparator.comparing(h -> h.get()));
     return false; 
    }
    
     
}
