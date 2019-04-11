package gestao.services;

import gestao.exceptions.paciente.PacienteNaoEncontradoException;
import gestao.models.paciente.HistoricoPaciente;
import gestao.models.paciente.Paciente;
import gestao.exceptions.paciente.PacienteNaoEncontradoException;
import gestao.repositories.PacienteRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

//import gestao.models.paciente.Paciente.HistoricoPaciente;
//import gestao.models.paciente.Paciente.Paciente;

@Service
public class PacienteService {

    //  @Autowired
    private final PacienteRepository pacienteRepository;
    //  @Autowired
    // private final HistoricoPacienteRepository historicoPacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


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
             optional = pacienteRepository.findById(paciente.getId());    //Optional só funciona para busca pelo ID. Neste caso, a busca é pelo CPF
            return optional;
        } catch (Exception ex) {
            throw  new PacienteNaoEncontradoException("Paciente não encontrado"); //Tentar colocar um retorno que não gere o trace. Apenas a mensagem
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
