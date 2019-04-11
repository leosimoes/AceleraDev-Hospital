package gestao.utils.Geolocalizacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
@JsonRootName("location")
public class Coordenadas {

    @JsonProperty("latitude")
    @Range(min = -90, max = 90,  message = "A longitude deve estar contida no intervalo [-90, 90]")
    @NotNull(message = "A latitude não deve ser nula e deve ser um número real.")
    private Double latitude;

    @JsonProperty("lon")
    @Range(min = -180, max = 180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @NotNull(message = "A longitude não deve ser nula e deve ser um número real.")
    private Double longitude;

    public Coordenadas(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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

    public Double distancia(Coordenadas coordenadas) {
        return DistanciaEntreCoordenadas.distancia(this, coordenadas);
    }

}
