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
        int resposta;

        do {
            System.out.println("Menu Cliente:");
            System.out.println("1- Criar Cliente");
            System.out.println("2- Editar Cliente");
            System.out.println("3- Buscar Cliente");
            System.out.println("4- Excluir Cliente");
            System.out.println("5- Visualizar Todos os Clientes");
            System.out.println("6- Sair");

            do {
                resposta = scanner.nextInt();
                if(resposta < 1 || resposta > 6) {
                    System.out.println("Insira uma opção válida: ");
                }

            } while(resposta < 1 || resposta > 6);

            switch (resposta) {
                case 1:
                    criarCliente(scanner);
                    break;
                case 2:
                    editarCliente(scanner);
                    break;
                case 3:
                    buscarCliente(scanner);
                    break;
                case 4:
                    excluirCliente(scanner);
                    break;
                case 5:
                    visualizarTodosClientes();
                    break;
                case 6:
                    System.out.println("Saindo do menu de clientes.");
                    return;
            }
        } while(resposta != 6);
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

    private void editarCliente(Scanner scanner) {
        System.out.println("Editar Cliente:");

        System.out.print("ID do Cliente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consume newline left-over

        ClienteEntity clienteEntity = clienteController.buscarCliente(id);
        if (clienteEntity == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

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

        clienteEntity.setNome(nome);
        clienteEntity.setCpf(cpf);
        clienteEntity.setTelefone(telefone);
        clienteEntity.setEmail(email);
        clienteEntity.setPreferencia(preferencia);

        try {
            ClienteEntity clienteEntityEditado = clienteController.updateById(clienteEntity);
            if (clienteEntityEditado != null) {
                System.out.println("Cliente editado com sucesso!");
            } else {
                System.out.println("Falha ao editar cliente.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }


    private void buscarCliente(Scanner scanner) {
        System.out.println("Buscar Cliente:");

        System.out.print("ID do Cliente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consume newline left-over

        ClienteEntity clienteEntity = clienteController.buscarCliente(id);
        if (clienteEntity == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Detalhes do Cliente:");
        System.out.println("ID: " + clienteEntity.getId());
        System.out.println("Nome: " + clienteEntity.getNome());
        System.out.println("CPF: " + clienteEntity.getCpf());
        System.out.println("Telefone: " + clienteEntity.getTelefone());
        System.out.println("Email: " + clienteEntity.getEmail());
        System.out.println("Preferência: " + clienteEntity.getPreferencia());
    }


    private void excluirCliente(Scanner scanner) {
        System.out.println("Excluir Cliente:");

        System.out.print("ID do Cliente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consume newline left-over

        ClienteEntity clienteEntity = clienteController.buscarCliente(id);
        if (clienteEntity == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        try {
            clienteController.delete(id);
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    private void visualizarTodosClientes() {
        List<ClienteEntity> clienteEntities = clienteController.buscarTodosClientes();
        if (clienteEntities.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (ClienteEntity clienteEntity : clienteEntities) {
                System.out.println("ID: " + clienteEntity.getId());
                System.out.println("Nome: " + clienteEntity.getNome());
                System.out.println("CPF: " + clienteEntity.getCpf());
                System.out.println("Telefone: " + clienteEntity.getTelefone());
                System.out.println("Email: " + clienteEntity.getEmail());
                System.out.println("Preferência: " + clienteEntity.getPreferencia());
                System.out.println("------------------------");
            }
        }
    }

}
