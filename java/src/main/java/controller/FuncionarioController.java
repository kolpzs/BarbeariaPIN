package controller;

import java.util.List;

import model.entities.FuncionarioEntity;
import model.services.FuncionarioService;
import model.repositories.FuncionarioRepository;

public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController() {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        this.funcionarioService = new FuncionarioService(funcionarioRepository);
    }

    public FuncionarioEntity criarFuncionario(String nome, String login, String senha) {
        try {
            return funcionarioService.criarFuncionario(nome, login, senha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FuncionarioEntity buscarFuncionario(Long id) {
        return funcionarioService.findById(id);
    }

    public List<FuncionarioEntity> buscarTodosFuncionarios() {
        return funcionarioService.findAll();
    }
}
