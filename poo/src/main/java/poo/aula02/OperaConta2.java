package poo.aula02;

public class OperaConta2 {
    public static void main(String[] args) {
        Conta c1 = new Conta();
        c1.depositar(100);

        Conta c2 = new Conta();
        c2.depositar(200);

        c1 = c2;
        // c1 para a apontar para o mesmo endereco de memoria de c2,
        // pois eh copiado o valor inteiro aponta para a posicao de memoria
        c2.depositar(1);
        System.out.println("c1: " + c1.getSaldo()); // 201
        System.out.println("c2: " + c2.getSaldo()); // 201

        // invoca o garbage collector
        System.gc();
    }
}
