package gestao.controllers;

import gestao.models.banco_de_sangue.BancoDeSangue;
import gestao.models.hospital.Hospital;
import gestao.services.BancoDeSangueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitais/{id}/bancodesangue")
public class BancoDeSangueController {

    @Autowired
    BancoDeSangueService bancoDeSangueService;

    @PutMapping("/adicionaSangue")
    @ApiOperation(value="Adicionar sangue ao hospital.")
    public ResponseEntity<Hospital> add(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
        if(bancoDeSangueService.adicionarSangue(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros())) {
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }

    @PutMapping("/removeSangue")
    @ApiOperation(value="Remove Sangue do hospital.")
    public ResponseEntity<Hospital> remove(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
        if(bancoDeSangueService.removerSangue(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
