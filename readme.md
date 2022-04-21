--------------------------------
         How to run locally. [docker images might not work on apple M1]
------------------------------------

Just naviage to the docker-compose file and run the below command to run all the containers :-)

1. cd PrometheusDeepDrive/src/main/resources/docker
2. docker-compose up -d
3. docker-compose up ps
4. docker-compose up logs -f


ALL endPoints. Open Browser and hit the below URL
--------------------------------------------------

swagger API : http://localhost:8080/swagger-ui/index.html

OpenAPI: http://localhost:8080/v3/api-docs

Reddis commander: http://localhost:8099/ [guest/guest]

prometheus server: http://localhost:9090

grafana server:    http://localhost:3000 [admin/admin]

alert manager :    http://localhost:9093

pushgateway :      http://localhost:9091

redis metrics:     http://localhost:9121/metrics

rabbit-mq:         http://localhost:15692/metrics

spring-boot-actuator:  http://localhost:8080/actuator/prometheus


Grafana-dashboard Impoer ID:
----------------------------

1. SpringBoot APM Dashboard --> 12900

2. RabbitMQ-Overview        --> 10991

3. Redis dashboard:         --> 11835 





If you want to run Gatling scripts run the gradle gatling command. The scripts will flood the controller with random request
-----------
Run Gratling : gradlew gatlingRun


Metrics endpoints:
------------------
redis metrics:     http://localhost:9121/metrics

rabbit-mq:         http://localhost:15692/metrics

springBoot metrics http://localhost:8080/actuator/prometheus


Grafana pre-build Dashboard:
-------
redis: 763

SpringBoot APM Dashboard: 12900

rabbitMQ: 10991


Pagerduty:
---------
https://vznab.pagerduty.com/incidents


Spring metric doc:
----
https://docs.spring.io/spring-boot/docs/2.6.4/reference/html/actuator.html#actuator.jmx
