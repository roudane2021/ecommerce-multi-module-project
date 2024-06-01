package ma.roudane.commandes.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionWeb.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(ExceptionWeb ex, WebRequest request) {

        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .httpCodeMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}