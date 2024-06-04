package model.services;

import java.util.List;

import model.entities.AgendarClienteEntity;
import model.entities.AgendarEntity;
import model.entities.ClienteEntity;
import model.repositories.AgendarClienteRepository;
import model.repositories.AgendarRepository;
import model.repositories.ClienteRepository;

public class AgendarClienteService {

    private final AgendarClienteRepository agendarClienteRepository;
    private final AgendarRepository agendarRepository;
    private final ClienteRepository clienteRepository;

    public AgendarClienteService(AgendarClienteRepository agendarClienteRepository, AgendarRepository agendarRepository, ClienteRepository clienteRepository) {
        this.agendarClienteRepository = agendarClienteRepository;
        this.agendarRepository = agendarRepository;
        this.clienteRepository = clienteRepository;
    }

    public AgendarClienteEntity criarAgendarCliente(Long agendar_id, Long cliente_id) {
        AgendarEntity agendar = agendarRepository.findById(agendar_id);
        ClienteEntity cliente = clienteRepository.findById(cliente_id);

        if (agendar == null || cliente == null) {
            throw new IllegalArgumentException("Agendar ou Cliente não encontrado");
        }

        AgendarClienteEntity novoAgendarClienteEntity = new AgendarClienteEntity(agendar_id, cliente_id);
        return (AgendarClienteEntity) agendarClienteRepository.create(novoAgendarClienteEntity);
    }

    public AgendarClienteEntity findById(Long id) {
        return agendarClienteRepository.findById(id);
    }

    public List<AgendarClienteEntity> findAll() {
        return agendarClienteRepository.findAll();
    }

    public void delete(Long id) {
        agendarClienteRepository.delete(id);
    }
}
