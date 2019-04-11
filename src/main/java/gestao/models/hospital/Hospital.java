package gestao.models.hospital;


import gestao.models.produto.Produto;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.leito.TipoLeitoENUM;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 *
 * @author Michael Ulguim
 *
 */
@Entity
public class Hospital {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Hospital deve ter um nome")
    private String nome;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;


    @ElementCollection
    private Map<BancoDeSangueENUM, Integer> bancoDeSangue;

    @NotNull(message = "Precisa definir tipos e quantidade de leitos")
    @ElementCollection
    private Map<TipoLeitoENUM, Integer> leitos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Produto> produtos;

    Hospital() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Map<BancoDeSangueENUM, Integer> getBancoDeSangue() {
        return bancoDeSangue;
    }

    public void setBancoDeSangue(Map<BancoDeSangueENUM, Integer> bancoDeSangue) {
        this.bancoDeSangue = bancoDeSangue;
    }

    public Map<TipoLeitoENUM, Integer> getLeitos() {
        return leitos;
    }

    public void setLeitos(Map<TipoLeitoENUM, Integer> leitos) {
        this.leitos = leitos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public static Hospital criarComDTO(HospitalDTO dto) {
        Hospital hospital = new Hospital();
        hospital.nome = dto.getNome();
        hospital.endereco = dto.getEndereco();
        hospital.leitos = dto.getLeitos();
        return hospital;
    }

    public void alterarComDTO(HospitalDTO dto) {
        this.nome = dto.getNome();
        this.endereco = dto.getEndereco();
        this.leitos = dto.getLeitos();
    }

    public void addProduto(String nome, Integer quantidade){
        if(this.produtos == null){
            this.produtos = new ArrayList<>();
        }
        Optional<Produto> pOpt =  this.produtos.stream().filter(p-> p.getNome().equals(nome)).findFirst();
        pOpt.get().incrementaQuantidade(quantidade);
    }

    public void decrementaProduto(String nome, Integer quantidade){
        if(this.produtos != null){
            Optional<Produto> pOpt =  this.produtos.stream().filter(p-> p.getNome().equals(nome)).findFirst();
            pOpt.get().decrementaQuantidade(quantidade);
        }
    }

    public boolean hasEstoque(String nome, Integer quantidade){
        Optional<Produto> pOpt =  this.produtos.stream().filter(p-> p.getNome().equals(nome)&& p.getQuantidade()-quantidade>=4).findFirst();
        return pOpt.isPresent();
    }
}
