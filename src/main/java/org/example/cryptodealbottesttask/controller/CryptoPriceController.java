package org.example.cryptodealbottesttask.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CryptoPriceController {

    @GetMapping("/price/{cryptoPairs}")
    public Map<String, String> getCryptoPrice(@PathVariable("cryptoPairs") String[] cryptoPairs) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> prices = new HashMap<>();

        for (String cryptoPair : cryptoPairs) {
            String apiUrl = "https://api.binance.com/api/v3/ticker/price?symbol=" + cryptoPair;
            String response = restTemplate.getForObject(apiUrl, String.class);

            JSONObject jsonObject = new JSONObject(response);
            String price = jsonObject.getString("price");

            prices.put(cryptoPair, price);
        }

        return prices;
    }
}
