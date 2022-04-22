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

    private static final String[] LABEL_NAME = new String[]{"queueName", "metric_type", "connected"};
    private static final String GAUGE_NAME = "ott_queue_statistics";
    private static final String HELP_MESSAGE = "ott queue stat metric";

    private Gauge gauge;

    public MultipleTagsInGaugeRegistry(CollectorRegistry collectorRegistry) {
        gauge = Gauge.build()
                .name(GAUGE_NAME)
                .labelNames(LABEL_NAME)
                .help(HELP_MESSAGE)
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
