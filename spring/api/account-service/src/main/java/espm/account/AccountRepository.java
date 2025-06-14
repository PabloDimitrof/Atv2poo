package espm.account;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Metodos JPQL para acesso ao banco de dados
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
@Repository
public interface AccountRepository extends CrudRepository<AccountModel, String> {

    public List<AccountModel> findAll();
    public List<AccountModel> findByEmail(String email);
    public AccountModel findByEmailAndHashPassword(String email, String hash);
    
}
