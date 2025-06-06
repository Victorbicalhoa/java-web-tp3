package JavaWeb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio09 {
    public static void main(String[] args) {
        try {
            // Endpoint para DELETE
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/9");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int status = connection.getResponseCode();
            System.out.println("DELETE Status HTTP: " + status);

            connection.disconnect();

            // Verificação com GET logo em seguida
            verificarEntidade();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarEntidade() {
        try {
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/9");
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            System.out.println("GET após DELETE - Status HTTP: " + status);

            if (status == 404) {
                System.out.println("Entidade não encontrada (como esperado).");
            } else {
                System.out.println("Resposta ainda está disponível (API é stateless).");
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
