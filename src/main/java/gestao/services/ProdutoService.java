package gestao.services;

import gestao.models.produto.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    public Optional<Produto> find(Long id);

    public List<Produto> findAll();

    public Produto create(Produto p);

    public boolean delete(Long id);

}
