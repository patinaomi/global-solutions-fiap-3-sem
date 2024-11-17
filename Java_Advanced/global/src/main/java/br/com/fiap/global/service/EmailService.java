package br.com.fiap.global.service;

public interface EmailService {

    void sendEmail(String destinatario, String assunto, String msg);

}
