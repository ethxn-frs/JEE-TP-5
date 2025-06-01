package fr.esgi.rent.health;

import fr.esgi.rent.monitor.health.VelibStationHealthIndicator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;

import static org.assertj.core.api.Assertions.assertThat;

public class VelibStationHealthIndicatorTest {

    @Test
    void healthShouldAlwaysReturnHealthObject() {
        VelibStationHealthIndicator indicator = new VelibStationHealthIndicator();
        Health health = indicator.health();

        assertThat(health).isNotNull();
        assertThat(health.getStatus().getCode()).isIn("UP", "DOWN", "ERROR");
    }
}
