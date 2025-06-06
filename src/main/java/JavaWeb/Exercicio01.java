package JavaWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio01 {
    public static void main(String[] args) {
        try {
            // Define a URL do endpoint
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Define o método como GET
            connection.setRequestMethod("GET");

            // Conecta e pega o código de status
            int statusCode = connection.getResponseCode();
            System.out.println("Código de Status HTTP: " + statusCode);

            // Lê o corpo da resposta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }

            reader.close();
            connection.disconnect();

            // Exibe a resposta JSON no console
            System.out.println("Resposta da API:");
            System.out.println(resposta.toString());

        } catch (Exception e) {
            System.err.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
