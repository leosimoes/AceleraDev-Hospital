package gestao.Paciente;


import gestao.HistoricoPaciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping//Salva um paciente
    public Paciente salvaPaciente(@RequestBody Paciente paciente) {
        return pacienteService.salvaPaciente(paciente);
    }

    @GetMapping //Retorna Lista de todos os pacientes
    public List<Paciente> listaPacientes() {
        return pacienteService.buscaTodosPaciente();
    }

    @GetMapping("/{cpf}") //Retorna um paciente pelo CPF
    public Paciente paciente(@PathVariable(value = "cpf") Long cpf) {
        return pacienteService.buscaPacientePorCpf(cpf);
    }

    @PutMapping //Atualiza um paciente passado por parâmetro
    public Paciente atualizaPaciente(@RequestBody Paciente paciente) {
        return pacienteService.salvaPaciente(paciente);
    }

    @GetMapping("/{cpf}/historico") //Retorna o histórico de um paciente pelo CPF
    public List<HistoricoPaciente> historicoPaciente(@PathVariable(value = "cpf") Long cpf) {
        return pacienteService.historicoPaciente(cpf);
    }



}
