package in.co.iman.prometheusdeepdrive.controllers;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class GaugeController {
    private final Gauge queueSize;

    public GaugeController(CollectorRegistry collectorRegistry) {
        queueSize = Gauge.build()
                .name("queue_size")
                .help("Size of queue.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/push")
    public String push() {
        queueSize.inc();
        return "Pushed an item!";
    }

    @GetMapping(value = "/pop")
    public String pop() {
        queueSize.dec();
        return "Popped an item!";
    }
}