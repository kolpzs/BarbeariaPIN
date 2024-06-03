package view;

import java.util.List;
import java.util.Scanner;

import controller.ServicoController;
import model.entities.Servico;

public class ServicoView {

    private ServicoController servicoController;

    public ServicoView() {
        this.servicoController = new ServicoController();
    }

    public void mostrarMenuServico() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Servico:");
            System.out.println("1- Criar Servico");
            System.out.println("2- Visualizar Todos os Servicos");
            System.out.println("3- Sair");

            int resposta = scanner.nextInt();
            scanner.nextLine(); 

            switch (resposta) {
                case 1:
                    criarServico(scanner);
                    break;
                case 2:
                    visualizarTodosServicos();
                    break;
                case 3:
                    System.out.println("Saindo do menu de serviços.");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private void criarServico(Scanner scanner) {
        System.out.println("Criar Servico:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        try {
            Servico novoServico = servicoController.criarServico(nome);
            if (novoServico != null) {
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
        List<Servico> servicos = servicoController.buscarTodosServicos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço encontrado.");
        } else {
            System.out.println("Lista de Serviços:");
            for (Servico servico : servicos) {
                System.out.println("ID: " + servico.getId() + ", Nome: " + servico.getNome());
            }
        }
    }
}
