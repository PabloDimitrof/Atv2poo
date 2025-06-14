package poo.aula04.colecao;

import java.util.HashMap;
import java.util.Map;

public class ExemploDicionario {

    public static void main(String[] args) {

        Map<String, String> agenda = new HashMap<>();
        agenda.put("Surjan", "+385 23343");
        agenda.put("FlavioGP", "+55 11 98234.3452");
        agenda.put("Jose", "+75 2345");
        System.out.println(agenda);

        agenda.put("Surjan", "+55 21 455454");
        System.out.println(agenda);

        System.out.println(agenda.get("Jose"));

    }

}
