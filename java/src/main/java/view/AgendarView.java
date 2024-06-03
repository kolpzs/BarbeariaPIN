package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import controller.AgendarController;
import model.entities.Agendar;
import model.entities.ClienteEntity;
import model.entities.FuncionarioEntity;
import model.entities.ServicoEntity;

public class AgendarView {

    private AgendarController agendarController;

    public AgendarView() {
        this.agendarController = new AgendarController();
    }

    public void mostrarMenuAgendar() throws ParseException {

        Scanner scanner = new Scanner(System.in);
        int resposta;

        do {
            System.out.println("Menu Agendar:");
            System.out.println("1- Criar Agendamento");
            System.out.println("2- Editar Agendamento");
            System.out.println("3- Buscar Agendamento");
            System.out.println("4- Excluir Agendamento");
            System.out.println("5- Visualizar Todos os Agendamentos");
            System.out.println("6- Sair");

            do {
                resposta = scanner.nextInt();
                if(resposta < 1 || resposta > 6) {
                    System.out.println("Insira uma opção válida: ");
                }

            } while(resposta < 1 || resposta > 6);

            switch (resposta) {
                case 1:
                    criarAgendamento(scanner);
                    break;
                case 2:
                    editarAgendamento(scanner);
                    break;
                case 3:
                    buscarAgendamento(scanner);
                    break;
                case 4:
                    excluirAgendamento(scanner);
                    break;
                case 5:
                    visualizarTodosAgendamentos();
                    break;
                case 6:
                    System.out.println("Saindo do menu de agendamentos.");
                    return;
            }
        } while(resposta != 6);
    }

    private void criarAgendamento(Scanner scanner) throws ParseException {
        System.out.println("Criar Agendamento:");

        FuncionarioEntity funcionario = new FuncionarioEntity();
        ClienteEntity cliente = new ClienteEntity();
        ServicoEntity servico = new ServicoEntity();

        System.out.print("Horário (formato yyyy-MM-dd HH:mm:ss): ");
        String horarioStr = scanner.nextLine();
        java.util.Date horario = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(horarioStr);

        System.out.print("Ativo (true/false): ");
        Boolean ativo = scanner.nextBoolean();

        Agendar novoAgendar = new Agendar();
        novoAgendar.setFuncionario(funcionario);
        novoAgendar.setCliente(cliente);
        novoAgendar.setServico(servico);
        novoAgendar.setHorario(horario);
        novoAgendar.setAtivo(ativo);

        try {
            Agendar agendarCriado = agendarController.criarAgendar(novoAgendar);
            if (agendarCriado != null) {
                System.out.println("Agendamento criado com sucesso!");
            } else {
                System.out.println("Falha ao criar agendamento.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    private void editarAgendamento(Scanner scanner) {
        // Implemente a lógica para editar um agendamento aqui
    }

    private void buscarAgendamento(Scanner scanner) {
        // Implemente a lógica para buscar um único agendamento aqui
    }

    private void excluirAgendamento(Scanner scanner) {
        // Implemente a lógica para excluir um agendamento aqui
    }

    private void visualizarTodosAgendamentos() {
        List<Agendar> agendamentos = agendarController.buscarTodosAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            System.out.println("Lista de Agendamentos:");
            for (Agendar agendar : agendamentos) {
                // Implemente a lógica para visualizar um agendamento aqui
            }
        }
    }
}
