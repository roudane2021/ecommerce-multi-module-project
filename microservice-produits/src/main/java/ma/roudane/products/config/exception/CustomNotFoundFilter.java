package ma.roudane.products.config.exception;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Order(-2)
public class CustomNotFoundFilter implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    public CustomNotFoundFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ResponseStatusException && ((ResponseStatusException) ex).getStatus() == HttpStatus.NOT_FOUND) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .mensaje(ex.getMessage())
                    .detalles(exchange.toString())
                    .httpCodeMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .build();
            byte[] bytes = new byte[0];
            try {
                bytes = objectMapper.writeValueAsBytes(exceptionResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            DataBuffer buffer = bufferFactory.wrap(bytes);

            // Retourner et écrire le corps de la réponse
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
        return Mono.error(ex);
    }
}



