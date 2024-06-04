package model.services;

import java.util.List;

import model.entities.FuncionarioEntity;
import model.repositories.FuncionarioRepository;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioEntity criarFuncionario(String nome, String login, String senha) throws Exception {
        if (funcionarioRepository.findByNome(nome) != null) {
            return null;
        }

        if (senha.equals(nome)) {
            return null;
        }

        FuncionarioEntity novoFuncionarioEntity = new FuncionarioEntity(nome, login, senha);
        return (FuncionarioEntity) funcionarioRepository.create(novoFuncionarioEntity);
    }

    public FuncionarioEntity findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public List<FuncionarioEntity> findAll() {
        return funcionarioRepository.findAll();
    }

    public void delete(Long id) {
        funcionarioRepository.delete(id);
    }
}
