package org.ifsr.anonmailbox.mailbox;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Component
@PropertySource("classpath:mail.properties")
public class MailLogic {
    @Getter
    private final String username;

    private final JavaMailSenderImpl sender;

    public MailLogic(@Value("${spring.mail.username}") String username, @Value("${spring.mail.password}") String password) {
        this.username = username;
        System.out.println(password);
        System.out.println(username);
        this.sender = new JavaMailSenderImpl();
        sender.setHost("mail.ifsr.de");
        sender.setPort(587);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties properties = sender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    public void sendSimpleMessage(String email, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("joachim.stramke@ifsr.de");
        message.setTo("joachim@stramke.com");
        message.setSubject("New Message from AnonMailbox");
        message.setText(messageBuilder(email, text));
        sender.send(message);
    }

    private String messageBuilder(String email, String text) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (email != null) {
            return "New Message in Anon Mailbox at" + time + "\n From: " + email + "\n Message: \n" + text;
        }
        else {
            return "New Message in Anon Mailbox at" + time + "\n Message: \n" + text;
        }
    }
}
