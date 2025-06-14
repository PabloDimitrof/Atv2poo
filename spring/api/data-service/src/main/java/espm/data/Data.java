package espm.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Accessors(fluent = true)
public class Data {

    private Long id;
    private LocalDateTime data;
    private Short idSensor;
    private Integer delta;
    private Short bateria;
    private Double h2s;
    private Double umidade;
    private Double nh3;
    private Double temperatura;
    private Short ocupado;
    private String sensor;
    private Long registroId;
}
