package in.co.iman.prometheusdeepdrive.controllers;

import io.micrometer.core.instrument.Counter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    public Counter hitCounter;

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

        hitCounter.increment();
        String bodyOfResponse = "Oops! something wrong";

        log.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(value = {AmqpConnectException.class})
    protected ResponseEntity<Object> handleRabbitException(RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "RabbitMQ is down !";

        log.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}