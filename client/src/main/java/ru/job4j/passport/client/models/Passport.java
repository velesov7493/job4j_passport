package ru.job4j.passport.client.models;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Passport {

    private long id;
    private int series;
    private int number;
    private Date whenIssued;
    private Date expirationDate;
    private String issuer;
    private String issuerCode;

    public Passport() {
        Calendar cl = Calendar.getInstance();
        whenIssued = cl.getTime();
        cl.add(Calendar.YEAR, 6);
        expirationDate = cl.getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getWhenIssued() {
        return whenIssued;
    }

    public void setWhenIssued(Date whenIssued) {
        this.whenIssued = whenIssued;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return
                id == passport.id
                && series == passport.series
                && number == passport.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, series, number);
    }
}
