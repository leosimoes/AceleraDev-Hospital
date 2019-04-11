package gestao.utils.Geolocalizacao;

import gestao.exceptions.CoordenadaNaoEncontradaException;
import gestao.models.hospital.Endereco;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;

public class GoogleApi implements InterfaceGeolocalizacaoAPI {

    private static final String URL= "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String TOKEN = "AIzaSyDKbiuvRBa1McusIOiXtx9hp6de-9q6xIA";

    @Override
    public Coordenadas buscarCoordenadaDoEndereco(Endereco endereco) {

        try {
            JSONObject response = this.send(endereco.formattedAddress());
            return this.coordenadasAdapter(response);
        } catch (JSONException e) {
            throw new CoordenadaNaoEncontradaException();
        } catch (Exception e) {
            throw new CoordenadaNaoEncontradaException();
        }
    }

    private Coordenadas coordenadasAdapter(JSONObject response) throws JSONException {
        JSONArray results = response.getJSONArray("results");

        JSONObject location = results.getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        Double lat = Double.parseDouble(location.getString("lat"));
        Double lng = Double.parseDouble(location.getString("lng"));
        return new Coordenadas(lng, lat);
    }

    private JSONObject send(String endereco) throws JSONException {
        String url = URL + "?address="+this.formatURIGoogle(endereco)+"&key="+TOKEN;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        return  new JSONObject(responseEntity.getBody());


    }

    private String formatURIGoogle(String uri) {
        return uri.replace(" ", "+");
    }

}