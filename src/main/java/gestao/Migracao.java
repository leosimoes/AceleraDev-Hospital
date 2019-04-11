/*package gestao;

import gestao.repositories.hospital.HospitalRepository;
import gestao.controllers.HospitalController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Migracao implements InitializingBean {

    @Autowired
    private HospitalRepository repository;

    @Override
    public void afterPropertiesSet() throws Exception {
        repository.save(getHospital(1L,"Hospital a", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(2L,"Hospital b", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(3L,"Hospital c", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(4L,"Hospital d", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(5L,"Hospital e", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(6L,"Hospital Teste", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));
        repository.save(getHospital(7L,"Hospital Ultimato", "41.123-312","teste", "complemento", "bairro", "loc", "uf", "12", "ultimo" ));

    }
    private Hospital getHospital(Long id, String nome,
                                 String cep, String Logradouro,
                                 String complemento, String bairro,
                                 String localidade, String uf,
                                 String numero, String formatter_addres){

        Hospital hospital = new Hospital();
        hospital.setId(id);
        hospital.setNome(nome);
        hospital.setCep(cep);
        hospital.setLogradouro(Logradouro);
        hospital.setComplemento(complemento);
        hospital.setBairro(bairro);
        hospital.setLocalidade(localidade);
        hospital.setUf(uf);
        hospital.setNumero(numero);
        hospital.setFormatted_address(formatter_addres);

        return hospital;

    }
}
*/