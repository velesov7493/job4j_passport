package ru.job4j.passport.client.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.client.models.Passport;
import ru.job4j.passport.client.services.PassportsApiService;

import java.util.List;

@RestController
public class PassportController {

    private final PassportsApiService api;

    public PassportController(PassportsApiService api) {
        this.api = api;
    }

    @GetMapping("/passports/by-seria/{seria}")
    public ResponseEntity<List<Passport>> findAllBySeries(@PathVariable("seria") int series) {
        List<Passport> result = api.findAllBySeries(series);
        return
                result == null || result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/passports/unavailable")
    public ResponseEntity<List<Passport>> findUnavailable() {
        List<Passport> result = api.findUnavailable();
        return
                result == null || result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/passports/replaceable")
    public ResponseEntity<List<Passport>> findReplaceable() {
        List<Passport> result = api.findReplaceable();
        return
                result == null || result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/passports")
    public ResponseEntity<List<Passport>> findAll() {
        List<Passport> result = api.findAll();
        return
                result == null || result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/passports")
    public ResponseEntity<Passport> add(@RequestBody Passport value) {
        Passport result = api.add(value);
        return
                result == null
                ? new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE)
                : new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/passports")
    public ResponseEntity<Passport> update(@RequestBody Passport value) {
        return
                api.update(value)
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/passport/{id}")
    public ResponseEntity deleteById(@PathVariable("id") long passportId) {
        return
                api.deleteById(passportId)
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
