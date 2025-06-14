package espm.account;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PriceService {

    public Map<String, String> price(String from, String to) {

        // montando a URI para acessar a API de terceiro
        // https://github.com/awesomeapibrasil/economy-api
        from = from.toUpperCase();
        to = to.toUpperCase();
        String uri = "https://economia.awesomeapi.com.br/json/last/" + from + "-" + to;

        // Definindo o padrão de envio e recebimento,
        // definindo os headers da requisicao
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        headers.setAccept(List.of( MediaType.APPLICATION_JSON ));

        // Definindo o request:
        // metodo: get
        // headers: tipos aceitaveis
        RequestEntity<Void> request = RequestEntity
            .get(uri)
            .accept(MediaType.APPLICATION_JSON)
            .headers(headers)
            .build();

        // Definindo a resposta, para o parser.
        // Aqui, estou esperando um
        // Map com chave string e valor sendo outro map (String, String)
        ParameterizedTypeReference<Map<String, Map<String, String>>> responseType =
            new ParameterizedTypeReference<>() {};
            
        // aqui, a requisicao esta sendo feita
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(request, responseType).getBody().get(from + to);
    }
    
}
