package ro.microservice.store.mappers;

import java.math.BigDecimal;

import ro.microservice.store.entities.Product;
import ro.microservice.store.models.InventoryModel;
import ro.microservice.store.models.ProductModel;

public class ProductMapper {

    public static ProductModel toModel(final Product product) {
        return ProductMapper.toModel(product, InventoryModel.builder().price(BigDecimal.ZERO).build());
    }

    public static ProductModel toModel(final Product product, final InventoryModel inventory) {
        return ProductModel.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .price(inventory.getPrice())
                .isPublished(product.getIsPublished())
                .build();
    }

}
