package JavaWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio04 {
    public static void main(String[] args) {
        // Parâmetros fictícios
        String categoria = "teste";
        int limite = 5;

        String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
        String finalUrl = baseUrl + "?categoria=" + categoria + "&limite=" + limite;

        try {
            URL url = new URL(finalUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            System.out.println("URL final montada: " + finalUrl);
            System.out.println("Status HTTP: " + statusCode);

            // Leitura da resposta
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta da API:");
            System.out.println(response);

        } catch (IOException e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }
    }
}
