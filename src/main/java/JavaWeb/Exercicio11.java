package JavaWeb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("OPTIONS");

            connection.connect();

            int status = connection.getResponseCode();
            System.out.println("Status HTTP: " + status);

            // Ler o cabeçalho Allow que contém métodos permitidos
            String allowHeader = connection.getHeaderField("Allow");
            if (allowHeader != null) {
                System.out.println("Métodos HTTP permitidos: " + allowHeader);
            } else {
                System.out.println("Cabeçalho 'Allow' não encontrado.");
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
