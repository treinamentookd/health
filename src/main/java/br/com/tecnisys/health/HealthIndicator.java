package br.com.tecnisys.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthIndicator  implements org.springframework.boot.actuate.health.HealthIndicator {

    @Value("${aplicacao.saudavel}")
    private Boolean saudavel;

    @Override
    public Health health() {
        Health.Builder health = Boolean.TRUE.equals(saudavel) ? Health.up() : Health.down();



        return health.withDetail("Saudável pela variavel",saudavel).build();
    }
}
