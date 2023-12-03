package ma.roudane.commandes.config.exception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@ToString
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String mensaje;
    private String detalles;
    private String httpCodeMessage;
    private Map<String, String> errors;
}
