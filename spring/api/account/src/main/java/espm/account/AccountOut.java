package espm.account;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Builder @Accessors(fluent = true)
public record AccountOut (
    String id,
    String name,
    String email,
    String password,
    String createdAt
) {
    
}
