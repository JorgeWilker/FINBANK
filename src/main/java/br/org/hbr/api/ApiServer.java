package br.org.hbr.api;

import com.sun.net.httpserver.HttpServer;
import br.org.hbr.controller.ClienteController;

import java.net.InetSocketAddress;

public class ApiServer {

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        ClienteController controller =
                new ClienteController();

        server.createContext(
                "/clientes",
                new ClienteHandler(controller)
        );

        System.out.println("Servidor iniciado na porta 8080.");

        server.start();

    }

}

