package poo.aula06;

import java.util.HashMap;
import java.util.Map;

import poo.banco.Cliente;
import poo.banco.PessoaFisica;

public class ExemploMap {

    public static void main(String[] args) {
        PessoaFisica pf1 = new PessoaFisica("01", "Marcelo", "314.123.123-12");
        PessoaFisica pf2 = new PessoaFisica("01", "Marcelo", "314.123.123-12");
        PessoaFisica pf3 = new PessoaFisica("02", "Marcelo", "314.123.123-12");
        PessoaFisica pf4 = new PessoaFisica("02", "Teodoro", "333.444.555-66");
        
        Map<String, Cliente> clientesById = new HashMap<>();
        clientesById.put(pf1.getId(), pf1);
        clientesById.put(pf2.getId(), pf1);
        clientesById.put(pf3.getId(), pf3);
        clientesById.put(pf4.getId(), pf4);
        System.out.println(clientesById);

        Map<String, Cliente> clientesByCpf = new HashMap<>();
        clientesByCpf.put(pf1.getCpf(), pf1);
        clientesByCpf.put(pf2.getCpf(), pf1);
        clientesByCpf.put(pf3.getCpf(), pf3);
        clientesByCpf.put(pf4.getCpf(), pf4);
        System.out.println(clientesByCpf);

        System.out.println(clientesByCpf.get("333.444.555-66"));

    }

}
