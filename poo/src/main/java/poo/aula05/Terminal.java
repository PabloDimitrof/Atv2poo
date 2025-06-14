package poo.aula05;

import java.util.Scanner;

import poo.banco.Cliente;
import poo.banco.Conta;
import poo.banco.ContaCorrente;
import poo.banco.PessoaFisica;

public class Terminal {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new PessoaFisica("teodoro", "123");
        Conta conta = new ContaCorrente(cliente, 5000);

        while (true) {

            System.out.print(
                conta.getSaldo() +                
                " > "
            );
            String line = scanner.nextLine().trim();
            if (line.equals("exit")) {
                break;
            }
            line = line.replaceAll("[^0-9-.]", "");
            if (line.length() == 0) {
                continue;
            }
            double value = Double.parseDouble(line);
            // tente esse trecho
            try {
                if (value < 0) {
                    conta.sacar(-value);
                }
                if (value > 0) {
                    conta.depositar(value);
                }
            // se houver excecao, capture o erro.
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        System.out.println("bis bald!");
        scanner.close();
    }

}
