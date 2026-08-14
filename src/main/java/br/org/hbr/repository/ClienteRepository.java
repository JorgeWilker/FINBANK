package br.org.hbr.repository;

import br.org.hbr.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import br.org.hbr.config.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.org.hbr.exception.RepositoryException;



public class ClienteRepository {


    private List<Cliente> clientes = new ArrayList<>();

    public void salvar(Cliente cliente) {
        String sql = """
            
                INSERT INTO cliente (nome, telefone)
            VALUES (?, ?)
            """;

        try (
                Connection connection = ConnectionFactory.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());

            statement.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            throw new RepositoryException(
                    "Erro ao acessar o banco de dados.", e);
        }

    }
    public void atualizar(Cliente cliente) {
        String sql = """
            UPDATE cliente
            SET    nome = ?,
                   telefone = ?
            WHERE id = ?
            """;
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setInt(3, cliente.getCodigo());


            statement.executeUpdate();

            System.out.println("Cliente atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void remover(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Cliente excluído com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public List<Cliente> listarClientes() {

        List<Cliente> clientes = new ArrayList<>();

        // atribui à variável sql o comando SQL
        String sql = "SELECT * FROM cliente";

        try {
            // realiza a conexão com o BD
            Connection connection = ConnectionFactory.getConnection();

            // insere o comando SQL em uma área protegida (PreparedStatement)
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            // executa o comando sql e retorno todos os clientes no objeto resultSet
            ResultSet resultSet = statement.executeQuery();

            // gera e guarda em uma lista cada cliente a partir dos dados do resultSet
            while (resultSet.next()) {
                String nome = "";
                String telefone = "";
                Cliente cliente = new Cliente(nome,telefone);

                cliente.setCodigo(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setTelefone(resultSet.getString("telefone"));

                clientes.add(cliente);
            }

        } catch (Exception e) {


            e.printStackTrace();
        }


        return clientes;
    }

    public Cliente buscarPorCodigo(int

    codigo) {

        return null;
    }
}
