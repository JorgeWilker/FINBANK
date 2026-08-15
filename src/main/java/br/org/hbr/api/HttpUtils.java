package br.org.hbr.api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    private static final Gson gson = new Gson();

    public static String lerBody(HttpExchange exchange)
            throws IOException {

        InputStream input = exchange.getRequestBody();

        return new String(
                input.readAllBytes(),
                StandardCharsets.UTF_8
        );

    }

    public static void responder(HttpExchange exchange,
                                 int status,
                                 Object objeto)
            throws IOException {

        String json = gson.toJson(objeto);

        byte[] resposta =
                json.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .add("Content-Type",
                        "application/json; charset=UTF-8");

        exchange.sendResponseHeaders(
                status,
                resposta.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(resposta);

        output.close();

    }
    public static int obterId(HttpExchange exchange) {

        String caminho =
                exchange.getRequestURI().getPath();

        String[] partes =
                caminho.split("/");

        return Integer.parseInt(
                partes[partes.length - 1]
        );

    }

}
