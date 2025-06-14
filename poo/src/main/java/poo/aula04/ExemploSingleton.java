package poo.aula04;

public class ExemploSingleton {

    public static void main(String[] args) {

        Cinema cinema1 = Cinema.getInstance();
        Cinema cinema2 = Cinema.getInstance();
        // cinema1 e cinema2 sao o mesmo objeto

        System.out.println(Cinema.NOME);
        System.out.println(cinema1.NOME);
    }

}
