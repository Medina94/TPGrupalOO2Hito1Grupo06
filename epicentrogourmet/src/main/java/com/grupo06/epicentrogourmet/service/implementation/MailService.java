package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.service.IMailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService implements IMailService {
    Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String mailFrom;
    @Value("classpath:templates/mail/mail-alta-empleado.html")
    private Resource template;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void enviarMailAlta(RegistroEmpleadoDTO dto, String username) {
        try {
            // 1- Levanto el contenido del template HTML
            String htmlContent = new String(template.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            // 2- Reemplazar las variables por los valores del usuario
            htmlContent = htmlContent.replace("{{NOMBRE}}", dto.getNombre() + " " + dto.getApellido())
                    .replace("{{USERNAME}}", username)
                    .replace("{{PASSWORD}}", dto.getPassword());

            // 3- Creo el mensaje del mail y seteo los valores necesarios
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(dto.getEmail());
            helper.setSubject("Alta de empleado en Epicentro Gourmet");
            helper.setText(htmlContent, true);

            // 4- Adjuntar la imagen para que se muestre en el correo
            ClassPathResource imageResource = new ClassPathResource("static/images/logo.jpg");
            helper.addInline("epicentrogourmetLogo", imageResource);

            mailSender.send(mimeMessage);

        } catch (IOException | MessagingException e) {
            logger.error("Error en el envío de mail: {}", e.getMessage());
            throw new RuntimeException("Error al leer la plantilla o enviar el correo", e);
        }
    }
}
