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
        String metodo = exchange.getRequestMethod();
        switch (metodo) {
            case "GET":
                listar(exchange);
                break;

            case "POST":
                inserir(exchange);
                break;

            case "PUT":
                atualizar(exchange);
                break;

            case "DELETE":
                excluir(exchange);
                break;

            default:
                exchange.sendResponseHeaders(405, -1);

        }

    }

    private void listar(HttpExchange exchange)
            throws IOException {

        HttpUtils.responder(
                exchange,
                200,
                controller.listar()
        );

    }

    private void inserir(HttpExchange exchange)
            throws IOException {

        Gson gson = new Gson();

        Cliente cliente =
                gson.fromJson(
                        HttpUtils.lerBody(exchange),
                        Cliente.class
                );

        controller.cadastrar(
                cliente.getNome(),
                cliente.getTelefone()
        );

        HttpUtils.responder(
                exchange,
                201,
                cliente
        );

    }


    private void atualizar(HttpExchange exchange)
            throws IOException {

        Gson gson = new Gson();

        Cliente cliente =
                gson.fromJson(
                        HttpUtils.lerBody(exchange),
                        Cliente.class
                );

        cliente.setCodigo(
                HttpUtils.obterId(exchange)
        );

        controller.atualizar(cliente);

        HttpUtils.responder(
                exchange,
                200,
                cliente
        );

    }

    private void excluir(HttpExchange exchange)
            throws IOException {

        int codigo =
                HttpUtils.obterId(exchange);

        controller.excluir(codigo);

        HttpUtils.responder(
                exchange,
                200,
                "Cliente excluído com sucesso."
        );

    }
}
