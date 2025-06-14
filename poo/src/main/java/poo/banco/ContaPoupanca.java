package poo.banco;

public class ContaPoupanca extends Conta implements Rendimento {

    private double taxa = 0.005;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor < 0) {
            throw new RuntimeException("Saque negativo");
        } else if (valor <= saldo) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    @Override
    public void render() {
        saldo *= 1 + taxa;
    }

    @Override
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
}
