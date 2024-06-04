package controller;

import java.util.List;

import model.entities.AgendarClienteEntity;
import model.repositories.AgendarRepository;
import model.repositories.ClienteRepository;
import model.services.AgendarClienteService;
import model.repositories.AgendarClienteRepository;

public class AgendarClienteController {

    private final AgendarClienteService agendarClienteService;

    public AgendarClienteController() {
        AgendarClienteRepository agendarClienteRepository = new AgendarClienteRepository();
        AgendarRepository agendarRepository = new AgendarRepository();
        ClienteRepository clienteRepository = new ClienteRepository();

        this.agendarClienteService = new AgendarClienteService(agendarClienteRepository, agendarRepository, clienteRepository);
    }

    public AgendarClienteEntity criarAgendarCliente(Long agendar_id, Long cliente_id) {
        try {
            return agendarClienteService.criarAgendarCliente(agendar_id, cliente_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AgendarClienteEntity buscarAgendarCliente(Long id) {
        return agendarClienteService.findById(id);
    }

    public List<AgendarClienteEntity> buscarTodosAgendarClientes() {
        return agendarClienteService.findAll();
    }

    public void deletarAgendarCliente(Long id) {
        agendarClienteService.delete(id);
    }
}
