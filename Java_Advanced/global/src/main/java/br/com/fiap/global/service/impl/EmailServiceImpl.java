package br.com.fiap.global.service.impl;

import br.com.fiap.global.service.EmailService;
import br.com.fiap.global.service.exception.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Override
    public void sendEmail(String destinatario, String assunto, String msg) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(remetente);
            email.setTo(destinatario);
            email.setSubject(assunto);
            email.setText(msg);
            mailSender.send(email);

        } catch (Exception e) {
            throw new EmailException("Erro ao enviar e-mail para: " + destinatario, e);
        }
    }
}
