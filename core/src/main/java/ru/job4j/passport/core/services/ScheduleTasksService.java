package ru.job4j.passport.core.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passport.core.models.Passport;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleTasksService {

    private final KafkaTemplate<Integer, String> template;
    private final PassportService passports;

    public ScheduleTasksService(
            KafkaTemplate<Integer, String> template,
            PassportService passports
    ) {
        this.template = template;
        this.passports = passports;
    }

    @Scheduled(fixedDelay = 10000)
    public void taskSendUnavailable() {
        List<Passport> list = passports.findAllByExpirationDateBefore(new Date());
        list.forEach((p) -> template.send("unavailable-passports", p.toString()));
    }
}
