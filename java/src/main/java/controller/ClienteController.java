package controller;

import java.util.List;

import model.entities.ClienteEntity;
import model.services.ClienteService;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public ClienteEntity criarCliente(String nome, String telefone, String cpf, String email, String preferencia) {
        try {
            ClienteEntity novoClienteEntity = new ClienteEntity(nome, telefone, cpf, email, preferencia);
            return clienteService.createCliente(novoClienteEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar cliente", e);
        }
    }

    public ClienteEntity buscarCliente(Long id) {
        return clienteService.findClienteById(id);
    }

    public List<ClienteEntity> buscarTodosClientes() {
        return clienteService.findAllClientes();
    }
}
