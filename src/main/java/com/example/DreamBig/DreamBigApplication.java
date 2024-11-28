package com.example.DreamBig;

import com.example.DreamBig.entity.*;
import com.example.DreamBig.repository.*;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class DreamBigApplication implements CommandLineRunner {

	private static final int MIN_PASSWORD_LENGTH = 4;

	private final UserRepository userRepository;
	private final TrainerRepository trainerRepository;
	private final ClubRepository clubRepository;
	private final ScheduleRepository scheduleRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final PaymentRepository paymentRepository;
	private final SessionRepository sessionRepository;
	private final PasswordEncoder passwordEncoder;

	private final ValidationService validationService = new ValidationServiceImpl();

	public DreamBigApplication(UserRepository userRepository,
							   TrainerRepository trainerRepository,
							   ClubRepository clubRepository,
							   ScheduleRepository scheduleRepository,
							   SubscriptionRepository subscriptionRepository,
							   PaymentRepository paymentRepository,
							   SessionRepository sessionRepository,
							   PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.trainerRepository = trainerRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.passwordEncoder = passwordEncoder;
		this.paymentRepository = paymentRepository;
		this.sessionRepository = sessionRepository;
		this.clubRepository = clubRepository;
		this.scheduleRepository = scheduleRepository;
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

		if (subscriptionRepository.count() == 0) {
			Optional<User> optionalUser = userRepository.findByEmail("user@example.com");
			Optional<User> optionalAdmin = userRepository.findByEmail("admin@example.com");

			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				creatDefaultSubscription(1L, "MONTHLY", true, user);
			} else {
				System.out.println("User not found with the given email.");
			}

			if (optionalAdmin.isPresent()) {
				User admin = optionalAdmin.get();
				creatDefaultSubscription(2L, "YEARLY", false, admin);
			} else {
				System.out.println("Admin not found with the given email.");
			}
		}

		if (paymentRepository.count() == 0) {
			Optional<User> optionalUser = userRepository.findByEmail("user@example.com");
			Optional<User> optionalAdmin = userRepository.findByEmail("admin@example.com");

			LocalDateTime dateTime = LocalDateTime.of(2024, 11, 15, 10, 30, 0);
			Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				createDefaultPayment(1L, user, 1000.0, "Completed", date);
			} else {
				System.out.println("User not found with the given email.");
			}

			if (optionalAdmin.isPresent()) {
				User admin = optionalAdmin.get();
				createDefaultPayment(2L, admin, 10000.0, "Pending", date);
			} else {
				System.out.println("Admin not found with the given email.");
			}
		}

		if (clubRepository.count() == 0) {
			Optional<User> optionalTrainer = userRepository.findByEmail("trainer@example.com");
			List<Trainer> trainers = new ArrayList<>();

			if (optionalTrainer.isPresent()) {
				User user = optionalTrainer.get();
				Trainer trainer = new Trainer(user);
				trainers.add(trainer);
			}

			List<Session> sessions= new ArrayList<>();

			createDefaultClub(1L, "Клуб на Спортивній", "м. Київ, вул. Спортивна, 10", sessions, trainers, true, true, true);
			createDefaultClub(2L, "Клуб на Олімпійській", "м. Київ, вул. Олімпійська, 42Б", null, null, false, false, false);
		}

		if (scheduleRepository.count() == 0) {
			LocalDateTime dateTime = LocalDateTime.of(2024, 11, 15, 10, 30, 0);
			Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

			List<Session> sessions= new ArrayList<>();

			createDefaultSchedule(1L, date, sessions);
			createDefaultSchedule(2L, date, null);
		}

		if (sessionRepository.count() == 0) {
			Optional<User> optionalUser = userRepository.findByEmail("user@example.com");
			Optional<User> optionalTrainer = userRepository.findByEmail("trainer@example.com");
			Optional<Club> optionalClub = clubRepository.findById(1L);
			Optional<Schedule> scheduleOptional = scheduleRepository.findById(1L);

			LocalDateTime dateTime = LocalDateTime.of(2024, 11, 15, 10, 30, 0);
			Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

			if (optionalUser.isPresent() && optionalClub.isPresent() && optionalTrainer.isPresent() && scheduleOptional.isPresent()) {
				User user = optionalUser.get();
				List<User> participants = new ArrayList<>();
				participants.add(user);
				Trainer trainer = new Trainer(optionalTrainer.get());
				Club club = optionalClub.get();
				Schedule schedule = scheduleOptional.get();
				createDefaultSession(1L, "Group", club, schedule, trainer, participants,  date);
			} else {
				System.out.println("User not found with the given email.");
			}
		}
	}

	private void createDefaultSchedule(Long id, Date date, List<Session> sessions) {
		Schedule schedule = new Schedule();

		schedule.setDate(date);
		schedule.setSessions(sessions);
		schedule.setId(id);

		scheduleRepository.save(schedule);
	}

	private void createDefaultClub(Long id, String name, String address, List<Session> sessions, List<Trainer> trainers, boolean hasGym, boolean hasPool, boolean hasCardioZone) {
		Club club = new Club();

		club.setId(id);
		club.setName(name);
		club.setAddress(address);
		club.setSessions(sessions);
		club.setHasGym(hasGym);
		club.setTrainers(trainers);
		club.setHasPool(hasPool);
		club.setHasCardioZone(hasCardioZone);

		clubRepository.save(club);
	}

	private void createDefaultSession(Long id, String sessionType, Club club, Schedule schedule, Trainer trainer, List<User> participants, Date dateTime) {
		Session session = new Session();

		session.setSessionType(sessionType);
		session.setId(id);
		session.setClub(club);
		session.setSchedule(schedule);
		session.setTrainer(trainer);
		session.setParticipants(participants);
		session.setDateTime(dateTime);

		sessionRepository.save(session);
	}

	private void createDefaultPayment(Long id, User user, Double amount, String status, Date date) {
		Payment payment = new Payment();

		payment.setId(id);
		payment.setUser(user);
		payment.setAmount(amount);
		payment.setStatus(status);
		payment.setDate(date);

		paymentRepository.save(payment);
	}

	private void creatDefaultSubscription(Long id, String type, boolean isActive, User user) {
		Subscription subscription = new Subscription();

		subscription.setActive(isActive);
		subscription.setId(id);
		subscription.setType(type);
		subscription.setUser(user);

		subscriptionRepository.save(subscription);
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
		if (password != null && password.length() >= MIN_PASSWORD_LENGTH) {
			user.setPassword(passwordEncoder.encode(password));
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
		user.setFullName(fullName);
		user.setPhoneNumberValid(true);


		if (role.equals("TRAINER")) {
			Trainer trainer = new Trainer(user);
			trainerRepository.save(trainer);
		} else {
			userRepository.save(user);
		}
	}
}