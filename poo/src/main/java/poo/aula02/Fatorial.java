package poo.aula02;

public class Fatorial {

    public static void main(String[] args) {
        // Linguagem imperativa

        // ternario
        int n = args.length == 0
                ? 0 
                : Integer.valueOf(args[0]);
        long f = 1;
        for (int c = n; c > 1; c--) {
            f *= c;
        }
        System.out.println(n + "! = " + f);
    }
    
}
