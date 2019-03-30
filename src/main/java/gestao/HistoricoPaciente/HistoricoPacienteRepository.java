package gestao.HistoricoPaciente;

import gestao.Paciente.Paciente;
//import gestao.Paciente.Paciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoPacienteRepository extends JpaRepository<HistoricoPaciente, Long> {
    HistoricoPaciente findByDataEntradaHospital(LocalDateTime id);

}
