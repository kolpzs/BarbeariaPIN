package view;

import java.util.List;
import java.util.Scanner;

import controller.ServicoController;
import model.entities.ServicoEntity;

public class ServicoView {

    private ServicoController servicoController;
    private final Scanner scanner = new Scanner(System.in);

    public ServicoView() {
        this.servicoController = new ServicoController();
    }

    public void mostrarMenuServico() {
        int resposta;

        do {
            System.out.println("Menu Servico:");
            System.out.println("1- Criar Servico");
            System.out.println("2- Visualizar Todos os Servicos");
            System.out.println("3- Sair");

            do {
                resposta = scanner.nextInt();
                scanner.nextLine();
                if(resposta < 1 || resposta > 3) {
                    System.out.println("Insira uma opção válida: ");
                }
            } while(resposta < 1 || resposta > 3);

            switch (resposta) {
                case 1:
                    criarServico(scanner);
                    break;
                case 2:
                    visualizarTodosServicos();
                    break;
                case 3:
                    System.out.println("Saindo do menu de serviços.");
                    break;
            }
        } while(resposta != 3);
    }

    private void criarServico(Scanner scanner) {
        System.out.println("Criar Servico:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        try {
            ServicoEntity novoServicoEntity = servicoController.criarServico(nome);
            if (novoServicoEntity != null) {
                System.out.println("Servico criado com sucesso!");
            } else {
                System.out.println("Falha ao criar serviço. O nome já está em uso.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar serviço: " + e.getMessage());
        }
    }

    private void visualizarTodosServicos() {
        List<ServicoEntity> servicoEntities = servicoController.buscarTodosServicos();
        if (servicoEntities.isEmpty()) {
            System.out.println("Nenhum serviço encontrado.");
        } else {
            System.out.println("Lista de Serviços:");
            for (ServicoEntity servicoEntity : servicoEntities) {
                System.out.println("ID: " + servicoEntity.getId() + ", Nome: " + servicoEntity.getNome());
            }
        }
    }
}
