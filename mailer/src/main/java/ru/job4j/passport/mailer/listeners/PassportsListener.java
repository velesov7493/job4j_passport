package ru.job4j.passport.mailer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.passport.mailer.services.MailerService;

@Component
public class PassportsListener {

    private final MailerService mailer;

    public PassportsListener(MailerService mailer) {
        this.mailer = mailer;
    }

    @KafkaListener(topics = {"unavailable-passports"})
    public void listenUnavailable(ConsumerRecord<Integer, String> input) {
        mailer.sendMail(input.value());
    }
}