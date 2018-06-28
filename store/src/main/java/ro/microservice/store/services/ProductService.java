package ro.microservice.store.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ro.microservice.store.clients.InventoryClient;
import ro.microservice.store.mappers.ProductMapper;
import ro.microservice.store.models.InventoryModel;
import ro.microservice.store.models.ProductModel;
import ro.microservice.store.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductService(final ProductRepository productResposytory, final InventoryClient inventoryClient) {
        this.productRepository = productResposytory;
        this.inventoryClient = inventoryClient;
    }


    public Collection<ProductModel> getByCategory(final Long categId) {
        return productRepository.findByCategoryId(categId).stream()
                .map(p -> {
                    final InventoryModel inventory = inventoryClient.getProductInventory(p.getCode()).getBody();
                    return  ProductMapper.toModel(p, inventory);
                })
                .collect(Collectors.toList());
    }

    public Collection<ProductModel> getByCode(final String code) {
        return productRepository.findByCode(code).stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }
}
