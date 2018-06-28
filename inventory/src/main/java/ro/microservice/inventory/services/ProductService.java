package ro.microservice.inventory.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import ro.microservice.inventory.config.KafkaGateway;
import ro.microservice.inventory.entities.Product;
import ro.microservice.inventory.mappers.ProductMapper;
import ro.microservice.inventory.models.ProductModel;
import ro.microservice.inventory.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final KafkaGateway kafkaGateway;

    public ProductService(final ProductRepository productRepository, final KafkaGateway kafkaGateway) {
        this.productRepository = productRepository;
        this.kafkaGateway = kafkaGateway;
    }


    public Optional<ProductModel> getByCode(final String prodCode) {
        return productRepository.findByCode(prodCode).stream()
                .findFirst()
                .map(p -> ProductMapper.toModel(p));
    }

    public ProductModel save(final ProductModel product) {
        Product prod = productRepository.findByCode(product.getCode()).stream()
                .findFirst()
                .map(p -> {
                    Integer initStock = p.getStock();
                    p.setStock(product.getStock());
                    p.setPrice(product.getPrice());
                    if(initStock != product.getStock()) {
                        kafkaGateway.write(product);
                    }
                    return p;
                })
                .orElseGet(() -> ProductMapper.toEntity(product));

        return ProductMapper.toModel(productRepository.save(prod));
    }
}
