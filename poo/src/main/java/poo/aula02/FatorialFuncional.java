package poo.aula02;

public class FatorialFuncional {

    public static void main(String[] args) {
        // Linguagem declarativa - funcional

        // ternario
        int n = args.length == 0
                ? 0 
                : Integer.valueOf(args[0]);
        System.out.println(n + "! = " + fatorial(n));
    }

    public static long fatorial(long n) {
        return n <= 1 ? 1 : n * fatorial(n-1);
    }
    
}
