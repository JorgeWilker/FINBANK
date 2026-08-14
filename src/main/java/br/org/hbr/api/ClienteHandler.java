package br.org.hbr.api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import br.org.hbr.controller.ClienteController;
import br.org.hbr.model.Cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClienteHandler implements HttpHandler {

    private final ClienteController controller;

    public ClienteHandler(ClienteController controller) {

        this.controller = controller;

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        List<Cliente> clientes =
                controller.listar();

        Gson gson = new Gson();

        String json =
                gson.toJson(clientes);

        exchange.getResponseHeaders()
                .add("Content-Type",
                        "application/json; charset=UTF-8");

        byte[] resposta =
                json.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(
                200,
                resposta.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(resposta);

        output.close();

    }

}
