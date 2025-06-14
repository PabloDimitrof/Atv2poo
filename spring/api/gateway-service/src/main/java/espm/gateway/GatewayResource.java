package espm.gateway;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayResource {

    @GetMapping("/health-check")
    public Map<String, String> health_check() {
        return Map.of(
            "api", "store"
        );
    }
    
}
