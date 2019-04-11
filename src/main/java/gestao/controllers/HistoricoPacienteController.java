package gestao.controllers;

import com.google.gson.Gson;
import gestao.models.hospital.Hospital;
import gestao.models.leito.Leitos;
import gestao.services.HistoricoPacienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/pacientes")
@CrossOrigin(origins = "*")
public class HistoricoPacienteController {

    private static final Gson gson = new Gson();

    @Autowired
    HistoricoPacienteService historicoPacienteService;

    @PostMapping("/{cpf}/checkin") //Realiza checkin
    @ApiOperation(value="Realiza o checkin no hospital.")
    public ResponseEntity<String> checkin(@RequestBody Hospital hospital, @PathVariable(value = "cpf") String cpf){
        if(historicoPacienteService.checkin(cpf, hospital)) {
            return new ResponseEntity<String>(gson.toJson("Checkin realizado"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/internar") //Define leito de internação para o atendimento atual
    @ApiOperation(value="Realiza o internamento no hospital.")
    public ResponseEntity<String> internado(@RequestBody Leitos leito, @PathVariable(value = "cpf") String cpf) {
        if(historicoPacienteService.internar(cpf, leito)) {
            return new ResponseEntity<String>(gson.toJson("Internação realizada"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/checkout") // Realiza checkout
    @ApiOperation(value="Realiza o checkout no hospital.")
    public ResponseEntity<String> checkout(@RequestBody String descricaoAtentimento, @PathVariable(value = "cpf") String cpf) {
        if(historicoPacienteService.checkout(cpf, descricaoAtentimento)) {
            return new ResponseEntity<String>(gson.toJson("Checkout realizado"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
