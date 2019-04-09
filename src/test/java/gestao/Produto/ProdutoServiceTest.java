package gestao.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

import gestao.models.produto.Produto;
import gestao.respositories.ProdutoRepository;
import gestao.services.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    void deveRetornarTodosOsProdutos() {
        List<Produto> listaprodutos = new ArrayList<Produto>();
        listaprodutos.add(Mockito.mock(Produto.class));
        when(this.produtoRepository.findAll()).thenReturn(listaprodutos);

        List<Produto> produtos = this.produtoService.findAll();
 
        assertEquals(produtos, listaprodutos);
        assertEquals(produtos.size(), listaprodutos.size());
    }
    
    @Test
    void deveCriarUmProduto() {
        Produto produto = Mockito.mock(Produto.class);
        when(this.produtoRepository.save(any(Produto.class))).thenReturn(produto);
        assertEquals(produto, this.produtoService.create(produto));

    }

    @Test
    void deveBuscarUmProdutoPeloId() {
        Produto hospital = Mockito.mock(Produto.class);
        when(this.produtoRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
        assertEquals(this.produtoService.find(anyLong()), Optional.of(hospital));
    }

/*
    @Test
    void DeveFazerOUpdateDoProduto() {
        Produto hospital = Mockito.mock(Produto.class);
        when(this.produtoRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
        when(this.produtoRepository.save(hospital)).thenReturn(hospital);
        assertTrue(this.produtoService.update(anyLong(), hospital));

    }

    @Test
    void deveRetornarFalsoCasoProdutoNaoExista() {
        when(this.produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(this.produtoService.update(anyLong(), new Produto()));
    }
*/
   /* @Test
    void deveDeletarProdutoPeloId() {
        when(this.produtoRepository.findById(anyLong()))
                .thenReturn(Optional.of(Mockito.mock(Produto.class)));
       assertTrue(this.produtoService.delete(anyLong()));
    }

    @Test
    void deveRetornaFalsoCasoNaoExistaProdutoAoDeletar() {
        when(this.produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
       assertFalse(this.produtoService.delete(anyLong()));
}
    
    */
}
