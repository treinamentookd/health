package br.com.tecnisys.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestController("/")
public class Endpoint {

    private final String URL_LIVENESS = "https://raw.githubusercontent.com/treinamentookd/teste/master/liveness.json";
    private final String URL_READINESS = "https://raw.githubusercontent.com/treinamentookd/teste/master/readiness.json";


    @GetMapping("/readiness")
    public String readiness() throws Exception{
        Logger.getLogger(Endpoint.class.getName()).info("*****************"+ LocalDateTime.now()+" -Readiness invocado... ");
        if (getSaudavel(URL_READINESS)){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }

    private boolean getSaudavel(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url   ,String.class);
        String body = responseEntity.getBody();
        return body.contains("true");
    }


    @GetMapping("/liveness")
    public String liveness() throws Exception{
        Logger.getLogger(Endpoint.class.getName()).info("*****************"+ LocalDateTime.now()+" -Liveness invocado... ");
        if (getSaudavel(URL_LIVENESS)){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }
}
