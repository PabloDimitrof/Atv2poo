package espm.data;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DataRepository extends CrudRepository<DataModel, Long> {

    List<DataModel> findBySensor(String sensor);

    List<DataModel> findBySensorAndIdSensor(String sensor, Short idSensor);

    List<DataModel> findBySensorAndDataBetween(String sensor, LocalDateTime inicio, LocalDateTime fim);
}
