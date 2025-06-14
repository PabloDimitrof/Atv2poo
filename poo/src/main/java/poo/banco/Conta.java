package poo.banco;

import java.util.UUID;

public abstract class Conta {

    private String id = UUID.randomUUID().toString();
    
    // a classe e os filhos podem enxergar esse atributo.
    protected double saldo = 0;
    protected final Cliente cliente;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new BancoException("D10", "apenas depositos positivos");
        }
        this.saldo += valor;
    }

    // a classe especialista (filha) eh obrigada
    // a implementar esse metodo.
    public abstract void sacar(double valor);

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /*
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html
     */
    @Override
    public String toString() {
        return "[" + this.id + "]: " + this.saldo;
    }

}
