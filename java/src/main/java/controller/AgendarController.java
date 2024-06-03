package controller;

import java.util.List;

import model.entities.Agendar;
import model.services.AgendarService;
import model.repositories.AgendarRepository;

public class AgendarController {

    private AgendarService agendarService;

    public AgendarController() {
        AgendarRepository agendarRepository = new AgendarRepository();
        this.agendarService = new AgendarService(agendarRepository);
    }

    public Agendar criarAgendar(Agendar agendar) {
        try {
            return agendarService.criarAgendar(agendar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Agendar buscarAgendar(Long id) {
        return agendarService.findById(id);
    }

    public List<Agendar> buscarTodosAgendamentos() {
        return agendarService.findAll();
    }
}
