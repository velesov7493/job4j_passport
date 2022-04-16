package ru.job4j.passport.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.core.models.Passport;

import java.util.Date;
import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    List<Passport> findAllBySeries(int series);

    List<Passport> findAllByExpirationDateBefore(Date when);

    List<Passport> findAllByExpirationDateBetween(Date start, Date end);
}
