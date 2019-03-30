package gestao.Hospital;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    public List<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    public Hospital create(Hospital hospital) {
        return this.hospitalRepository.save(hospital);
    }

    public Optional<Hospital> find(Long id) {
        return this.hospitalRepository.findById(id);
    }

    public boolean update(Long id, Hospital hospital) {
        if (! this.hospitalRepository.findById(id).isPresent()) {
            return false;
        }

        Hospital record = new Hospital();
        record.setNome(hospital.getNome());
        record.setN_leitos(hospital.getN_leitos());
        record.setEndereco(hospital.getEndereco());
        this.hospitalRepository.save(record);
        return true;
    }

    public boolean delete(Long id) {
        if (! this.hospitalRepository.findById(id).isPresent()) {
            return false;
        }
        this.hospitalRepository.deleteById(id);
        return true;
    }
}
