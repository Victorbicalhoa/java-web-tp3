package JavaWeb;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio12 {

    public static void main(String[] args) {
        try {
            String isbn = gerarISBN();
            criarItem(isbn);
            listarItens();
            atualizarItem(isbn);
            deletarItem(isbn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String gerarISBN() throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String isbn = br.readLine().replace("\"", ""); // tira aspas
        br.close();

        System.out.println(" ISBN gerado: " + isbn);
        return isbn;
    }

    static void criarItem(String isbn) throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String json = String.format("""
                {
                  "type": "book",
                  "isbn13": "%s",
                  "price": 5.99,
                  "numberinstock": 5
                }""", isbn);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
        }

        System.out.println(" Criado (status: " + conn.getResponseCode() + ")");
        imprimirResposta(conn);
    }

    static void listarItens() throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        System.out.println("\n Itens disponíveis:");
        imprimirResposta(conn);
    }

    static void atualizarItem(String isbn) throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String json = String.format("""
                {
                  "type": "book",
                  "isbn13": "%s",
                  "price": 10.99,
                  "numberinstock": 10
                }""", isbn);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
        }

        System.out.println("\n Atualizado (status: " + conn.getResponseCode() + ")");
        imprimirResposta(conn);
    }

    static void deletarItem(String isbn) throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/" + isbn);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        System.out.println("\n Deletado (status: " + conn.getResponseCode() + ")");
        imprimirResposta(conn);
    }

    static void imprimirResposta(HttpURLConnection conn) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("Erro na resposta: " + conn.getResponseCode());
        }
    }
}
