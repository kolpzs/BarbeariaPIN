package controller;

import java.util.List;

import model.entities.Servico;
import model.services.ServicoService;

public class ServicoController {

    private ServicoService servicoService;

    public ServicoController() {
        this.servicoService = new ServicoService();
    }

    public Servico criarServico(String nome) {
        try {
            Servico novoServico = new Servico();
            novoServico.setNome(nome);
            boolean created = servicoService.createServico(novoServico);
            if (created) {
                return novoServico;
            } else {
                throw new RuntimeException("Nome do serviço já existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar serviço", e);
        }
    }

    public Servico buscarServico(Long id) {
        return servicoService.findById(id);
    }

    public List<Servico> buscarTodosServicos() {
        return servicoService.findAll();
    }
}
