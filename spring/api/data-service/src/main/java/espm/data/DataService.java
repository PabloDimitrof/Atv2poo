package espm.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRepository dataRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "https://iagen.espm.br/sensores/dados?sensor=%s&id_inferior=%d&data_inicial=2025-06-02&data_final=2025-06-03";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void acquire(String sensor) {
        Long maxRegistroId = dataRepository.findBySensor(sensor).stream()
                .map(DataModel::registroId)
                .max(Long::compareTo)
                .orElse(0L);

        String uri = String.format(BASE_URL, sensor, maxRegistroId);
        log.info("Adquirindo dados do sensor '{}', a partir do registro_id={}", sensor, maxRegistroId);

        List<Map<String, ?>> novosDados = requestFromApi(uri);
        log.info("Foram recebidos {} novos registros da API", novosDados.size());

        saveNewData(novosDados, sensor);
    }

    public List<Map<String, ?>> export(String sensor) {
        String uri = String.format(BASE_URL, sensor, 0L);
        log.info("Exportando todos os dados do sensor '{}' diretamente da API", sensor);
        return requestFromApi(uri);
    }

    private List<Map<String, ?>> requestFromApi(String uri) {
        try {
            List<Map<String, ?>> response = restTemplate.getForObject(uri, List.class);
            if (response == null) {
                log.warn("Resposta nula da API para URI: {}", uri);
                return Collections.emptyList();
            }
            return response;
        } catch (Exception e) {
            log.warn("Erro ao requisitar dados da API: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private void saveNewData(List<Map<String, ?>> data, String sensor) {
        if (data.isEmpty()) {
            log.info("Nenhum dado novo para salvar.");
            return;
        }

        Set<Long> registrosExistentes = dataRepository.findBySensor(sensor).stream()
                .map(DataModel::registroId)
                .collect(Collectors.toSet());

        List<DataModel> novos = data.stream()
                .filter(reg -> reg.get("registro_id") != null)
                .map(reg -> parseRecord(reg, sensor))
                .filter(Objects::nonNull)
                .filter(model -> !registrosExistentes.contains(model.registroId()))
                .collect(Collectors.toList());

        if (!novos.isEmpty()) {
            dataRepository.saveAll(novos);
            log.info("{} novos registros salvos no banco para o sensor '{}'", novos.size(), sensor);
        } else {
            log.info("Nenhum novo registro para salvar após verificação de duplicidade.");
        }
    }

    private DataModel parseRecord(Map<String, ?> reg, String sensor) {
        try {
            return DataModel.builder()
                    .data(LocalDateTime.parse((String) reg.get("data"), FORMATTER))
                    .idSensor(toShort(reg.get("id_sensor")))
                    .delta(toInt(reg.get("delta")))
                    .bateria(toShort(reg.get("bateria")))
                    .h2s(toDouble(reg.get("h2s")))
                    .umidade(toDouble(reg.get("umidade")))
                    .nh3(toDouble(reg.get("nh3")))
                    .temperatura(toDouble(reg.get("temperatura")))
                    .ocupado(toShort(reg.get("ocupado")))
                    .sensor(sensor)
                    .registroId(toLong(reg.get("registro_id")))
                    .build();
        } catch (Exception e) {
            log.warn("Erro ao converter registro: {}. Erro: {}", reg, e.getMessage());
            return null;
        }
    }


    private Short toShort(Object o) {
        return o == null ? null : ((Number) o).shortValue();
    }

    private Integer toInt(Object o) {
        return o == null ? null : ((Number) o).intValue();
    }

    private Long toLong(Object o) {
        return o == null ? null : ((Number) o).longValue();
    }

    private Double toDouble(Object o) {
        return o == null ? null : ((Number) o).doubleValue();
    }
}
