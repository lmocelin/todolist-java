package repository;

import model.Tarefa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    private static final String ARQUIVO = "data/tarefas.txt";

    public List<Tarefa> carregarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();

        File file = new File(ARQUIVO);
        if (!file.exists()) return tarefas;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", 3);
                int id = Integer.parseInt(partes[0]);
                String descricao = partes[1];
                boolean concluida = Boolean.parseBoolean(partes[2]);

                Tarefa tarefa = new Tarefa(id, descricao);
                if (concluida) tarefa.marcarComoConcluida();
                tarefas.add(tarefa);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }

        return tarefas;
    }

    public void salvarTarefas(List<Tarefa> tarefas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Tarefa t : tarefas) {
                writer.write(t.getId() + ";" + t.getDescricao() + ";" + t.isConcluida());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }
}