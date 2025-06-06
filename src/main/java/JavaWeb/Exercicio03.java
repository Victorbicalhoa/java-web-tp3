package JavaWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio03 {
    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities/13";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status HTTP: " + statusCode);

            if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Entidade não encontrada. Verifique se o ID existe.");
            } else {
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
            }

        } catch (IOException e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }
    }
}
