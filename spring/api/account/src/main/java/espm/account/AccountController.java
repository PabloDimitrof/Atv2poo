package espm.account;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account", url = "http://account:8080")
public interface AccountController {

    @GetMapping("/account")
    public List<AccountOut> read();

    @GetMapping("/account/{id}")
    public AccountOut read(@PathVariable String id);

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable String id);

    @PostMapping("/account")
    public AccountOut create(@RequestBody AccountIn in);

    @PutMapping("/account/{id}")
    public AccountOut update(
        @PathVariable String id,
        @RequestBody AccountIn in
    );

    @PostMapping("/account/login")
    public AccountOut login(@RequestBody AccountIn in);

    @GetMapping("/price/{from}/{to}")
    public Map<String, String> price(
        @PathVariable String from,
        @PathVariable String to
    );

}
