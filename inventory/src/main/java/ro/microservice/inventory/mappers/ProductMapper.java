package ro.microservice.inventory.mappers;

import ro.microservice.inventory.entities.Product;
import ro.microservice.inventory.models.ProductModel;

public class ProductMapper {
    public static ProductModel toModel(final Product product) {
         return ProductModel.builder()
                 .code(product.getCode())
                 .price(product.getPrice())
                 .stock(product.getStock())
                 .build();
    }

    public static Product toEntity(final ProductModel product) {
        return Product.builder()
                .code(product.getCode())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
