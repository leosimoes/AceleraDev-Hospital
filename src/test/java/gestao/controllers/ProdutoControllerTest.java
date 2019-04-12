package gestao.controllers;

import gestao.models.hospital.Hospital;
import gestao.models.produto.Produto;
import gestao.services.HospitalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HospitalService hospitalService;

    private final HttpHeaders httpHeaders;
    private Optional<Hospital> hospitalTest;
    private Produto produtoTest;

    @Mock
    private Hospital hospitalMock;

    @Mock
    private Produto produtoMock;

    public ProdutoControllerTest() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Before
    public void setup() {
      hospitalTest = Optional.ofNullable(hospitalService.find(1L));
        if (hospitalTest.isPresent()) {
            List<Produto> produtos = hospitalTest.get().getProdutos();
            if (produtos.size() > 0) {
                produtos.stream().forEach(p -> hospitalTest.get().addProduto(p.getNome(), p.getQuantidade()));
                produtoTest = produtos.get(0);
            }
        }
    }

    @Test
    public void findAll() {
        ResponseEntity<List<Produto>> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto", HttpMethod.GET, null, new ParameterizedTypeReference<List<Produto>>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void find() {
        ResponseEntity<Produto> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto", HttpMethod.GET, null, new ParameterizedTypeReference<Produto>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

   /* @Test
    public void add() {
        ResponseEntity<String> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto/add",
                HttpMethod.POST,
                new ParameterizedTypeReference<String>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void adicionarProdutoEstoque() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto/add",
                HttpMethod.PUT,
                new ParameterizedTypeReference<Void>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }*/

    @Test
    public void removerProdutoEstoque() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto/sub", HttpMethod.PUT, null, new ParameterizedTypeReference<Void>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/hospitais/" + hospitalTest.get().getId().toString()
                + "/produto", HttpMethod.DELETE, null, new ParameterizedTypeReference<Void>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
