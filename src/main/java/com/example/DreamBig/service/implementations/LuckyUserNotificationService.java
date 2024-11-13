package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LuckyUserNotificationService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public LuckyUserNotificationService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public void sendLuckyUserNotification() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("Немає користувачів для вибору.");
            return;
        }

        Random random = new Random();
        UserEntity luckyUser = users.get(random.nextInt(users.size()));

        sendEmail(luckyUser.getEmail());
    }

    private void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Вітаємо! Ви щасливчик!");
        message.setText("Вітаємо! Ви виграли випадковий розіграш цього місяця!");
        mailSender.send(message);
        System.out.println("Сповіщення відправлено на: " + email);
    }
}