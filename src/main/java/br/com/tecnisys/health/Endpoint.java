package br.com.tecnisys.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.util.logging.Logger;
import java.util.stream.Stream;

@RestController("/")
public class Endpoint {

    private final String URL_LIVENESS = "http://douglasramiro.com.br/liveness.json";
    private final String URL_READINESS = "http://douglasramiro.com.br/readiness.json";


    @GetMapping("/readiness")
    public String readiness() throws Exception{
        boolean valor = getSaudavel(URL_READINESS).getSaudavel();
        Logger.getLogger(Endpoint.class.getName()).info("*****************"+ LocalDateTime.now()+" -Readiness invocado... " + valor);
        if (valor){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }

    private Retorno getSaudavel(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "?date="+LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(), String.class);
        String body = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(body,Retorno.class);
    }


    @GetMapping("/liveness")
    public String liveness() throws Exception{
        boolean valor = getSaudavel(URL_LIVENESS).getSaudavel();
        Logger.getLogger(Endpoint.class.getName()).info("*****************"+ LocalDateTime.now()+" -Liveness invocado... "+valor);
        if (valor){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }
}
