package ro.microservice.inventory.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservice.inventory.models.ProductModel;
import ro.microservice.inventory.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;


    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductModel> getProductByCode(@PathVariable("code") final String prodCode) {
        return productService.getByCode(prodCode)
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody final ProductModel product) {
        return ResponseEntity.ok(productService.save(product));
    }
}
