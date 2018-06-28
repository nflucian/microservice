package ro.microservice.inventory.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ro.microservice.inventory.models.ProductModel;

@MessagingGateway
public interface KafkaGateway {

    @Gateway(requestChannel = "stockChannel")
    void write(final ProductModel product);

}
