package in.co.iman.prometheusdeepdrive.controllers;

import in.co.iman.prometheusdeepdrive.models.Student;
import in.co.iman.prometheusdeepdrive.services.StudentService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
@Timed
public class StudentController {

    private RestTemplate restTemplate;

    public Counter hitCounter;

    private StudentService studentService;

    @Autowired
    public StudentController(RestTemplate restTemplate, Counter hitCounter, StudentService studentService) {
        this.restTemplate = restTemplate;
        this.hitCounter = hitCounter;
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student){
        addLatency(10, 100);
        log.info("add student API triggered {}", student.toString());
        studentService.addStudent(student);
    }

    @GetMapping("/student")
    public List<Student> getAllStudents(){
        hitCounter.increment();
        addLatency(10, 100);
        log.info("retrieved all student API triggered  ");
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        addLatency(1, 20);
        log.info("retrieved student id={} API trigger ", id);
        return studentService.getStudentById(id);
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student ){
        log.info("pul student API trigged {}", student.toString());
        studentService.updateStudent(id, student);
    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id){
        log.info("delete id={} student API trigger ", id);
        addLatency(0, 40);
        studentService.deleteStudent(id);
    }

    @RequestMapping("/external/activity")
    public String api1() {
        log.info("external api triggered /external/activity ");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://randomuser.me/api2/", String.class);
        return "Response from outbound request was " + responseEntity.getBody();
    }

    @RequestMapping("/external/ip")
    public String api2() {
        log.info("external api triggered /external/ip ");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.ipify.org?format=json", String.class);
        return "Response from outbound request was " + responseEntity.getBody();
    }

    private String apiResponse(int apiNumber) {
        return String.format("API %s response from %s", apiNumber, "course-tracker");
    }

    private void addLatency(int minimumMs, int maximumMs) {
        long sleepDuration = ThreadLocalRandom.current().nextInt(minimumMs, maximumMs + 1);
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/log")
    public String logMethod() {
        log.info("A INFO Message printed ");
        log.trace("A TRACE Message printed ");
        log.debug("A DEBUG Message printed ");
        log.warn("A WARN Message printed ");
        log.error("A ERROR Message printed ");

        return "logged messaged!";
    }

}
