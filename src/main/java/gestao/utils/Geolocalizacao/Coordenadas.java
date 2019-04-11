package gestao.utils.Geolocalizacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
@JsonRootName("location")
public class Coordenadas {

    @JsonProperty("lat")
    @Range(min = -90, max = 90,  message = "A longitude deve estar contida no intervalo [-90, 90]")
    @NotNull(message = "A latitude não deve ser nula e deve ser um número real.")
    private Double lat;

    @JsonProperty("lon")
    @Range(min = -180, max = 180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @NotNull(message = "A longitude não deve ser nula e deve ser um número real.")
    private Double lng;

    public Coordenadas(Double longitude, Double latitude) {
        this.lng = longitude;
        this.lat = latitude;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
