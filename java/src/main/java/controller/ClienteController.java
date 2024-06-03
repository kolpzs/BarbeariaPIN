package controller;

import java.util.List;

import model.entities.Cliente;
import model.services.ClienteService;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public Cliente criarCliente(String nome, String telefone, String cpf, String email, String preferencia) {
        try {
            Cliente novoCliente = new Cliente(nome, telefone, cpf, email, preferencia);
            return clienteService.createCliente(novoCliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar cliente", e);
        }
    }

    public Cliente buscarCliente(Long id) {
        return clienteService.findClienteById(id);
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteService.findAllClientes();
    }
}
