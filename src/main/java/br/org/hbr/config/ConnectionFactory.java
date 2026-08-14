// Classe ConnectionFactory
package br.org.hbr.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    // Objeto responsável por armazenar as configurações
    // lidas do arquivo config.properties.
    private static final Properties properties = new Properties();

    // Bloco executado apenas uma vez, quando a classe é carregada.
    // Sua responsabilidade é carregar as configurações do banco.
    static {

        try (
                // Localiza o arquivo config.properties dentro da pasta resources.
                InputStream input = ConnectionFactory.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties")
        ) {

            // Carrega todas as propriedades do arquivo para o objeto Properties.
            properties.load(input);

        } catch (Exception e) {

            // Encerra a aplicação caso o arquivo não seja encontrado
            // ou ocorra algum erro durante a leitura.
            throw new RuntimeException("Erro ao carregar config.properties.", e);

        }

    }

    // Método responsável por abrir e devolver uma conexão com o banco.
    public static Connection getConnection() {

        try {

            // Cria uma conexão utilizando as informações
            // armazenadas no arquivo config.properties.
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (Exception e) {

            // Caso ocorra erro na conexão, interrompe a execução
            // informando a causa do problema.
            throw new RuntimeException("Erro ao conectar ao banco.", e);

        }

    }

}
