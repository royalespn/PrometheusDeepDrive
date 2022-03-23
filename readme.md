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

docker run --rm -p 8080:8080 royalespn/prometheusdeepdrive:001

docker tag royalespn/prometheusdeepdrive:001 royalespn/springboot-prometheus:latest

docker push royalespn/prometheusdeepdrive:001:latest
