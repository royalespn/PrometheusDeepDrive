package performancetest;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;

public class StudentRequestSimulation extends Simulation {


    HttpProtocolBuilder httpProtocol = HttpDsl.http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .userAgentHeader("Gatling/Performance Test");


    Iterator<Map<String, Object>> feeder =
            Stream.generate((Supplier<Map<String, Object>>) ()
                    -> Collections.singletonMap("name", UUID.randomUUID().toString())
            ).iterator();


    // Scenario
    ScenarioBuilder scn = CoreDsl.scenario("Load Test Creating Customers")
            .feed(feeder)
            .exec(http("create-student-request")
                    .post("/api/v1/student")
                    .header("Content-Type", "application/json")
                    .body(StringBody("{\n" +
                            "    \"name\" : \"${name}\",\n" +
                            "    \"email\" : \"cloud@sku.com\"\n" +
                            "}"))
                    .check(status().is(200))
            )
            .exec(http("get-student-request")
                    .get("/api/v1/student")
                    .header("Content-Type", "application/json")
                    .check(status().is(200))
            )
            .exec(http("get-external-activity-API")
                    .get("/api/v1/external/activity")
                    .header("Content-Type", "application/json")
                    .check(status().is(200))
            )
            .exec(http("get-external-ip-API")
                    .get("//api/v1/external/ip")
                    .header("Content-Type", "application/json")
                    .check(status().is(200))
            )
            .exec(http("get-log")
                    .get("/api/v1/log")
                    .header("Content-Type", "application/json")
                    .check(status().is(200))
            )
            .exec(http("publish-messages-to-rabbit")
                    .post("/publishToRabbit")
                    .header("Content-Type", "application/json")
                    .body(StringBody("{\n" +
                            "    \"organizationId\" : \"00011\",\n" +
                            "    \"accountId\" : \"000222\"\n" +
                            "}"))
                    .check(status().is(200))
            );

    // Simulation
    public StudentRequestSimulation() {
        this.setUp(scn.injectOpen(constantUsersPerSec(2).during(Duration.ofSeconds(2))))
                .protocols(httpProtocol);
    }

}
// The Gradle :  /gradlew gatlingRun