package view;

import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import model.entities.ClienteEntity;

public class ClienteView {

    private ClienteController clienteController;

    public ClienteView() {
        this.clienteController = new ClienteController();
    }

    public void mostrarMenuCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Cliente:");
            System.out.println("1- Criar Cliente");
            System.out.println("2- Visualizar Todos os Clientes");
            System.out.println("3- Sair");

            int resposta = scanner.nextInt();
            scanner.nextLine(); 

            switch (resposta) {
                case 1:
                    criarCliente(scanner);
                    break;
                case 2:
                    visualizarTodosClientes();
                    break;
                case 3:
                    System.out.println("Saindo do menu de clientes.");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private void criarCliente(Scanner scanner) {
        System.out.println("Criar Cliente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Preferência: ");
        String preferencia = scanner.nextLine();
        try {
            ClienteEntity novoClienteEntity = clienteController.criarCliente(nome, telefone, cpf, email, preferencia);
            if (novoClienteEntity != null) {
                System.out.println("Cliente criado com sucesso!");
            } else {
                System.out.println("Falha ao criar cliente. Talvez o CPF já esteja em uso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
    }

    private void visualizarTodosClientes() {
        List<ClienteEntity> clienteEntities = clienteController.buscarTodosClientes();
        if (clienteEntities.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (ClienteEntity clienteEntity : clienteEntities) {
                System.out.println("ID: " + clienteEntity.getId() + ", Nome: " + clienteEntity.getNome() + ", CPF: " + clienteEntity.getCpf());
            }
        }
    }
}
