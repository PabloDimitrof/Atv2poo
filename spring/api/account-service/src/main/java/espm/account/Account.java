package espm.account;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data @Accessors(fluent = true)
@Builder
public class Account {
    
    private String id;
    private String name;
    private String email;
    private String password;
    private String hashPassword;
    private Date createdAt;

}
