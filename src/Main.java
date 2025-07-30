import service.TarefaService;
import model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TarefaService service = new TarefaService();

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> adicionarTarefa();
                case 2 -> listarTarefas();
                case 3 -> concluirTarefa();
                case 4 -> removerTarefa();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== Gerenciador de Tarefas =====");
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Concluir tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void adicionarTarefa() {
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        service.adicionarTarefa(descricao);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void listarTarefas() {
        List<Tarefa> tarefas = service.listarTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("\nTarefas:");
            tarefas.forEach(System.out::println);
        }
    }

    private static void concluirTarefa() {
        System.out.print("Digite o ID da tarefa a concluir: ");
        int id = lerOpcao();
        if (service.concluirTarefa(id)) {
            System.out.println("Tarefa marcada como concluída.");
        } else {
            System.out.println("Tarefa não encontrada ou já concluída.");
        }
    }

    private static void removerTarefa() {
        System.out.print("Digite o ID da tarefa a remover: ");
        int id = lerOpcao();
        if (service.removerTarefa(id)) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
}