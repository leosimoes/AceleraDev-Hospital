package gestao.services;


import gestao.models.banco_de_sangue.BancoDeSangueFactory;
import gestao.models.hospital.HospitalDTO;

import gestao.exceptions.HospitalNotFoundException;
import gestao.models.hospital.Hospital;
import gestao.repositories.hospital.HospitalRepository;
import gestao.utils.Geolocalizacao.Ponto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

        Hospital hospital = Hospital.criarViaDTO(hospitalDTO);
        hospital.setBancoDeSangue(BancoDeSangueFactory.createDefault());

        return this.repository.save(hospital);
    }

    public Optional<Hospital> find(Long id) {
        return this.repository.findById(id);
    }

    public void update(Long id, HospitalDTO hospitalDTO) {
        this.repository.findById(id).map(x -> {
            x.atualizarViaDTO(hospitalDTO);
            return this.repository.save(x);
        }).orElseThrow(() -> new HospitalNotFoundException());
    }

    public void delete(Long id) {
        if (! this.repository.findById(id).isPresent()) {
            throw new HospitalNotFoundException();
        }
        this.repository.deleteById(id);
    }

    public List<Hospital> procurarPorHospitaisProximos(Ponto geocolocalizacao) {
        return this.repository.buscarMaisProximosPorGeo(geocolocalizacao);
    }
}
