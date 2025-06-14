package poo.banco;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(String name, String cnpj) {
        super(name);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String id, String name, String cnpj) {
        super(id, name);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
    
}
