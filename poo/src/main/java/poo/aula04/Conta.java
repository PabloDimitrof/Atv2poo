package poo.aula04;

import java.util.UUID;

public class Conta {
    
    public String id = UUID.randomUUID().toString();
    public double saldo = 0;
    public final double LIMITE = 100;

}
