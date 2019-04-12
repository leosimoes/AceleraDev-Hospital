package gestao.services;

import gestao.exceptions.paciente.PacienteNaoEncontradoException;
import gestao.models.paciente.HistoricoPaciente;
import gestao.models.paciente.Paciente;
import gestao.repositories.HistoricoPacienteRepository;
import gestao.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Classe responsável pela implementação dos serviços relacionados ao paciente.
 *
 * @author Jardel Casteluber
 *
 */

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    HistoricoPacienteRepository historicoPacienteRepository;


    public boolean salvaPaciente(Paciente paciente, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return false;
        }
        paciente.setCpf(paciente.getCpf().replaceAll(Pattern.quote("."), "").replaceAll(("-"), ""));
        pacienteRepository.save(paciente);
        return true;
    }

    public List<Paciente> buscaTodosPaciente() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        return listaPacientes;
    }

    public Optional<Paciente> buscaPacientePorCpf(String cpf) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        Optional<Paciente> optional = null;
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            optional = pacienteRepository.findById(paciente.getId());
            return optional;
        } catch (Exception ex) {
            throw new PacienteNaoEncontradoException("Paciente não encontrado");
        }
    }

    public List<HistoricoPaciente> historicoPaciente(String cpf) {
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            return paciente.pegaHistoricoPaciente();
        } catch (Exception ex) {
            return null;
        }
    }

}
