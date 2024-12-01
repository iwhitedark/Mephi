package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    private static final String YANDEX_API_URL = "https://api.weather.yandex.ru/v2/forecast";
    private static final String YANDEX_API_KEY = "...";
    private static final double LAT = 55.65;
    private static final double LON = 37.65;
    private static final int PERIOD = 7; // Период, за который будет рассчитано среднее (7 дней)

    public static void main(String[] args) {
        try {
            String responseStr = getWeatherData();
            System.out.println("Полученные данные: ");
            System.out.println(responseStr);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseStr);

            // Получение текущей температуры
            int temp = rootNode.path("fact").path("temp").asInt();
            System.out.println("Температура: " + temp + "°C");

            // Вычисление средней температуры
            double averageTemp = calculateAverageTemp(rootNode);
            System.out.println("Средняя температура за " + PERIOD + " дней: " + averageTemp + "°C");

            // Извлечение названия города
            String cityName = rootNode.path("geo_object").path("locality").path("name").asText();
            System.out.println("Город: " + cityName);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String getWeatherData() throws Exception {
        URL url = new URL(YANDEX_API_URL + "?lat=" + LAT + "&lon=" + LON + "&limit=" + PERIOD);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Yandex-API-Key", YANDEX_API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private static double calculateAverageTemp(JsonNode rootNode) {
        JsonNode forecasts = rootNode.path("forecasts");
        double totalTemp = 0;
        int count = 0;

        for (JsonNode forecast : forecasts) {
            if (count >= PERIOD) break;
            totalTemp += forecast.path("parts").path("day").path("temp_avg").asDouble();
            count++;
        }

        return count > 0 ? totalTemp / count : 0;
    }
}
