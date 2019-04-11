package gestao.repositories.hospital;

import gestao.models.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HospitalRepository extends JpaRepository<Hospital, Long>, HospitalGeoRepository {

}