package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import controller.AgendarController;
import model.entities.AgendarEntity;
import model.entities.ClienteEntity;
import model.entities.FuncionarioEntity;
import model.entities.ServicoEntity;

public class AgendarView {

    private AgendarController agendarController;
    private final Scanner scanner = new Scanner(System.in);

    public AgendarView() {
        this.agendarController = new AgendarController();
    }

    public void mostrarMenuAgendar() throws ParseException {

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
                scanner.nextLine();
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
                    break;
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
        scanner.nextLine();
        java.util.Date horario = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(horarioStr);
        Boolean ativo = true;

        AgendarEntity novoAgendarEntity = new AgendarEntity();
        novoAgendarEntity.setFuncionario(funcionario);
        novoAgendarEntity.setCliente(cliente);
        novoAgendarEntity.setServico(servico);
        novoAgendarEntity.setHorario(horario);
        novoAgendarEntity.setAtivo(ativo);

        try {
            AgendarEntity agendarEntityCriado = agendarController.criarAgendar(novoAgendarEntity);
            if (agendarEntityCriado != null) {
                System.out.println("Agendamento criado com sucesso!");
            } else {
                System.out.println("Falha ao criar agendamento.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    private void editarAgendamento(Scanner scanner) throws ParseException {
        System.out.println("Editar Agendamento:");

        System.out.println("ID do Agendamento: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        AgendarEntity agendarEntity = agendarController.buscarAgendar(id);
        if (agendarEntity == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        FuncionarioEntity funcionario = new FuncionarioEntity();
        ClienteEntity cliente = new ClienteEntity();
        ServicoEntity servico = new ServicoEntity();

        System.out.print("Horário (formato yyyy-MM-dd HH:mm:ss): ");
        String horarioStr = scanner.nextLine();
        java.util.Date horario = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(horarioStr);
        Boolean ativo = true;

        agendarEntity.setFuncionario(funcionario);
        agendarEntity.setCliente(cliente);
        agendarEntity.setServico(servico);
        agendarEntity.setHorario(horario);
        agendarEntity.setAtivo(ativo);

        try {
            AgendarEntity agendarEntityEditado = agendarController.updateById(agendarEntity);
            if (agendarEntityEditado != null) {
                System.out.println("Agendamento editado com sucesso!");
            } else {
                System.out.println("Falha ao editar agendamento.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao editar agendamento: " + e.getMessage());
        }
    }

    private void buscarAgendamento(Scanner scanner) {
        System.out.println("Buscar Agendamento:");

        System.out.println("ID do Agendamento: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        AgendarEntity agendar = agendarController.buscarAgendar(id);
        if (agendar == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        System.out.println("Detalhes do Agendamento:");
        System.out.println("ID: " + agendar.getId());
        System.out.println("Funcionario: " + agendar.getFuncionario().getNome());
        System.out.println("Cliente: " + agendar.getCliente().getNome());
        System.out.println("Servico: " + agendar.getServico().getNome());
        System.out.println("Horário: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(agendar.getHorario()));
        System.out.println("Ativo: " + (agendar.getAtivo() ? "Sim" : "Não"));
    }

    private void excluirAgendamento(Scanner scanner) {
        System.out.println("Excluir Agendamento:");

        System.out.println("ID do Agendamento: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        AgendarEntity agendar = agendarController.buscarAgendar(id);
        if (agendar == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        try {
            agendarController.delete(id);
            System.out.println("Agendamento excluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir agendamento: " + e.getMessage());
        }
    }

    private void visualizarTodosAgendamentos() {
        List<AgendarEntity> agendamentos = agendarController.buscarTodosAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            System.out.println("Lista de Agendamentos:");
            for (AgendarEntity agendar : agendamentos) {
                System.out.println("ID: " + agendar.getId());
                System.out.println("Funcionario: " + agendar.getFuncionario().getNome());
                System.out.println("Cliente: " + agendar.getCliente().getNome());
                System.out.println("Servico: " + agendar.getServico().getNome());
                System.out.println("Horário: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(agendar.getHorario()));
                System.out.println("Ativo: " + (agendar.getAtivo() ? "Sim" : "Não"));
                System.out.println("------------------------");
            }
        }
    }
}
