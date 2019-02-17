package com.diego.pm.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static java.util.Objects.isNull;

public class MerchantAPI extends RestTemplate{

    private static MerchantAPI api;

    private MerchantAPI(){
    }

    public static MerchantAPI getInstance(){
        if(isNull(api))
            api = new MerchantAPI();
        return api;
    }

    public boolean existeMerchant(String merchantId){
        String getUrl = String.format("http://localhost:8888/merchant/%s", merchantId);
        try {
            ResponseEntity<String> getResponse = api.getForEntity(getUrl, String.class);
            if (getResponse.getStatusCode() == HttpStatus.OK) {
                return true;
            }
        }
        catch(HttpClientErrorException ex){
            return false;
        }
        return false;
    }
}
