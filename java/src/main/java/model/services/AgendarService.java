package model.services;

import java.util.List;

import model.entities.AgendarEntity;
import model.repositories.AgendarRepository;

public class AgendarService {

    private AgendarRepository agendarRepository;

    public AgendarService(AgendarRepository agendarRepository) {
        this.agendarRepository = agendarRepository;
    }

    public AgendarEntity criarAgendar(AgendarEntity agendarEntity) throws Exception {
        // Adicione aqui as validações necessárias antes de criar um agendamento
        return (AgendarEntity) agendarRepository.create(agendarEntity);
    }

    public AgendarEntity findById(Long id) {
        return agendarRepository.findById(id);
    }

    public List<AgendarEntity> findAll() {
        return agendarRepository.findAll();
    }

    public AgendarEntity updateById(AgendarEntity agendar) {
        return agendarRepository.updateById(agendar);
    }

    public void delete(Long id) {
        agendarRepository.delete(id);
    }

}
