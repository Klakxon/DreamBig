package com.example.DreamBig;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class DreamBigApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private ValidationService validationService = new ValidationServiceImpl();

	public static void main(String[] args) {
		SpringApplication.run(DreamBigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			createDefaultUser(1L, "adminPassword", "ADMIN", "Administrator", "+380123456789", "admin@example.com");
			createDefaultUser(2L, "userPassword", "USER", "John Doe", "+380987654321", "user@example.com");

			System.out.println("Default users created");
		}
	}

	private void createDefaultUser(Long userId, String password, String role, String fullName, String phoneNumber, String email) {
		if (!validationService.isValidPhoneNumber(phoneNumber)) {
			throw new IllegalArgumentException("Invalid phone number format");
		}
		if (!validationService.isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email format");
		}

		UserEntity user = new UserEntity();
		user.setId(userId);
		user.setRole(role);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setFullName(fullName);

		userRepository.save(user);
	}
}