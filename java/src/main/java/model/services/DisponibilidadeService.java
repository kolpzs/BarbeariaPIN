package model.services;

import java.util.List;
import model.entities.DisponibilidadeEntity;
import model.repositories.DisponibilidadeRepository;

public class DisponibilidadeService {

    private DisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService() {
        this.disponibilidadeRepository = new DisponibilidadeRepository();
    }

    public DisponibilidadeEntity createDisponibilidade(DisponibilidadeEntity disponibilidadeEntity) {
        return disponibilidadeRepository.create(disponibilidadeEntity);
    }

    public DisponibilidadeEntity findDisponibilidadeById(Long id) {
        return disponibilidadeRepository.findById(id);
    }

    public DisponibilidadeEntity updateDisponibilidade(DisponibilidadeEntity disponibilidadeEntity) {
        return disponibilidadeRepository.updateById(disponibilidadeEntity);
    }

    public void deleteDisponibilidade(Long id) {
        disponibilidadeRepository.delete(id);
    }

    public List<DisponibilidadeEntity> findAllDisponibilidades() {
        return disponibilidadeRepository.findAll();
    }

    public DisponibilidadeEntity setAtivo(Long id, boolean ativo) {
        DisponibilidadeEntity disponibilidadeEntity = findDisponibilidadeById(id);
        if (disponibilidadeEntity != null) {
            disponibilidadeEntity.setAtivo(ativo);
            return updateDisponibilidade(disponibilidadeEntity);
        } else {
            throw new RuntimeException("Disponibilidade não encontrada para o ID fornecido.");
        }
    }
}
