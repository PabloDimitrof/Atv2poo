package espm.auth;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import espm.account.AccountOut;

@RestController
public class AuthResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public TokenOut login(
        @RequestBody CredentialIn credential
    ) {

        String jwt = authService.login(
            credential.email(),
            credential.password()
        );
        return TokenOut.builder()
            .jwt(jwt)
            .build();

    }

    @PostMapping("/auth/register")
    public void register(
        @RequestBody RegisterIn register
    ) {

        authService.register(
            register.name(),
            register.email(),
            register.password()
        );

    }

    @GetMapping("/auth/whoiam")
    public AccountOut whoiam(
        @RequestHeader(value = "id-account", required = true)
        String idAccount) {
        return authService.whoiam(idAccount);
    }

    @PostMapping("/auth/solve")
    public Map<String, String> solve(@RequestBody Map<String, String> map) {
        logger.debug("solve: " + map);
        final String jwt = map.get("jwt");
        logger.debug("jwt: " + jwt);
        return authService.solve(jwt);
    }
    
}
