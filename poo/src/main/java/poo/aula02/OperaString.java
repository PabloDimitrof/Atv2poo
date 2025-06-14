package poo.aula02;

public class OperaString {
    
    public static void main(String[] args) {

        String s1 = "ESPM";
        String s2 = "ES";
        String s3 = s2 + "PM";

        System.out.println("s1 == s3? " + " " + s1 + "==" + s3 + "? " + (s1 == s3));        // True
        System.out.println("s1 == ESPM? " + (s1 == "ESPM"));  // True
        System.out.println("s3 == ESPM? " + (s3 == "ESPM"));  // False
        System.out.println("s1.equals(s3)? " + s1.equals(s3));        // True
        
    }

}
