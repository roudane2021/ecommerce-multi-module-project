package ma.roudane.produits.web.produit.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriteriaDto {
    private String name;
    private Object value;
    private List<Object> listValue;
    private OperatorDto operator;
}
