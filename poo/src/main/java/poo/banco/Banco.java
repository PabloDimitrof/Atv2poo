package poo.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Banco {

    private final String name;
    private final Map<String, Cliente> clientesById;
    private final Map<String, Conta> contasById;

    public Banco(String name) {
        this.name = name;
        this.clientesById = new HashMap<>();
        this.contasById = new HashMap<>();
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientesById.values());
    }

    public Cliente getCliente(String id) {
        return clientesById.get(id);
    }

    public void addCliente(Cliente cliente) {
        clientesById.put(cliente.getId(), cliente);
    }

    public void addConta(Conta conta) {
        contasById.put(conta.getId(), conta);
    }

    public List<Conta> getContas() {
        return new ArrayList<>(contasById.values());
    }

    public String getName() {
        return name;
    }
    
}
