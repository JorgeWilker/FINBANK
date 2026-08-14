// Classe TesteConexao
package br.org.hbr.config;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            System.out.println("Conexão realizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
