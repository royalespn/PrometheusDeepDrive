package in.co.iman.prometheusdeepdrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrometheusDeepDriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrometheusDeepDriveApplication.class, args);
    }

}
