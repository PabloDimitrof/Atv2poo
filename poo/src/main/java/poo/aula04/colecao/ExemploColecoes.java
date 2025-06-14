package poo.aula04.colecao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ExemploColecoes {

    public static void main(String[] args) {
        
        List<String> listNomes = new ArrayList<>();
        listNomes.add("SrMini");
        listNomes.add("Rafael");
        listNomes.add("Surjan");
        listNomes.add("FlavioGP");
        listNomes.add("SrMini");
        print(listNomes);

        Set<String> setNomes = new TreeSet<>();
        setNomes.add("SrMini");
        setNomes.add("Rafael");
        setNomes.add("Surjan");
        setNomes.add("FlavioGP");
        setNomes.add("SrMini");
        print(setNomes);

    }

    public static void print(Collection<?> c) {
        c.forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }
    
}
