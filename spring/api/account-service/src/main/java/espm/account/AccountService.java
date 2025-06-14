package espm.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
 * Aqui ficam as regras de negocio.
 */
@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account a) {
        a.createdAt(new Date());
        constraints(a);
        List<AccountModel> listByEmail = accountRepository.findByEmail(a.email());
        if (listByEmail.size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email has already been registered");
        }
        return accountRepository.save(new AccountModel(a)).to();
    }

    public Account update(Account a) {
        // constraints
        String id = a.id().trim();
        Account found = accountRepository.findById(id).orElse(null).to();
        if (found == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id);
        }
        constraints(a);
        if (!found.email().equals(a.email())) {
            List<AccountModel> listByEmail = accountRepository.findByEmail(a.email());
            if (listByEmail.size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email has already been registered");
            }    
        }
        // update
        found
            .email(a.email())
            .name(a.name())
            .hashPassword(a.hashPassword());

        return accountRepository.save(new AccountModel(found)).to();
    }

    public List<Account> findAll() {
        return accountRepository.findAll().stream()
            .map(AccountModel::to)
            .toList();
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public Account findById(String id) {
        return accountRepository.findById(id).orElse(null).to();
    }

    public Account login(String email, String password) {
        logger.debug("email: " + email);
        logger.debug("password: " + password);
        final String hash = hash(password);
        logger.debug("hash: " + hash);
        AccountModel found = accountRepository.findByEmailAndHashPassword(email, hash);
        return found == null ? null : found.to();
    }

    /*
     * Calcula o hash
     * https://bytebytego.com/guides/how-to-store-passwords-in-the-database/
     */
    private String hash(String value) {
    try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] hash = digester.digest(value.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void constraints(Account a) {
        if (a.email() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is mandatory");
        }
        if (a.password() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is mandatory");
        }
        String password = a.password().trim();
        if (password.length() < 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "short password: >= 4");
        }
        String hash = hash(password);
        a.hashPassword(hash);
    }
    
}
