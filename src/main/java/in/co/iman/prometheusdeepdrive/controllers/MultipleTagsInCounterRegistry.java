package in.co.iman.prometheusdeepdrive.controllers;

import in.co.iman.prometheusdeepdrive.models.JsonPayload;
import io.micrometer.core.instrument.MeterRegistry;
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
public class MultipleTagsInCounterRegistry {

    private static final List<String> connectedStatus = Arrays.asList("TRUE", "FALSE");
    private static final List<String> queueName = Arrays.asList("jobs.ott.queue", "obs.wireline.queue",
            "jobs.retry.queue", "jobs.wireless.queue");

    private final MeterRegistry ottMeterRegistry;

    public MultipleTagsInCounterRegistry(MeterRegistry ottMeterRegistry) {
        this.ottMeterRegistry = ottMeterRegistry;
    }

    @GetMapping(value = "/metric/counter")
    public String metric(@RequestBody List<JsonPayload> jsonPayload) {

        log.info("request body payload" + Arrays.toString(jsonPayload.toArray()));

        for (JsonPayload payload : jsonPayload) {

            ottMeterRegistry.counter("ott.queue.consumer",
                            "queueName", payload.queueName,
                            "connectedStatus", payload.connected,
                            "queueDepth", payload.queueDepth,
                            "consumerCount", payload.consumerCount,
                            "listenerIdleTime", payload.listenerIdleTime)
                    .increment();
        }
        return "Pushed item to prometheus";
    }

}
