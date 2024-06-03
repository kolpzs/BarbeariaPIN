package controller;

import java.util.List;

import model.entities.AgendarEntity;
import model.services.AgendarService;
import model.repositories.AgendarRepository;

public class AgendarController {

    private AgendarService agendarService;

    public AgendarController() {
        AgendarRepository agendarRepository = new AgendarRepository();
        this.agendarService = new AgendarService(agendarRepository);
    }

    public AgendarEntity criarAgendar(AgendarEntity agendarEntity) {
        try {
            return agendarService.criarAgendar(agendarEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AgendarEntity buscarAgendar(Long id) {
        return agendarService.findById(id);
    }

    public List<AgendarEntity> buscarTodosAgendamentos() {
        return agendarService.findAll();
    }

    public AgendarEntity updateById(AgendarEntity agendarEntity) {
        return agendarService.updateById(agendarEntity);
    }

    public void delete(Long id) {
        agendarService.delete(id);
    }
}
