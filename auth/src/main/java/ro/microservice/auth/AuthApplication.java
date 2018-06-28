package ro.microservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.stereotype.Component;
import ro.microservice.auth.entities.User;
import ro.microservice.auth.repositories.UserRepository;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(final String... strings) throws Exception {
		userRepository.save(
				User.builder()
				.username("user")
				.password(passwordEncoder.encode("12345678"))
				.build());
	}
}