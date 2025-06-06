package JavaWeb;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio08 {
    public static void main(String[] args) {
        try {
            // PUT para atualizar completamente a entidade 10
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // JSON enviado
            String jsonInput = "{\"name\": \"atualizado\"}";

            // Envia a requisição PUT
            try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                writer.writeBytes(jsonInput);
                writer.flush();
            }

            int status = connection.getResponseCode();
            System.out.println("Status da requisição PUT: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();
            System.out.println("Resposta do PUT:\n" + resposta);

            connection.disconnect();

            // Verifica com GET
            System.out.println("\nVerificando com GET...");
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("Status do GET: " + getStatus);

            BufferedReader getReader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
            String linhaGet;
            StringBuilder respostaGet = new StringBuilder();
            while ((linhaGet = getReader.readLine()) != null) {
                respostaGet.append(linhaGet);
            }
            getReader.close();
            System.out.println("Resposta do GET:\n" + respostaGet);

            getConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
