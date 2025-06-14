package espm.account;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountResource implements AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PriceService priceService;

    @Override
    public List<AccountOut> read() {
        return accountService.findAll().stream()
            .map(AccountParser::to)
            .toList();
    }

    @Override
    public AccountOut read(@PathVariable String id) {
        Account found = accountService.findById(id);
        if (found == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id);
        }
        return AccountParser.to(found);
    }

    @Override
    public void delete(@PathVariable String id) {
        accountService.deleteById(id);
    }

    @Override
    public AccountOut create(@RequestBody AccountIn in) {
        Account a = AccountParser.to(in);
        Account saved = accountService.create(a);
        return AccountParser.to(saved);
    }

    @Override
    public AccountOut update(
        @PathVariable String id,
        @RequestBody AccountIn in
    ) {
        Account a = AccountParser.to(in).id(id);
        Account saved = accountService.update(a);
        return AccountParser.to(saved);
    }

    @Override
    public AccountOut login(AccountIn in) {
        logger.debug("login: " + in.email() + ":" + in.password());
        return AccountParser.to(
            accountService.login(
                in.email(),
                in.password()
            )
        );
    }

    @Override
    public Map<String, String> price(
        @PathVariable String from,
        @PathVariable String to
    ) {
        Map<String, String> out = priceService.price(from, to);
        return out;
    }

}
