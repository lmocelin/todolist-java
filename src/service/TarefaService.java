package service;

import model.Tarefa;
import repository.TarefaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaService {
    private final TarefaRepository repositorio;
    private final List<Tarefa> tarefas;
    private int proximoId = 1;

    public TarefaService() {
        this.repositorio = new TarefaRepository();
        this.tarefas = new ArrayList<>(repositorio.carregarTarefas());
        if (!tarefas.isEmpty()) {
            proximoId = tarefas.get(tarefas.size() - 1).getId() + 1;
        }
    }

    public void adicionarTarefa(String descricao) {
        Tarefa nova = new Tarefa(proximoId++, descricao);
        tarefas.add(nova);
        salvar();
    }

    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(tarefas);
    }

    public boolean concluirTarefa(int id) {
        Optional<Tarefa> tarefa = buscarPorId(id);
        if (tarefa.isPresent() && !tarefa.get().isConcluida()) {
            tarefa.get().marcarComoConcluida();
            salvar();
            return true;
        }
        return false;
    }

    public boolean removerTarefa(int id) {
        Optional<Tarefa> tarefa = buscarPorId(id);
        if (tarefa.isPresent()) {
            tarefas.remove(tarefa.get());
            salvar();
            return true;
        }
        return false;
    }

    private Optional<Tarefa> buscarPorId(int id) {
        return tarefas.stream()
                      .filter(t -> t.getId() == id)
                      .findFirst();
    }

    private void salvar() {
        repositorio.salvarTarefas(tarefas);
    }
}