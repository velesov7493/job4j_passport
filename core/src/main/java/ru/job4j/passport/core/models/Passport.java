package ru.job4j.passport.core.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tz_passports")
public class Passport {

    @Id
    @SequenceGenerator(
            name = "passportsIdSeq",
            sequenceName = "tz_passports_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passportsIdSeq")
    private long id;
    private int series;
    private int number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date whenIssued;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    private String issuer;
    private String issuerCode;

    public Passport() {
        Calendar cl = Calendar.getInstance();
        whenIssued = cl.getTime();
        cl.add(Calendar.YEAR, 6);
        expirationDate = cl.getTime();
    }

    public Passport replace(Passport other) {
        series = other.series;
        number = other.number;
        whenIssued = other.whenIssued;
        expirationDate = other.expirationDate;
        issuer = other.issuer;
        issuerCode = other.issuerCode;
        return this;
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
                series == passport.series
                && number == passport.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, number);
    }
}