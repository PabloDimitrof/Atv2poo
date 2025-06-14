package poo.aula06;

import java.util.Scanner;

public class ExemploExceptionDeclarado {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("valor: ");
            validaEntrada(scanner.nextLine().trim());
            scanner.close();
        } catch (MeuException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
    }

    public static int validaEntrada(String positivo) throws MeuException {
        try {
            int valor = Integer.parseInt(positivo);
            if (valor <= 0) {
                throw new MeuException("Apenas valores positivos");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new MeuException(positivo + " nao eh numero", e);
        }

    }
    
}
