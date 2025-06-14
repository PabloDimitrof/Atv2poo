package poo.aula02;

public class OperaConta {

    public static void main(String[] args) {
        
        Conta contaSeuMini = new Conta();
        System.out.println("SeuMini saldo: " + contaSeuMini.getSaldo());

        contaSeuMini.depositar(1000);
        System.out.println("SeuMini saldo: " + contaSeuMini.getSaldo());

        contaSeuMini.sacar(1500);
        System.out.println("SeuMini saldo: " + contaSeuMini.getSaldo());

        Conta contaFlavioGP = new Conta();
        contaFlavioGP.depositar(70);
        System.out.println("Flavio [" + contaFlavioGP.getId() + "] saldo: " + contaFlavioGP.getSaldo());

    }
    
}
