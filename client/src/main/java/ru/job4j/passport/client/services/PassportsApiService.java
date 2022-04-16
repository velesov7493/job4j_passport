package ru.job4j.passport.client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passport.client.models.Passport;

import java.util.List;

@Service
public class PassportsApiService {

    private static final Logger LOG = LoggerFactory.getLogger(PassportsApiService.class);

    @Value("${passports.api.host}")
    private String apiHost;

    private final RestTemplate rest;

    public PassportsApiService(RestTemplate rest) {
        this.rest = rest;
    }

    public List<Passport> findAllBySeries(int series) {
        ResponseEntity<List<Passport>> resp = null;
        try {
            HttpEntity<Void> request = new HttpEntity<>(null);
            resp = rest.exchange(
                    apiHost + "/find?seria={s}", HttpMethod.GET,
                    request, new ParameterizedTypeReference<>() { }, series
            );
        } catch (Throwable ex) {
            LOG.error("Ошибка получения списка паспортов: ", ex);
        }
        return
                resp == null ? null
                : resp.getBody();
    }

    public List<Passport> findUnavailable() {
        ResponseEntity<List<Passport>> resp = null;
        try {
            HttpEntity<Void> request = new HttpEntity<>(null);
            resp = rest.exchange(
                    apiHost + "/unavailable", HttpMethod.GET,
                    request, new ParameterizedTypeReference<>() { }
            );
        } catch (Throwable ex) {
            LOG.error("Ошибка получения списка паспортов: ", ex);
        }
        return
                resp == null ? null
                : resp.getBody();
    }

    public List<Passport> findReplaceable() {
        ResponseEntity<List<Passport>> resp = null;
        try {
            HttpEntity<Void> request = new HttpEntity<>(null);
            resp = rest.exchange(
                    apiHost + "/find-replaceable", HttpMethod.GET,
                    request, new ParameterizedTypeReference<>() { }
            );
        } catch (Throwable ex) {
            LOG.error("Ошибка получения списка паспортов: ", ex);
        }
        return
                resp == null ? null
                : resp.getBody();
    }

    public List<Passport> findAll() {
        ResponseEntity<List<Passport>> resp = null;
        try {
            HttpEntity<Void> request = new HttpEntity<>(null);
            resp = rest.exchange(
                    apiHost + "/find", HttpMethod.GET,
                    request, new ParameterizedTypeReference<>() { }
            );
        } catch (Throwable ex) {
            LOG.error("Ошибка получения списка паспортов: ", ex);
        }
        return
                resp == null ? null
                : resp.getBody();
    }

    public Passport add(Passport value) {
        ResponseEntity<Passport> resp = null;
        try {
            HttpEntity<Passport> request = new HttpEntity<>(value);
            resp = rest.exchange(apiHost + "/save", HttpMethod.POST, request, Passport.class);
        } catch (Throwable ex) {
            LOG.error("Ошибка создания паспорта: ", ex);
        }
        return resp == null ? null : resp.getBody();
    }

    public boolean update(Passport value) {
        ResponseEntity resp = null;
        try {
            HttpEntity<Passport> request = new HttpEntity<>(value);
            resp = rest.exchange(
                apiHost + "/update?id={id}",
                HttpMethod.PUT, request, Passport.class, value.getId()
            );
        } catch (Throwable ex) {
            LOG.error("Ошибка обновления паспорта: ", ex);
        }
        return resp != null && resp.getStatusCode().is2xxSuccessful();
    }

    public boolean deleteById(Long id) {
        boolean result = false;
        ResponseEntity<Void> resp;
        try {
            HttpEntity<Void> request = new HttpEntity<>(null);
            resp = rest.exchange(
                    apiHost + "/delete?id={id}", HttpMethod.DELETE,
                    request, Void.class, id
            );
            result = resp != null && resp.getStatusCode().is2xxSuccessful();
        } catch (Throwable ex) {
            LOG.error("Ошибка удаления паспорта: ", ex);
        }
        return result;
    }
}