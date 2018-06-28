package ro.microservice.inventory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;
import ro.microservice.inventory.config.KafkaChannels;
import ro.microservice.inventory.entities.Product;
import ro.microservice.inventory.repositories.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(KafkaChannels.class)
@IntegrationComponentScan(basePackages = "ro.microservice.inventory")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner {

	@Value("${test.value}")
	private Integer value;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(final String... strings) throws Exception {
		productRepository.save(Product.builder().code("prod1").price(new BigDecimal(value)).build());
	}
}