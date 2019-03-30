package gestao.HistoricoPaciente;

//import gestao.Paciente.Paciente.HistoricoPaciente;
import gestao.Paciente.PacienteSemCheckoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/paciente")
@CrossOrigin(origins = "*")
public class HistoricoPacienteController {

    @Autowired
    HistoricoPacienteService historicoPacienteService;

    @PostMapping("/{cpf}/checkin") //Realiza checkin
    public void checkin(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) throws PacienteSemCheckoutException {
        historicoPacienteService.checkin(cpf, historico);
    }

    @PutMapping("/{cpf}/internado") //Define leito de internação para o atendimento atual
    public void internado(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) {
        historicoPacienteService.internado(cpf, historico);
    }

    @PutMapping("/{cpf}/checkout") // Realiza checkout
    public void checkout(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) throws PacienteSemCheckoutException {
        historicoPacienteService.checkout(cpf, historico);
    }






}
