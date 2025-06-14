package poo.banco;

public class BancoException extends RuntimeException {

    private final String code;

    public BancoException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s", code, getMessage());
    }
    
}
