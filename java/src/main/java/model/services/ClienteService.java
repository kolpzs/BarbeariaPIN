package model.services;

import model.entities.Cliente;
import model.repositories.ClienteRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    public Cliente createCliente(Cliente cliente) throws Exception {
        if (clienteExists(cliente.getCpf())) {
            throw new Exception("Cliente já existe com o CPF fornecido.");
        }
        
        if (!isTelefoneValido(cliente.getTelefone())) {
            throw new Exception("Número de telefone inválido.");
        }

        return clienteRepository.create(cliente);
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente updateCliente(Cliente cliente) throws Exception {
        if (!isTelefoneValido(cliente.getTelefone())) {
            throw new Exception("Número de telefone inválido.");
        }

        return clienteRepository.updateById(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.delete(id);
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    private boolean clienteExists(String cpf) {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente c : clientes) {
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
