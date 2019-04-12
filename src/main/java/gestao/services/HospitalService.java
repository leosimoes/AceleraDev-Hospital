package gestao.services;


import gestao.exceptions.HospitalNaoEncontradoException;
import gestao.models.banco_de_sangue.BancoDeSangueFactory;
import gestao.models.hospital.Endereco;
import gestao.models.hospital.Hospital;
import gestao.models.hospital.HospitalDTO;
import gestao.repositories.hospital.HospitalRepository;
import gestao.utils.geolocalizacao.Coordenadas;
import gestao.utils.geolocalizacao.GoogleApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável pela implementação dos serviços relacionados a solicitações de banco de sangue.
 *
 * @author Michael Ulguim
 *
 */
@Service
public class HospitalService {

    private final HospitalRepository repository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.repository = hospitalRepository;
    }

    public Page<Hospital> findAll(Pageable page) {
        return this.repository.findAll(page);
    }

    public Hospital create(HospitalDTO hospitalDTO) {

        Hospital hospital = Hospital.criarComDTO(hospitalDTO);
        hospital.setBancoDeSangue(BancoDeSangueFactory.createDefault());

        Endereco endereco = hospital.getEndereco();

        Coordenadas coordenadas = new GoogleApi().buscarCoordenadaDoEndereco(endereco);

        endereco.setCoordenadas(coordenadas);
        hospital.setEndereco(endereco);

        return this.repository.save(hospital);
    }

    public Hospital find(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new HospitalNaoEncontradoException());
    }

    public void update(Long id, HospitalDTO hospitalDTO) {
        this.repository.findById(id).map(x -> {
            x.alterarComDTO(hospitalDTO);
            return this.repository.save(x);
        }).orElseThrow(() -> new HospitalNaoEncontradoException());
    }

    public void delete(Long id) {
        this.repository.findById(id)
                .orElseThrow(() -> new HospitalNaoEncontradoException());

        this.repository.deleteById(id);
    }

    public List<Hospital> procurarPorHospitaisProximos(Coordenadas geocolocalizacao) {
         return  this.repository.findAll()
                  .stream()
                  .distinct()
                  .sorted(
                          Comparator.comparingDouble(x -> x.getEndereco().getCoordenadas().distancia(geocolocalizacao))
                  )
                 .limit(10)
                 .collect(Collectors.toList());
    }
}
