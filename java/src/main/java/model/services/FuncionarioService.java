package model.services;

import java.util.List;

import model.entities.Funcionario;
import model.repositories.FuncionarioRepository;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario criarFuncionario(String nome, String login, String senha) throws Exception {
        if (funcionarioRepository.findByNome(nome) != null) {
            return null;
        }

        if (senha.equals(nome)) {
            return null;
        }

        Funcionario novoFuncionario = new Funcionario(nome, login, senha);
        return (Funcionario) funcionarioRepository.create(novoFuncionario);
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id);
    }
    
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }
    
}
