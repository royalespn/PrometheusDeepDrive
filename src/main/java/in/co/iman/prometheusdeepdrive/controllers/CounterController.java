package in.co.iman.prometheusdeepdrive.controllers;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class CounterController {

    private final Counter requestCount;

    public CounterController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count")
                .help("Number of hello requests.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/counter")
    public String hello() {
        requestCount.inc();
        return "counter!";
    }
}