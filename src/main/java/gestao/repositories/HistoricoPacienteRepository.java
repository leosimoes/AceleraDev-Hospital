package gestao.repositories;

//import gestao.models.paciente.Paciente.HistoricoPaciente;
//import gestao.models.paciente.Paciente.Paciente;
import gestao.models.paciente.HistoricoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HistoricoPacienteRepository extends JpaRepository<HistoricoPaciente, Long> {
    HistoricoPaciente findByDataEntradaHospital(LocalDateTime id);

}
