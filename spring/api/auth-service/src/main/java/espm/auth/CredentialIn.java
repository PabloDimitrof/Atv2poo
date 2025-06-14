package espm.auth;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Builder @Data @Accessors(fluent = true)
public class CredentialIn {
    private String email;
    private String password;
}
