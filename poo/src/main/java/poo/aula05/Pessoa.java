package poo.aula05;

public class Pessoa {

    private String nome;
    private String sobrenome;

    public void setNome(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.sobrenome;
    }
    
}
