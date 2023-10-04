package nbp.service;

import nbp.model.ExchangeRate;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {
    private static final CurrencyService instance = new CurrencyService();
    private static final String URL = "http://api.nbp.pl/api/exchangerates/rates/a/";

    private CurrencyService(){}
    public static CurrencyService getInstance(){
        return instance;
    }

    public List<ExchangeRate> getExchangeRates(String currencyCode, String startDate, String stopDate) throws Exception {
        String url = URL + currencyCode + "/" + startDate + "/" + stopDate + "/";
        String jsonResponse = fetchJsonResponse(url);
        return parseExchangeRates(jsonResponse);
    }

    private String fetchJsonResponse(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    private List<ExchangeRate> parseExchangeRates(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray ratesArray = jsonObject.getJSONArray("rates");

        List<ExchangeRate> exchangeRates = new ArrayList<>();
        for (int i = 0; i < ratesArray.length(); i++) {
            JSONObject rateObject = ratesArray.getJSONObject(i);
            String no = rateObject.getString("no");
            String effectiveDate = rateObject.getString("effectiveDate");
            double mid = rateObject.getDouble("mid");

            exchangeRates.add(new ExchangeRate(no, effectiveDate, mid));
        }
        return exchangeRates;
    }

    public void saveExchangeRatesToFile(String currencyCode, String startDate, String endDate, String filePath) throws Exception {
        String url = URL + currencyCode + "/" + startDate + "/" + endDate + "/";
        String jsonResponse = fetchJsonResponse(url);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonResponse);
        }
    }
}
