package in.co.iman.prometheusdeepdrive.controllers;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping(path = "/api/v1")
public class HistogramController {
    private final Histogram requestDuration;

    public HistogramController(CollectorRegistry collectorRegistry) {
        requestDuration = Histogram.build()
                .name("request_duration")
                .help("Time for HTTP request.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/wait")
    public String makeMeWait() throws InterruptedException {
        Histogram.Timer timer = requestDuration.startTimer();
        return "Sleep"+Math.floor(Math.random() * 10 * 1000);
    }
}

///api/v1/wait
///api/v1/counter
///api/v1/push
///api/v1/pop