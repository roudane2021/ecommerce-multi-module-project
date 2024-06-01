package ma.roudane.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("params.pagination")
@Data
@ToString
public class PaginationParams {
    private Integer sizeDefault;
    private Integer pageDefault;
}
