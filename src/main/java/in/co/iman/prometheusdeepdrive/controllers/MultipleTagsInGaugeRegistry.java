package in.co.iman.prometheusdeepdrive.controllers;

import in.co.iman.prometheusdeepdrive.models.JsonPayload;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class MultipleTagsInGaugeRegistry {

    private static final List<String> connectedStatus = Arrays.asList("TRUE", "FALSE");
    private static final List<String> queueName = Arrays.asList("jobs_ott_queue", "obs_wireline_queue",
            "jobs.retry.queue", "jobs.wireless.queue");
    private Gauge gauge;

    public MultipleTagsInGaugeRegistry(CollectorRegistry collectorRegistry) {
        gauge = Gauge.build()
                .name("ott_queue_statistics")
                .labelNames("queueName", "metric_type" , "connected")
                .help("ott queue stat metric")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/metric/gauge")
    public String metric(@RequestBody List<JsonPayload> jsonPayload) {

        log.info("request body payload" + Arrays.toString(jsonPayload.toArray()));

        for (JsonPayload payload : jsonPayload) {

            gauge.labels(payload.getQueueName(),  "queueDepth" , payload.connected)
                    .set(Double.parseDouble(payload.queueDepth));

            gauge.labels(payload.getQueueName(), "consumerCount", payload.connected)
                    .set(Double.parseDouble(payload.consumerCount));

            gauge.labels(payload.getQueueName(), "listenerIdleTime", payload.connected)
                    .set(Double.parseDouble(payload.listenerIdleTime));
        }
        return "Pushed item to prometheus";
    }

}
