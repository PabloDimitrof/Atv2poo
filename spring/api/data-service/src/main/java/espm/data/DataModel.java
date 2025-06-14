package espm.data;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(fluent = true)
public class DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(name = "id_sensor", nullable = false)
    private Short idSensor;

    @Column(nullable = false)
    private Integer delta;

    @Column(nullable = false)
    private Short bateria;

    @Column
    private Double h2s;

    @Column
    private Double umidade;

    @Column
    private Double nh3;

    @Column
    private Double temperatura;

    @Column
    private Short ocupado;

    @Column(nullable = false)
    private String sensor;

    @Column(name = "registro_id", nullable = false)
    private Long registroId;

    public DataModel(Data data) {
        this.id = data.id();
        this.data = data.data();
        this.idSensor = data.idSensor();
        this.delta = data.delta();
        this.bateria = data.bateria();
        this.h2s = data.h2s();
        this.umidade = data.umidade();
        this.nh3 = data.nh3();
        this.temperatura = data.temperatura();
        this.ocupado = data.ocupado();
        this.sensor = data.sensor();
        this.registroId = data.registroId();
    }

    public Data to() {
        return Data.builder()
                .id(id)
                .data(data)
                .idSensor(idSensor)
                .delta(delta)
                .bateria(bateria)
                .h2s(h2s)
                .umidade(umidade)
                .nh3(nh3)
                .temperatura(temperatura)
                .ocupado(ocupado)
                .sensor(sensor)
                .registroId(registroId)
                .build();
    }
}
