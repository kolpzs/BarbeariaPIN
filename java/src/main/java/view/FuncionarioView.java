package view;

import java.util.List;
import java.util.Scanner;

import controller.FuncionarioController;
import model.entities.FuncionarioEntity;

public class FuncionarioView {

    private FuncionarioController funcionarioController;

    public FuncionarioView() {
        this.funcionarioController = new FuncionarioController();
    }

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenuFuncionario() {
        
        int resposta;

        do {
            System.out.println("Menu Funcionario:");
            System.out.println("1- Criar Funcionario");
            System.out.println("2- Visualizar Todos os Funcionarios");
            System.out.println("3- Sair");

            do {
                resposta = scanner.nextInt();
                if(resposta < 1 || resposta > 3) {
                    System.out.println("Insira uma opção válida: ");
                }

            } while(resposta < 1 || resposta > 3);

            switch (resposta) {
                case 1:
                    criarFuncionario(scanner);
                    break;
                case 2:
                    visualizarTodosFuncionarios();
                    break;
                case 3:
                    System.out.println("Saindo do menu de funcionários.");
                    break;
            }
        } while(resposta != 3);
    }

    private void criarFuncionario(Scanner scanner) {
        System.out.println("Criar Funcionario:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            FuncionarioEntity novoFuncionarioEntity = funcionarioController.criarFuncionario(nome, login, senha);
            if (novoFuncionarioEntity != null) {
                System.out.println("Funcionario criado com sucesso!");
            } else {
                System.out.println("Falha ao criar funcionário. Talvez o nome já esteja em uso ou a senha seja igual ao nome.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar funcionário: " + e.getMessage());
        }
    }

    private void visualizarTodosFuncionarios() {
        List<FuncionarioEntity> funcionarioEntities = funcionarioController.buscarTodosFuncionarios();
        if (funcionarioEntities.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            System.out.println("Lista de Funcionários:");
            for (FuncionarioEntity funcionarioEntity : funcionarioEntities) {
                System.out.println("ID: " + funcionarioEntity.getId() + ", Nome: " + funcionarioEntity.getNome() + ", Login: " + funcionarioEntity.getLogin());
            }
        }
    }
}
