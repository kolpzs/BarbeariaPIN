package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import controller.DisponibilidadeController;
import model.entities.DisponibilidadeEntity;

public class DisponibilidadeView {

    private DisponibilidadeController disponibilidadeController;

    public DisponibilidadeView() {
        this.disponibilidadeController = new DisponibilidadeController();
    }

    public void mostrarMenuDisponibilidade() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Disponibilidade:");
            System.out.println("1- Criar Disponibilidade");
            System.out.println("2- Visualizar Todas as Disponibilidades");
            System.out.println("3- Ativar/Desativar Disponibilidade");
            System.out.println("4- Sair");

            int resposta = scanner.nextInt();
            scanner.nextLine();

            switch (resposta) {
                case 1:
                    criarDisponibilidade(scanner);
                    break;
                case 2:
                    visualizarTodasDisponibilidades();
                    break;
                case 3:
                    setAtivo(scanner);
                    break;
                case 4:
                    System.out.println("Saindo do menu de disponibilidades.");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private void criarDisponibilidade(Scanner scanner) {
        System.out.println("Criar Disponibilidade:");
        System.out.print("ID do Funcionário: ");
        Long funcionarioId = scanner.nextLong();
        scanner.nextLine(); 
        System.out.print("Horário de Início (dd/MM/yyyy HH:mm:ss): ");
        String inicioStr = scanner.nextLine();
        LocalDateTime horarioInicio = LocalDateTime.parse(inicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        System.out.print("Horário de Fim (dd/MM/yyyy HH:mm:ss): ");
        String fimStr = scanner.nextLine();
        LocalDateTime horarioFim = LocalDateTime.parse(fimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try {
            DisponibilidadeEntity novaDisponibilidade = disponibilidadeController.criarDisponibilidade(funcionarioId, horarioInicio, horarioFim);
            if (novaDisponibilidade != null) {
                System.out.println("Disponibilidade criada com sucesso!");
            } else {
                System.out.println("Falha ao criar disponibilidade.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar disponibilidade: " + e.getMessage());
        }
    }

    private void visualizarTodasDisponibilidades() {
        List<DisponibilidadeEntity> disponibilidades = disponibilidadeController.buscarTodasDisponibilidades();
        if (disponibilidades.isEmpty()) {
            System.out.println("Nenhuma disponibilidade encontrada.");
        } else {
            System.out.println("Lista de Disponibilidades:");
            for (DisponibilidadeEntity disponibilidade : disponibilidades) {
                System.out.println("ID: " + disponibilidade.getId() + 
                                   ", Funcionário ID: " + disponibilidade.getFuncionarioId() + 
                                   ", Início: " + disponibilidade.getHorarioInicio() + 
                                   ", Fim: " + disponibilidade.getHorarioFim() + 
                                   ", Ativo: " + disponibilidade.isAtivo());
            }
        }
    }

    private void setAtivo(Scanner scanner) {
        System.out.print("ID da Disponibilidade: ");
        Long disponibilidadeId = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Ativar (true) ou Desativar (false): ");
        boolean ativo = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        try {
            DisponibilidadeEntity disponibilidade = disponibilidadeController.setAtivo(disponibilidadeId, ativo);
            if (disponibilidade != null) {
                System.out.println("Disponibilidade atualizada com sucesso!");
            } else {
                System.out.println("Falha ao atualizar disponibilidade.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar disponibilidade: " + e.getMessage());
        }
    }
}
