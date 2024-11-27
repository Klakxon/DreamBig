package com.example.DreamBig;

import com.example.DreamBig.entity.User;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
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

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private final ValidationService validationService = new ValidationServiceImpl();

	public DreamBigApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(DreamBigApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (userRepository.count() == 0) {
			createDefaultUser(1L, "admin", "ADMIN", "Administrator", "+380123456789", "admin@example.com");
			createDefaultUser(2L, "user", "USER", "John Doe", "+380987654321", "user@example.com");
			createDefaultUser(3L, "trainer", "TRAINER", "Andrew Koko", "+380961857329", "trainer@example.com");

			System.out.println("Default users created");
		}
	}

	private void createDefaultUser(Long userId, String password, String role, String fullName, String phoneNumber, String email) {
		boolean isPhoneNumberValid = validationService.isValidPhoneNumber(phoneNumber);

		if (!isPhoneNumberValid) {
			throw new IllegalArgumentException("Invalid phone number format");
		}
		if (!validationService.isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email format");
		}

		User user = new User();
		user.setId(userId);
		user.setRole(role);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setPassword(passwordEncoder.encode(password));
		user.setFullName(fullName);
		user.setPhoneNumberValid(true);

		userRepository.save(user);
	}
}