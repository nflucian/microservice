package ro.microservice.store.resource;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservice.store.models.ProductModel;
import ro.microservice.store.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(final ProductService productService) {
        this.productService = Objects.requireNonNull(productService, "productService should not be null");
    }

    @GetMapping("/list/{category}")
    public Collection<ProductModel> getCategoryProducts(@PathVariable("category") final Long categId) {
        return productService.getByCategory(categId);
    }

    @GetMapping("/{code}")
    public Collection<ProductModel> getByCode(@PathVariable("code") final String code) {
        return productService.getByCode(code);
    }
}
