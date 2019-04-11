package gestao.controllers;

import com.google.gson.Gson;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.services.BancoDeSangueSolicitacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SolicitacaoController {

    private static final Gson gson = new Gson();

    @Autowired
    BancoDeSangueSolicitacaoService bancoDeSangueSolicitacaoService;

    @GetMapping("/hospitais/{id}/bancodesangue/{tipo}/{quantidade}")
    @ApiOperation(value="Solicita um ativo de outro hospital.")
    public ResponseEntity<String> solicitarSangue(@PathVariable(value = "id") long id, @PathVariable(value = "tipo") BancoDeSangueENUM tipo, @PathVariable(value = "quantidade") Integer quantidade) {
        if(bancoDeSangueSolicitacaoService.solicitarSangue(id, tipo, quantidade)) {
            return new ResponseEntity<String >(gson.toJson("Transferência realizada"), HttpStatus.OK);
        }
       return ResponseEntity.notFound().build();
    }


}
