package espm.auth;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import espm.account.AccountController;
import espm.account.AccountIn;
import espm.account.AccountOut;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AccountController accountController;

    @Autowired
    private JwtService jwtService;

    public String login(String email, String password) {

        logger.debug("login: " +
            "email: [" + email + "] " +
            "password: [" + password + "] "
        );

        // verifica se usuario e senha existem
        AccountOut accountOut = accountController.login(
            AccountIn.builder()
                .email(email)
                .password(password)
                .build()
        );
        if (accountOut == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        logger.debug("account id: " + accountOut.id());

        return createToken(accountOut);

    }

    public void register(String name, String email, String password) {

        logger.debug("register: " + name + ":" + password);

        AccountIn accountIn = AccountIn.builder()
            .name(name)
            .email(email)
            .password(password)
            .build();

        logger.debug("in: " + accountIn);

        AccountOut accountOut = accountController.create(accountIn);

        logger.debug("out: " + accountOut);

    }

    public AccountOut whoiam(String idAccount) {
        return accountController.read(idAccount);
    }

    private String createToken(AccountOut account) {
        Date notBefore = new Date();
        Date expiration = new Date(
            notBefore.getTime() + 1000l * 60 * 60 * 24 * 30);
        return jwtService.create(
            account.id(),
            notBefore,
            expiration
        );  
    }

    public Map<String, String> solve(String token) {
        final String id = jwtService.getId(token);
        return Map.of("idAccount", id);
    }
    
}
