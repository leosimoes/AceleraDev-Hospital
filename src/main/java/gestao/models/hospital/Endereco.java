package gestao.models.hospital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestao.utils.Geolocalizacao.Coordenadas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O logradouro não pode ser nulo e deve existir")
    private String logradouro;

    private String complemento;

    @NotBlank(message = "Bairro não pode ser nulo e deve existir")
    private String bairro;


    @NotBlank(message = "Localidade não pode ser nula e deve existir")
    private String localidade;


    @NotBlank
    @Size(max = 2, min = 2, message = "a Sigla de UF de ve conter apenas 2 letras")
    private String uf;

    @NotBlank
    private String numero;

    @JsonIgnore
    private Double latitude;

    @JsonIgnore
    private Double longitude;

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

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getLatitude() {
        return latitude;
    }

    private void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    private void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public void setCoordenadas(Coordenadas coordenadas) {
        this.setLongitude(coordenadas.getLng());
        this.setLatitude(coordenadas.getLat());
    }

    public Coordenadas getCoordenadas() {
        return new Coordenadas(this.getLongitude(), this.getLatitude());
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    public String formattedAddress() {
        return String.format("%s %s, %s, %s, %s", logradouro, numero, bairro, localidade, uf);
    }

}

