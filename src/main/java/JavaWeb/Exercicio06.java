package JavaWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio06 {
    public static void main(String[] args) {
        String endpoint = "https://apichallenges.eviltester.com/sim/entities/11";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status HTTP: " + statusCode);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta da API:");
            System.out.println(response.toString());

        } catch (Exception e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }
    }
}
