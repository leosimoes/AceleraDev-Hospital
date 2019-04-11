package gestao.controllers;

import gestao.models.paciente.HistoricoPaciente;
import gestao.models.paciente.Paciente;
import gestao.services.PacienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//import gestao.models.paciente.Paciente.HistoricoPaciente;
//import gestao.models.paciente.Paciente.Paciente;

@RestController
@RequestMapping(value = "/api/v1/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    @ApiOperation(value="Salva um paciente.")
    public ResponseEntity<Paciente> salvaPaciente(@RequestBody @Valid Paciente paciente, BindingResult resultado) {
        if (pacienteService.salvaPaciente(paciente, resultado)) {
            return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping 
    @ApiOperation(value="Retorna a lista com todos os pacientes.")
    public ResponseEntity<List<Paciente>> listaPacientes() {
        return new ResponseEntity<List<Paciente>>(pacienteService.buscaTodosPaciente(), HttpStatus.FOUND);
    }

    @GetMapping("/{cpf}")
    @ApiOperation(value="Retorna o paciente através CPF.")
    public ResponseEntity<Paciente> paciente(@PathVariable(value = "cpf") String cpf) {
        Optional<Paciente> paciente = pacienteService.buscaPacientePorCpf(cpf);
        if(paciente != null && paciente.isPresent()) {
            return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.FOUND);
        } else {
           return  ResponseEntity.notFound().build(); // Nao vai chegar aqui pq lança exception no service.
        }
    }

    @PutMapping //Atualiza um paciente passado por parâmetro
    @ApiOperation(value="Atualiza um paciente.")
    public ResponseEntity<Paciente> atualizaPaciente(@RequestBody @Valid Paciente paciente, BindingResult resultado) {
        if (pacienteService.salvaPaciente(paciente, resultado)) {
            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{cpf}/historico") //Retorna o histórico de um paciente pelo CPF
    @ApiOperation(value="Retorna o histórico do paciente.")
    public ResponseEntity<List<HistoricoPaciente>> historicoPaciente(@PathVariable(value = "cpf") String cpf) {
        List<HistoricoPaciente> historicoPacientes = pacienteService.historicoPaciente(cpf);
        if (historicoPacientes != null) {
            return new ResponseEntity<List<HistoricoPaciente>>(historicoPacientes, HttpStatus.FOUND);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }


}
