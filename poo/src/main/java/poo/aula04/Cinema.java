package poo.aula04;

/* 
 * Exemplo de um Singleton
 */
public class Cinema {

    public static final String NOME = "ESPM Cine";
    private static Cinema instance = new Cinema();

    // construtor foi escondido
    private Cinema() {

    }

    public static Cinema getInstance() {
        return instance;
    }
    
}
