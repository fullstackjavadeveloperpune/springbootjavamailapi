package com.fullstack.service;

import com.fullstack.model.EmailModel;
import jakarta.mail.MessagingException;

public interface IEmailService {

    void sendEmail(EmailModel emailModel) throws MessagingException;
}
