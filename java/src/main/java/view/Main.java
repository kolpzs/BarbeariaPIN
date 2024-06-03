package view;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner respostaSC = new Scanner(System.in);
        FuncionarioView funcionarioView = new FuncionarioView();
        ClienteView clienteView = new ClienteView();
        ServicoView servicoView = new ServicoView();
        
        System.out.println("O que deseja fazer?");
        System.out.println("1- Menu Funcionario");
        System.out.println("2- Menu Cliente");
        System.out.println("3- Adicionar Serviços");
        System.out.println("4- 4");
        
        int resposta = respostaSC.nextInt();
        
        switch (resposta) {
            case 1:
                System.out.println("Você escolheu o Menu Funcionario.");
                funcionarioView.mostrarMenuFuncionario(); 
                break;
            case 2:
                System.out.println("Você escolheu Menu Cliente");
                clienteView.mostrarMenuCliente();
                break;
            case 3:
                System.out.println("Você escolheu Menu Serviços");
                servicoView.mostrarMenuServico();
                break;
            case 4:
                System.out.println("Você escolheu ...");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
        
        respostaSC.close();
    }
}
