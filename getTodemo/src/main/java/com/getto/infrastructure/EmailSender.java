package com.getto.infrastructure;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body) throws MessagingException, MailException {
        if (to != null && subject != null && body != null) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("alsoft27@gmail.com");
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);
            javaMailSender.send(message);
        } else {
            throw new MessagingException("can't be null: to, subject or body");
        }

    }
}
