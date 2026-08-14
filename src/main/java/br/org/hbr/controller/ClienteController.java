package br.org.hbr.controller;

import br.org.hbr.model.Cliente;
import br.org.hbr.repository.ClienteRepository;
import java.util.List;

public class ClienteController {

    private ClienteRepository repository;

    public ClienteController() {
        repository = new ClienteRepository();
    }

    public void cadastrar(String nome, String telefone) {


        Cliente cliente =
                new Cliente(nome,telefone);

        repository.salvar(cliente);
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = repository.listarClientes();
        return clientes;
    }


    public Cliente buscar(int codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    public void alterar(Cliente cliente) {
        repository.atualizar(cliente);
    }


    public void excluir(int id) {
        repository.excluir(id);
    }

}
