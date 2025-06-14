package poo.aula06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import poo.banco.Cliente;
import poo.banco.PessoaFisica;

public class ExemploSet {

    public static void main(String[] args) {
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "314.123.123-12");
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "314.123.123-12");
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "314.123.123-12");
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "333.444.555-66");
        
        Set<Cliente> clientes = new HashSet<>();
        clientes.add(pf1);
        clientes.add(pf2);
        clientes.add(pf3);
        clientes.add(pf4);
        System.out.println(Arrays.toString(clientes.toArray()));

    }

}
