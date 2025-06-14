package poo.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Cliente {

    private final String id;
    private final String name;
    private final Map<String, Conta> contas;

    public Cliente(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Cliente(String id, String name) {
        this.id = id;
        this.name = name;
        this.contas = new HashMap<>();
    }

    public final String getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public List<Conta> getContas() {
        return new ArrayList<>(contas.values());
    }

    public Conta getConta(String id) {
        return contas.get(id);
    }

    public void addConta(Conta conta) {
        contas.put(conta.getId(), conta);
    }

    @Override
    public String toString() {
        return "[" + id + "]: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        return 
            (obj != null) &&
            (obj instanceof Cliente) &&
            ((Cliente) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
}
