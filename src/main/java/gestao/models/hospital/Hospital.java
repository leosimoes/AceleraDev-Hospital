package gestao.models.hospital;


import gestao.models.Produto.Produto;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.leito.TipoLeitoENUM;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
public class Hospital {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Hospital deve ter um nome")
    private String nome;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    private Double latitude;
    private Double longitude;

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
}
