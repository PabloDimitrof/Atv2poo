package espm.account;

import java.text.SimpleDateFormat;

public class AccountParser {

    /*
     * See:
     * https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    public static Account to(AccountIn in) {
        return in == null ? null :
            Account.builder()
                .name(in.name())
                .email(in.email())
                .password(in.password())
                .build();
    }

    public static AccountOut to(Account a) {
        return a == null ? null :
            AccountOut.builder()
                .id(a.id())
                .name(a.name())
                .email(a.email())
                .createdAt(sdf.format(a.createdAt()))                
                .build();
    }

}
