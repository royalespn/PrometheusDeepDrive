swagger API : http://localhost:8080/swagger-ui

OpenAPI: http://localhost:8080/v3/api-docs

Run Gratling : gradlew gatlingRun

Reddis commander: http://localhost:8099/ [guest/guest]

prometheus server: http://localhost:9090

grafana server:    http://localhost:3000 [admin/admin]

alert manager :    http://localhost:9093

pushgateway :      http://localhost:9091

redis metrics:     http://localhost:9121/metrics

rabbit-mq:         http://localhost:15692/metrics




docker-compose -f docker-compose up -d    [/logs / ps]

Grafana-dashboard Impoer ID:
----------------------------

1. SpringBoot APM Dashboard --> 12900

2. RabbitMQ-Overview        --> 10991

3. Redis dashboard:         --> 11835 


How to build docker file>>
./gradlew jibDockerBuild

How to run docker:
------------------

docker run --rm -p 8080:8080 royalespn/prometheusdeepdrive:latest

docker tag royalespn/prometheusdeepdrive:005 royalespn/springboot-prometheus:latest

docker push royalespn/prometheusdeepdrive:001:latest


--------------------------
         Demo:
--------------------------

Docker-compose run 
-------------------
1. cd /Users/irah0001/Documents/iman-poc/iman-code-push-to-repo/PrometheusDeepDrive/src/main/resources/docker
2. docker-compose up -d
3. docker-compose up ps
4. docker-compose up logs -f

Run PrometheusDeepDrive Application
----------------------------------
5. docker run --rm -p 8080:8080 \
-e SPRING_DATASOURCE_URL='jdbc:mariadb://host.docker.internal:3306/prometheusdeepdrive' \
-e SPRING_REDIS_HOST='host.docker.internal' \
-e SPRING_RABBITMQ_HOST='host.docker.internal' \
royalespn/prometheusdeepdrive:latest

Run SpringScheduler
----------------------
1. docker run --rm \
-e MANAGEMENT_METRICS_EXPORT_PROMETHEUS_PUSHGATEWAY_BASE-URL='host.docker.internal:9091' \
royalespn/springboottaskscheduler:latest

Run Gatling
-----------
Run Gratling : gradlew gatlingRun

Open Browser Window
-------------------
swagger API : http://localhost:8080/swagger-ui/index.html

prometheus:        http://localhost:9090
grafana server:    http://localhost:3000 [admin/admin]
alert manager :    http://localhost:9093
pushgateway :      http://localhost:9091


Metrics endpoints:
------------------
redis metrics:     http://localhost:9121/metrics
rabbit-mq:         http://localhost:15692/metrics
springBoot metrics http://localhost:8080/actuator/prometheus


Grafana  Dashboard:
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