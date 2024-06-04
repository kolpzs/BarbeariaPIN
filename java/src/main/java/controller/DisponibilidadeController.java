package controller;

import java.time.LocalDateTime;
import java.util.List;

import model.entities.DisponibilidadeEntity;
import model.services.DisponibilidadeService;

public class DisponibilidadeController {

    private DisponibilidadeService disponibilidadeService;

    public DisponibilidadeController() {
        this.disponibilidadeService = new DisponibilidadeService();
    }

    public DisponibilidadeEntity criarDisponibilidade(Long funcionarioId, LocalDateTime horarioInicio, LocalDateTime horarioFim) {
        try {
            DisponibilidadeEntity disponibilidadeEntity = new DisponibilidadeEntity();
            disponibilidadeEntity.setFuncionarioId(funcionarioId);
            disponibilidadeEntity.setHorarioInicio(horarioInicio);
            disponibilidadeEntity.setHorarioFim(horarioFim);
            return disponibilidadeService.createDisponibilidade(disponibilidadeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DisponibilidadeEntity buscarDisponibilidade(Long id) {
        return disponibilidadeService.findDisponibilidadeById(id);
    }

    public List<DisponibilidadeEntity> buscarTodasDisponibilidades() {
        return disponibilidadeService.findAllDisponibilidades();
    }

    public DisponibilidadeEntity atualizarDisponibilidade(DisponibilidadeEntity disponibilidadeEntity) {
        try {
            return disponibilidadeService.updateDisponibilidade(disponibilidadeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletarDisponibilidade(Long id) {
        disponibilidadeService.deleteDisponibilidade(id);
    }

    public DisponibilidadeEntity setAtivo(Long id, boolean ativo) {
        try {
            return disponibilidadeService.setAtivo(id, ativo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
