package com.diego.pm.clients;

import com.diego.pm.entities.Exchange;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class FixerIOAPI extends RestTemplate{

    private static FixerIOAPI api;
    private static final String ACCESS_KEY = "0d43c431b9123d647519f2632155c26c";

    private FixerIOAPI(){
    }

    public static FixerIOAPI getInstance(){
        if(isNull(api))
            api = new FixerIOAPI();
        return api;
    }

    public Exchange getExchangeRates() throws JSONException {
        String getUrl = String.format("http://data.fixer.io/api/latest?access_key=%s&symbols=USD,UYU&format=1", ACCESS_KEY);
        ResponseEntity<String> getResponse = api.getForEntity(getUrl, String.class);
        if (getResponse.getStatusCode() == HttpStatus.OK) {
            JSONObject json = new JSONObject(getResponse.getBody());
            return new Exchange(ObjectId.get(), json.getJSONObject("rates").getDouble("USD"),json.getJSONObject("rates").getDouble("UYU"), LocalDate.now());
        }
        return null;
    }
}
