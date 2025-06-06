package JavaWeb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio05 {
    public static void main(String[] args) {
        String endpoint = "https://apichallenges.eviltester.com/sim/entities";
        String jsonBody = "{\"name\": \"aluno\"}";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // habilita envio de dados

            // Envia o JSON
            try (DataOutputStream output = new DataOutputStream(connection.getOutputStream())) {
                output.writeBytes(jsonBody);
                output.flush();
            }

            int statusCode = connection.getResponseCode();
            System.out.println("Status HTTP: " + statusCode);

            // Lê a resposta
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
