package ma.roudane.config.exception;

import ma.roudane.service.exception.ExceptionApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler({ExceptionWeb.class, ExceptionApplication.class})
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .httpCodeMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(HttpMediaTypeNotSupportedException ex, WebRequest request) {


        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .httpCodeMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                .build();
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(LocalDateTime.now())
                .mensaje("Validation failed")
                .errors(errors)
                .detalles(request.getDescription(false))
                .httpCodeMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}