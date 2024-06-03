package model.services;

import model.entities.ClienteEntity;
import model.repositories.ClienteRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws Exception {
        if (clienteExists(clienteEntity.getCpf())) {
            throw new Exception("Cliente já existe com o CPF fornecido.");
        }
        
        if (!isTelefoneValido(clienteEntity.getTelefone())) {
            throw new Exception("Número de telefone inválido.");
        }

        return clienteRepository.create(clienteEntity);
    }

    public ClienteEntity findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity updateCliente(ClienteEntity clienteEntity) throws Exception {
        if (!isTelefoneValido(clienteEntity.getTelefone())) {
            throw new Exception("Número de telefone inválido.");
        }

        return clienteRepository.updateById(clienteEntity);
    }

    public void deleteCliente(Long id) {
        clienteRepository.delete(id);
    }

    public List<ClienteEntity> findAllClientes() {
        return clienteRepository.findAll();
    }

    private boolean clienteExists(String cpf) {
        List<ClienteEntity> clienteEntities = clienteRepository.findAll();
        for (ClienteEntity c : clienteEntities) {
            if (c.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTelefoneValido(String telefone) {
        String regex = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }
}
