package org.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepService {

    public static String[] buscarEndereco(String cep) {
        try {
            String urlStr = "https://viacep.com.br/ws/" + cep + "/json/";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String json = response.toString();
            String cidade = extrairValor(json, "localidade");
            String estado = extrairValor(json, "uf");

            return new String[]{cidade, estado};

        } catch (Exception e) {
            System.out.println("Erro ao buscar CEP: " + e.getMessage());
            return new String[]{"", ""};
        }
    }

    private static String extrairValor(String json, String chave) {
        String busca = "\"" + chave + "\": \"";
        int inicio = json.indexOf(busca);
        if (inicio == -1) {
            busca = "\"" + chave + "\":\"";
            inicio = json.indexOf(busca);
        }
        inicio += busca.length();
        int fim = json.indexOf("\"", inicio);
        return json.substring(inicio, fim);
    }
}
