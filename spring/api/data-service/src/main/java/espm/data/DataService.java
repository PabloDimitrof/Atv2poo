package espm.data;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService {

    private final Logger logger = LoggerFactory.getLogger(DataService.class);

    private final String URL = "https://iagen.espm.br/sensores/dados?sensor=%s&data_inicial=%s&data_final=%s";

    public void acquire(String sensor) {

        String inicio = "2025-06-02";
        String termino = "2025-06-03";

        String uri = String.format(URL, sensor, inicio, termino);

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
        ParameterizedTypeReference<List<Map<String, ?>>> responseType =
            new ParameterizedTypeReference<>() {};

                    // aqui, a requisicao esta sendo feita
        RestTemplate restTemplate = new RestTemplate();

        List<Map<String, ?>> data = restTemplate.exchange(request, responseType).getBody();

        logger.debug(data.toString());

        // TODO: Adicionar no DB
    }
    
}
