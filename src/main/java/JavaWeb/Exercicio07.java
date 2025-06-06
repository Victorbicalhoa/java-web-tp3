package JavaWeb;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio07 {
    public static void main(String[] args) {
        try {
            // Atualiza a entidade 10
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // JSON com o novo nome
            String jsonInput = "{\"name\": \"atualizado\"}";

            // Envia o corpo da requisição
            try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                writer.writeBytes(jsonInput);
                writer.flush();
            }

            int status = connection.getResponseCode();
            System.out.println("Status da atualização: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();
            System.out.println("Resposta do POST:\n" + resposta);
            connection.disconnect();

            // Confirma a atualização com GET
            System.out.println("\nVerificando atualização com GET:");
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("Status do GET: " + getStatus);

            BufferedReader getReader = new BufferedReader(new InputStreamReader(
                    getConnection.getInputStream()));
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
