package JavaWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio02 {

    public static void main(String[] args) {
        for (int id = 1; id <= 8; id++) {
            String urlString = "https://apichallenges.eviltester.com/sim/entities/" + id;

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                int statusCode = connection.getResponseCode();
                System.out.println("ID: " + id);
                System.out.println("Status HTTP: " + statusCode);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                reader.close();

                System.out.println("Resposta da API:");
                System.out.println(response);

            } catch (IOException e) {
                System.err.println("Erro ao fazer requisição para o ID " + id + ": " + e.getMessage());
            }

            System.out.println("=".repeat(50)); // separador visual
        }
    }
}
