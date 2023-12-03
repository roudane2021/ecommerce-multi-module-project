package ma.roudane.produits.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler  {

    @ExceptionHandler(ExceptionWeb.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleResourceNotFoundException(ExceptionWeb ex, ServerWebExchange exchange) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(exchange.toString())
                .httpCodeMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return Mono.just(new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST));
    }
}
