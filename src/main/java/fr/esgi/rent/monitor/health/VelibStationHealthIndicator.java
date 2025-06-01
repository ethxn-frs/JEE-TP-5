package fr.esgi.rent.monitor.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VelibStationHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Health health() {
        try {
            String json = restTemplate.getForObject("http://localhost:8081/actuator/health", String.class);
            if (json != null && json.contains("\"status\":\"UP\"")) {
                return Health.up().withDetail("velib-stations-api", "UP").build();
            } else {
                return Health.down().withDetail("velib-stations-api", "DOWN").build();
            }
        } catch (Exception e) {
            return Health.down(e).withDetail("velib-stations-api", "ERROR").build();
        }
    }
}
