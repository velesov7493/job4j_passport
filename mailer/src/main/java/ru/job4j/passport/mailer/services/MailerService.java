package ru.job4j.passport.mailer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.passport.mailer.models.MailMessage;

@Service
public class MailerService {

    private static final Logger LOG = LoggerFactory.getLogger(MailerService.class);

    public void sendMail(String text) {
        MailMessage msg = new MailMessage();
        msg.setSubject("Паспорт просрочен");
        msg.setSender("info@passports.mvd.ru");
        msg.addReceiver("employee22@mail.ru");
        msg.setText(text);
        LOG.info(msg.toString());
    }
}
