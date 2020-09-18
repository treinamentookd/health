package br.com.tecnisys.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController("/")
public class Endpoint {

    private final String URL = "https://raw.githubusercontent.com/treinamentookd/teste/master/index.json";


    @GetMapping("/readiness")
    public String readiness() throws Exception{
        System.out.println("*****************"+ LocalDateTime.now()+" -Readiness invocado... ");
        if (getSaudavel()){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }

    private boolean getSaudavel() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL   ,String.class);
        String body = responseEntity.getBody();
        return body.contains("true");
    }


    @GetMapping("/liveness")
    public String liveness() throws Exception{
        System.out.println("*****************"+ LocalDateTime.now()+" -Liveness invocado... ");
        if (getSaudavel()){
            return "ok";
        }else{
            throw new Exception("erro");
        }

    }
}
