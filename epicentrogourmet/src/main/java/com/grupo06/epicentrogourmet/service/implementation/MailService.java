package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.dto.RegistroUnidadResponsableDTO;
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
        enviarMail(
                dto.getNombre() + " " + dto.getApellido(),
                dto.getEmail(),
                username,
                dto.getPassword(),
                "Alta de empleado en Epicentro Gourmet"
        );
    }

    @Override
    public void enviarMailAltaResponsable(RegistroUnidadResponsableDTO registroUnidadResponsableDTO, String username) {
        enviarMail(
                registroUnidadResponsableDTO.getNombreResponsable() + " "
                        + registroUnidadResponsableDTO.getApellidoResponsable(),
                registroUnidadResponsableDTO.getEmailResponsable(),
                username,
                registroUnidadResponsableDTO.getPassword(),
                "Alta de responsable de unidad en Epicentro Gourmet"
        );
    }

    private void enviarMail(
            String nombre,
            String email,
            String username,
            String password,
            String asunto) {
        try {
            String htmlContent = new String(
                    template.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );
            htmlContent = htmlContent.replace("{{NOMBRE}}", nombre)
                    .replace("{{USERNAME}}", username)
                    .replace("{{PASSWORD}}", password);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject(asunto);
            helper.setText(htmlContent, true);

            ClassPathResource imageResource = new ClassPathResource("static/images/logo.jpg");
            helper.addInline("epicentrogourmetLogo", imageResource);
            mailSender.send(mimeMessage);
        } catch (IOException | MessagingException e) {
            logger.error("Error en el envío de mail: {}", e.getMessage());
            throw new RuntimeException("Error al leer la plantilla o enviar el correo", e);
        }
    }
}
