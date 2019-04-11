package gestao.repositories;

//import gestao.models.paciente.Paciente.Paciente;
import gestao.models.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);

}
