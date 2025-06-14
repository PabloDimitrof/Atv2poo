package espm.account;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity // habilita o objeto para persistencia
@Table(name="account") // nome da tabela para persistencia
@Getter @Setter @Accessors(fluent = true)
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AccountModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "created_at")
    private Date createdAt;

    // ORM: Objeto -> Relacional
    public AccountModel(Account a) {
        this.id = a.id();
        this.name = a.name();
        this.email = a.email();
        this.hashPassword = a.hashPassword();
        this.createdAt = a.createdAt();
    }

    // ORM: Relacional -> Objeto
    public Account to() {
        return Account.builder()
            .id(this.id)
            .name(this.name)
            .email(this.email)
            .hashPassword(this.hashPassword)
            .createdAt(this.createdAt)
            .build();
    }

}
