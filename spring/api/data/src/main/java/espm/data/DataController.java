package espm.data;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "data", url = "http://data:8080")
public interface DataController {

    @GetMapping("/data/import/{idSensor}")
    public void acquire(@PathVariable String idSensor);

    @GetMapping("/data/export/{id}")
    public List<Map<String, ?>> export(@PathVariable String id);

}
