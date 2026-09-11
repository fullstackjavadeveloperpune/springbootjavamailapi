package com.fullstack.service;

import com.fullstack.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public void sendEmail(EmailModel emailModel) throws MessagingException {

        // Business Logic

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(fromEmail);

        mimeMessageHelper.setTo(emailModel.getToEmail());

        mimeMessageHelper.setCc(emailModel.getCcEmail());

        mimeMessageHelper.setSubject(emailModel.getEmailSubject());

        mimeMessageHelper.setText(emailModel.getEmailBody());

        File file = new File(emailModel.getEmailAttachment());

        mimeMessageHelper.addAttachment(file.getName(), file);

        javaMailSender.send(mimeMessage);

        log.info("Email Sent Successfully");

    }
}
