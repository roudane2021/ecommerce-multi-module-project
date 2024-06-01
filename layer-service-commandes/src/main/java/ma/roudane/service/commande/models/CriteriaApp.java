package ma.roudane.service.commande.models;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriteriaApp {
    private String name;
    private Object value;
    private List<Object> listValue;
    private OperatorApp operator;
}
