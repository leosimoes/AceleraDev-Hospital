package gestao.controllers;

import com.google.gson.Gson;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.hospital.Hospital;
import gestao.services.BancoDeSangueSolicitacaoService;
import gestao.services.ProdutoSolicitacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável pela implementação dos controladores relacionados a solicitações.
 *
 * @author Jardel Casteluber e Leonardo Simões
 */

@RestController
@RequestMapping("/api/v1")
public class SolicitacaoController {

    private static final Gson gson = new Gson();

    @Autowired
    BancoDeSangueSolicitacaoService bancoDeSangueSolicitacaoService;

    @Autowired
    ProdutoSolicitacaoService produtoSolicitacaoService;

    @GetMapping("/hospitais/{id}/bancodesangue/{tipo}/{quantidade}")
    @ApiOperation(value = "Solicita um ativo de outro hospital.")
    public ResponseEntity<String> solicitarSangue(@PathVariable(value = "id") long id, @PathVariable(value = "tipo") BancoDeSangueENUM tipo, @PathVariable(value = "quantidade") Integer quantidade) {
        Hospital hospital = bancoDeSangueSolicitacaoService.solicitarSangue(id, tipo, quantidade);
        if (hospital != null) {
            return new ResponseEntity<String>(gson.toJson("Transferência realizada pelo hospital: " + hospital.getNome() + "; Endereço: " + hospital.getEndereco().getLocalidade() + ", " + hospital.getEndereco().getUf()), HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hospitais/{id}/produto/{nome}/{quantidade}")
    @ApiOperation(value = "Solicita um produto de outro hospital.")
    public ResponseEntity<String> solicitarProduto(@PathVariable(value = "id") Long id, @PathVariable(value = "nome") String nome, @PathVariable(value = "quantidade") Integer quantidade) {
        Hospital hospital = produtoSolicitacaoService.solicitarProduto(id, nome, quantidade);
        if (hospital != null) {
            return new ResponseEntity<String>(gson.toJson("Transferência realizada pelo hospital: " + hospital.getNome() + "; Endereço: " + hospital.getEndereco().getLocalidade() + ", " + hospital.getEndereco().getUf()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
