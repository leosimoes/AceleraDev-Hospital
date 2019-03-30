package gestao.Paciente;

//import gestao.Paciente.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(Long cpf);

}
