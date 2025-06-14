package poo.aula03;

import poo.banco.Cliente;
import poo.banco.Conta;
import poo.banco.ContaPoupanca;
import poo.banco.PessoaFisica;

public class OperaConta {

    public static void main(String[] args) {

        Cliente cliente = new PessoaFisica("teodoro", "123");

        Conta c1 = new ContaPoupanca(cliente);
        System.out.println(c1);

        Conta c2 = new ContaPoupanca(cliente);
        System.out.println(c2);
        
    }
    
}
