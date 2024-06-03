package controller;

import java.util.List;

import model.entities.ServicoEntity;
import model.services.ServicoService;

public class ServicoController {

    private ServicoService servicoService;

    public ServicoController() {
        this.servicoService = new ServicoService();
    }

    public ServicoEntity criarServico(String nome) {
        try {
            ServicoEntity novoServicoEntity = new ServicoEntity();
            novoServicoEntity.setNome(nome);
            boolean created = servicoService.createServico(novoServicoEntity);
            if (created) {
                return novoServicoEntity;
            } else {
                throw new RuntimeException("Nome do serviço já existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar serviço", e);
        }
    }

    public ServicoEntity buscarServico(Long id) {
        return servicoService.findById(id);
    }

    public List<ServicoEntity> buscarTodosServicos() {
        return servicoService.findAll();
    }
}
