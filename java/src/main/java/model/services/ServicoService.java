package model.services;

import java.util.List;

import model.entities.ServicoEntity;
import model.repositories.ServicoRepository;

public class ServicoService {

    private ServicoRepository servicoRepository;

    public ServicoService() {
        this.servicoRepository = new ServicoRepository();
    }

    public boolean createServico(ServicoEntity servicoEntity) {
        if (isNomeExistente(servicoEntity.getNome())) {
            return false; 
        }
        servicoRepository.create(servicoEntity);
        return true;
    }

    public boolean updateServico(ServicoEntity servicoEntity) {
        if (isNomeExistente(servicoEntity.getNome())) {
            return false; 
        }
        servicoRepository.updateById(servicoEntity);
        return true;
    }

    private boolean isNomeExistente(String nome) {
        List<ServicoEntity> servicoEntities = servicoRepository.findAll();
        for (ServicoEntity s : servicoEntities) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public ServicoEntity findById(Long id) {
        return servicoRepository.findById(id);
    }

    public List<ServicoEntity> findAll() {
        return servicoRepository.findAll();
    }

    public void deleteServico(Long id) {
        servicoRepository.delete(id);
    }
}
