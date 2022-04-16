package ru.job4j.passport.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.passport.core.models.Passport;
import ru.job4j.passport.core.repositories.PassportRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    private static final Logger LOG = LoggerFactory.getLogger(PassportService.class);

    private final PassportRepository passports;

    public PassportService(PassportRepository passports) {
        this.passports = passports;
    }

    public List<Passport> findAllBySeries(int series) {
        return passports.findAllBySeries(series);
    }

    public List<Passport> findAllByExpirationDateBefore(Date when) {
        return passports.findAllByExpirationDateBefore(when);
    }

    public List<Passport> findAllByExpirationDateBetween(Date start, Date end) {
        return passports.findAllByExpirationDateBetween(start, end);
    }

    public List<Passport> findAll() {
        return passports.findAll();
    }

    public Passport save(Passport entity) {
        Passport result = null;
        try {
            result = passports.save(entity);
        } catch (Throwable ex) {
            LOG.error("Ошибка при сохранении паспорта: ", ex);
        }
        return result;
    }

    public Optional<Passport> findById(Long aLong) {
        return passports.findById(aLong);
    }

    public boolean deleteById(Long aLong) {
        boolean result = false;
        try {
            passports.deleteById(aLong);
            result = true;
        } catch (Throwable ex) {
            LOG.error("Ошибка при удалении паспорта: ", ex);
        }
        return result;
    }
}
