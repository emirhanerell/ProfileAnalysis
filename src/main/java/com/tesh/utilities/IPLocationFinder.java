package com.tesh.utilities;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class IPLocationFinder {
    public static String[] getLocation(String ip) throws Exception {
        String apiUrl = "http://ip-api.com/json/" + ip;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            String country = json.get("country").getAsString();
            String city = json.get("city").getAsString();

            String[] result = new String[2];
            result[0] = country;
            result[1] = city;

            return result;
        }
    }
}
