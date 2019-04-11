package gestao.controllers;

import gestao.models.Produto.Produto;
import gestao.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitais/{id}/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    @ApiOperation(value = "Retorna a lista dos produtos do estoque.")
    public ResponseEntity<List<Produto>> findAll(@PathVariable(value = "id") Long id) {
        List<Produto> produtos = produtoService.getProdutos(id);
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{nome}")
    @ApiOperation(value = "Retorna um produto do estoque.")
    public ResponseEntity<Produto> find(@PathVariable(value = "nome") String nome, @PathVariable(value = "id") Long id) {
        Produto produto = produtoService.find(nome, id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping()
    @ApiOperation(value = "Salva um produto do estoque.")
    public ResponseEntity<String> add(@PathVariable(value = "id") Long id, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.adicionar(produto, id, resultado)) {
            return ResponseEntity.ok().body("Adicionado");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/add")
    @ApiOperation(value = "Acidiona produto ao estoque.")
    public ResponseEntity<String> adicionarProdutoEstoque(@PathVariable(value = "id") Long id, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.adicionaProdutoEstoque(id, produto, resultado)) {
            return ResponseEntity.ok().body("Atualizado");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/sub")
    @ApiOperation(value = "Subtrai produto ao estoque.")
    public ResponseEntity<String> subtrairProdutoEstoque(@PathVariable(value = "id") Long id, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.subtrairProdutoEstoque(id, produto, resultado)) {
            return ResponseEntity.ok().body("Atualizado");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping()
    @ApiOperation(value = "Remove um produto do estoque.")
    public ResponseEntity<String> delete(@RequestBody Produto produto, @PathVariable(value = "id") Long id) {
        if (produtoService.delete(produto, id)) {
            return ResponseEntity.ok().body("Deletado");
        }
        return ResponseEntity.notFound().build();
    }

}
