package gestao.controllers;

import gestao.models.produto.Produto;
import gestao.services.ProdutoServiceImpl;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitais/{idHospital}/produtos")
public class ProdutoController {

    @Autowired
    ProdutoServiceImpl produtoService;

    @GetMapping
    @ApiOperation(value = "Retorna a lista dos produtos do estoque.")
    public ResponseEntity<List<Produto>> findAll(@PathVariable(value = "idHospital") Long idHospital) {
        List<Produto> produtos = produtoService.findAll(idHospital);
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{nome}")
    @ApiOperation(value = "Retorna um produto do estoque.")
    public ResponseEntity<Produto> find(@PathVariable(value = "idHospital") Long idHospital, @PathVariable(value = "nome") String nome) {
        Optional<Produto> produto = produtoService.find(idHospital, nome);
        if (produto.isPresent()) {
            return ResponseEntity.ok().body(produto.get());
        }
        return ResponseEntity.notFound().build();
    }

    //  @PostMapping(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(consumes = "application/json")
    @ApiOperation(value = "Salva um produto do estoque.")
    public ResponseEntity<String> add(@PathVariable(value = "idHospital") Long idHospital, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.add(idHospital, produto, resultado)) {
            return ResponseEntity.ok().body("Adicionado");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um produto do estoque.")
    public ResponseEntity<String> atualizar(@PathVariable(value = "idHospital") Long idHospital, @PathVariable(value = "id") Long id, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.update(idHospital, id, produto, resultado)) {
            return ResponseEntity.ok().body("Atualizado");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um produto do estoque.")
    public ResponseEntity<String> delete(@PathVariable(value = "idHospital") Long idHospital, @PathVariable(value = "id") Long id) {
        if (produtoService.delete(idHospital, id)) {
            return ResponseEntity.ok().body("Deletado");
        }
        return ResponseEntity.notFound().build();
    }
}
