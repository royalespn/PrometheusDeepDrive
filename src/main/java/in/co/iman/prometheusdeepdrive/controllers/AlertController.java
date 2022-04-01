package in.co.iman.prometheusdeepdrive.controllers;

import in.co.iman.prometheusdeepdrive.models.Student;
import in.co.iman.prometheusdeepdrive.services.StudentService;
import io.micrometer.core.instrument.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/api/v1/alert/")
@Slf4j
public class AlertController {


    @PostMapping("slack")
    public void slackHook(@RequestBody Map alert){
        log.info("received alert slack channel{}", alert);

    }

    @PostMapping("pagerduty")
    public void pagerdutyHook(@RequestBody Map alert){
        log.info("received alert pagerduty channel{}", alert);

    }

}
