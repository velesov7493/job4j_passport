DROP TABLE IF EXISTS tz_passports;

CREATE TABLE tz_passports (
    id BIGSERIAL PRIMARY KEY,
    series INTEGER NOT NULL,
    number INTEGER NOT NULL,
    when_issued TIMESTAMP NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    issuer VARCHAR(250) NOT NULL,
    issuer_code VARCHAR(20) NOT NULL,
    UNIQUE (series, number)
);