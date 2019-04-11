package gestao.utils.Geolocalizacao;

import gestao.models.Endereco;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleApi implements InterfaceGeolocalizacaoAPI {

    private final String URL= "https://maps.googleapis.com/maps/api/geocode/json";
    private final String TOKEN = "AIzaSyDKbiuvRBa1McusIOiXtx9hp6de-9q6xIA";

    @Override
    public Coordenadas buscarPontoPorEndereco(Endereco endereco) {


        GoogleResults response = this.send(endereco.formattedAddress());
        GoogleResults tteste = response;
            return this.coordenadasAdapter(response);

    }
    GoogleResults send(String endereco) {
           Map<String, String> parameters = new HashMap<>();
        parameters.put("address", this.formatURIGoogle(endereco));
        parameters.put("key", TOKEN);
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(URL, GoogleResults.class, parameters);
    }

//    private ResponseGoogle send(String endereco) {
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("address", this.formatURIGoogle(endereco));
//        parameters.put("key", TOKEN);
//        RestTemplate restTemplate = new RestTemplate();
//        return  restTemplate.getForObject(URL, Map.class, parameters);
//    }


    private String formatURIGoogle(String uri) {
        return uri.replace(" ", "+");
    }

    private Coordenadas coordenadasAdapter(GoogleResults google) {
        return new Coordenadas(1D,2D);
    }

    class GoogleResults {
        List<Results> results;
    }
    class Results {
        public List<Address_component> address_components;
        public String formatted_address;
        public Geometry geometry;
    }

    class Address_component {
         String long_name;
         String short_name;
         List<String> types;
    }

    class Geometry {
        Coordenadas location;

    }


}