package poo.banco;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    public PessoaFisica(String id, String name, String cpf) {
        super(id, name);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + cpf + ")";
    }

}
