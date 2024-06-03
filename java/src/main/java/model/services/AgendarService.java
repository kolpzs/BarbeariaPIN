package model.services;

import java.util.List;

import model.entities.Agendar;
import model.repositories.AgendarRepository;

public class AgendarService {

    private AgendarRepository agendarRepository;

    public AgendarService(AgendarRepository agendarRepository) {
        this.agendarRepository = agendarRepository;
    }

    public Agendar criarAgendar(Agendar agendar) throws Exception {
        // Adicione aqui as validações necessárias antes de criar um agendamento
        return (Agendar) agendarRepository.create(agendar);
    }

    public Agendar findById(Long id) {
        return agendarRepository.findById(id);
    }

    public List<Agendar> findAll() {
        return agendarRepository.findAll();
    }

}
