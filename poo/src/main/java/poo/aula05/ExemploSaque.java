package poo.aula05;

import poo.banco.Cliente;
import poo.banco.Conta;
import poo.banco.ContaCorrente;
import poo.banco.PessoaFisica;

public class ExemploSaque {

    public static void main(String[] args) {
        Cliente cliente = new PessoaFisica("teodoro", "123");
        Conta c1 = new ContaCorrente(cliente, 1000);
        c1.depositar(90);
        c1.sacar(70);
        c1.sacar(200);
//        c1.depositar(1000);
        System.out.println(c1.getSaldo());
    }

    
}
