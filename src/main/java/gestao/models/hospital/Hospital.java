package gestao.models.hospital;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gestao.models.Produto.Produto;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.leito.TipoLeitoENUM;
import java.util.ArrayList;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
public class Hospital {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String nome;


    @NotBlank
    private String cep;


    @NotBlank
    private String logradouro;


    private String complemento;


    @NotBlank
    private String bairro;


    @NotBlank
    private String localidade;


    @NotBlank
    private String uf;

    @NotBlank
    private String numero;

    private String formatted_address;

    @Range(min = -90, max = 90,  message = "A longitude deve estar contida no intervalo [-90, 90]")
    @NotNull(message = "A latitude não deve ser nula e deve ser um número real.")
    private Double latitude;

    @Range(min = -180, max = 180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @NotNull(message = "A longitude não deve ser nula e deve ser um número real.")
    private Double longitude;

    @JsonIgnore
    @ElementCollection
    private Map<BancoDeSangueENUM, Integer> bancoDeSangue;


    @ElementCollection
    private Map<TipoLeitoENUM, Integer> leitos;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Produto> produtos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
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
