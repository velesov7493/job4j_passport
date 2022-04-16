package ru.job4j.passport.core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.core.models.Passport;
import ru.job4j.passport.core.services.PassportService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PassportsController {

    private final PassportService passports;

    public PassportsController(PassportService passports) {
        this.passports = passports;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> getAllBySeries(
            @RequestParam(name = "seria", required = false) Integer series
    ) {
        List<Passport> list =
                series != null
                ? passports.findAllBySeries(series)
                : passports.findAll();
        return
                list == null || list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> getAllUnavailable() {
        List<Passport> list = passports.findAllByExpirationDateBefore(new Date());
        return
                list == null || list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> getAllReplaceable() {
        Calendar cl = Calendar.getInstance();
        Date start = cl.getTime();
        cl.add(Calendar.MONTH, 3);
        List<Passport> list = passports.findAllByExpirationDateBetween(start, cl.getTime());
        return
                list == null || list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        Passport result = passports.save(passport);
        return
                result != null
                ? new ResponseEntity<>(result, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/update")
    public ResponseEntity update(
            @RequestParam("id") long passportId,
            @RequestBody Passport passport
    ) {
        ResponseEntity result = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<Passport> oldPassport = passports.findById(passportId);
        if (oldPassport.isPresent()) {
            Passport p = passports.save(oldPassport.get().replace(passport));
            result =
                    p != null
                    ? new ResponseEntity(HttpStatus.ACCEPTED)
                    : new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return result;
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") long passportId) {
        return
                passports.deleteById(passportId)
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}