package gestao;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import gestao.models.hospital.Hospital;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import com.google.gson.Gson;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainn {
    public static void main(String[] args) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String  myArray = new String();
        List<Object> results1 = new ArrayList<Object>();
        List<Object> results2 = new ArrayList<Object>();
        Gson gson = new Gson();


        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=502+Rua+Sao+Paulo+Bela+Vista+Rio+das+Ostras+RJ&key=AIzaSyBfxfY86Slxa5zjLKqmFBTxhwahMkUke6M";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        JSONObject results = new JSONObject(responseEntity.getBody());

       JSONArray q = results.getJSONArray("results");

       JSONObject z = q.getJSONObject(0);

        z = z.getJSONObject("geometry");

        z = z.getJSONObject("location");

       Float lat = Float.parseFloat(z.getString("lat"));
       Float lng = Float.parseFloat(z.getString("lng"));





      Map<String, JSONObject> map = new HashMap<>();

    //    map.put("results", results.getJSONObject("results"));

     //   results1.add(results.getJSONObject("results")) ;





            //  Gson gson = new Gson();
      //  results1 = gson.toJson(results);

      //  myArray = responseEntity.getBody();
        System.out.println(results1);
        // System.out.println(responseEntity.getBody());
    }
}