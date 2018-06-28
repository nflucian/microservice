package ro.microservice.store.clients;

import java.math.BigDecimal;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.microservice.store.models.InventoryModel;

@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

    @Override
    public InventoryClient create(final Throwable throwable) {
        return new InventoryClient() {
            @Override
            public ResponseEntity<InventoryModel> getProductInventory(final String code) {
                return ResponseEntity.ok(InventoryModel.builder()
                    .code(code)
                    .price(BigDecimal.ZERO)
                    .build());
            }
        };
    }
}
