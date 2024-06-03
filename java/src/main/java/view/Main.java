package view;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        FuncionarioView funcionarioView = new FuncionarioView();
        ClienteView clienteView = new ClienteView();
        ServicoView servicoView = new ServicoView();
        AgendarView agendarView = new AgendarView();
        Scanner respostaMain = new Scanner(System.in);
        int resposta;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1- Menu Funcionario");
            System.out.println("2- Menu Cliente");
            System.out.println("3- Menu Disponibilidade");
            System.out.println("4- Menu Agendar");
            System.out.println("5- Menu Servico");
            System.out.println("6- Sair");

            do {
                resposta = respostaMain.nextInt();
                if(resposta < 1 || resposta > 6) {
                    System.out.println("Insira uma opção válida: ");
                }

            } while(resposta < 1 || resposta > 6);

            switch (resposta) {
                case 1:
                    funcionarioView.mostrarMenuFuncionario();
                    break;
                case 2:
                    clienteView.mostrarMenuCliente();
                    break;
                case 3:
                     // disponibilidadeView.mostrarMenuDisponibilidade();  // Atualizado
                    break;
                case 4:
                    agendarView.mostrarMenuAgendar();
                    break;
                case 5:
                    servicoView.mostrarMenuServico();
                    break;
                case 6:
                    System.out.println("Saindo do menu principal.");
                    break;
            }
        } while(resposta != 6);

        System.out.println("Obrigado por usar!");
        respostaMain.close();
    }
}
