package model.services;

import java.util.List;

import model.entities.Servico;
import model.repositories.ServicoRepository;

public class ServicoService {

    private ServicoRepository servicoRepository;

    public ServicoService() {
        this.servicoRepository = new ServicoRepository();
    }

    public boolean createServico(Servico servico) {
        if (isNomeExistente(servico.getNome())) {
            return false; 
        }
        servicoRepository.create(servico);
        return true;
    }

    public boolean updateServico(Servico servico) {
        if (isNomeExistente(servico.getNome())) {
            return false; 
        }
        servicoRepository.updateById(servico);
        return true;
    }

    private boolean isNomeExistente(String nome) {
        List<Servico> servicos = servicoRepository.findAll();
        for (Servico s : servicos) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public Servico findById(Long id) {
        return servicoRepository.findById(id);
    }

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public void deleteServico(Long id) {
        servicoRepository.delete(id);
    }
}
