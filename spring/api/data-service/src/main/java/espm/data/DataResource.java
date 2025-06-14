package espm.data; 

import java.util.List; 

import java.util.Map; 

 

import org.slf4j.Logger; 

import org.slf4j.LoggerFactory; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.web.bind.annotation.RestController; 

 

@RestController 

public class DataResource implements DataController { 

 

    private static final Logger logger = LoggerFactory.getLogger(DataResource.class); 

 

    @Autowired 

    private DataService dataService; 

 

    public void acquire(@PathVariable String idSensor) { 

        dataService.acquire(idSensor); 

    } 

 

    @Override 

    public List<Map<String, ?>> export(@PathVariable String id) { 

        return dataService.export(id); 

 

    } 

} 

 