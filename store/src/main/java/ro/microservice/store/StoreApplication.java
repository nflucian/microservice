package ro.microservice.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import ro.microservice.store.config.KafkaChannels;
import ro.microservice.store.entities.Category;
import ro.microservice.store.entities.Product;
import ro.microservice.store.repositories.CategoryRepository;
import ro.microservice.store.repositories.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableBinding(KafkaChannels.class)
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}


@Component
class DummyData implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(final String... args) throws Exception {
        final Category category = categoryRepository.save(
                Category.builder().name("Test Categ").build()
        );

        productRepository.save(
                Product.builder()
                    .code("prod1")
                    .name("Test Prod")
                    .category(category)
                    .build()
        );
    }

}