package ro.microservice.store.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.microservice.store.entities.Category;

@Data
@AllArgsConstructor
@Builder
public class ProductModel {
    private String code;
    private String name;
    private Category category;
    private BigDecimal price;
    private Boolean isPublished;
}
