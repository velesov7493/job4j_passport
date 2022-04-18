[![Build Status](https://app.travis-ci.com/velesov7493/job4j_passport.svg?branch=master)](https://app.travis-ci.com/velesov7493/job4j_passport)
## Описание ##
Это учебный проект. Сборник REST-сервисов.
#### Решенные задачи: ####
1. Синхронный обмен сообщений [#458496 #289630]
2. Асинхронное взаимодействие [#458497 #290052]
#### Сервис core ####
REST-сервис, исполняющий все операции с паспортами.
#### Сервис client ####
REST-сервис, обращающийся к core через RestTemplate.
#### Сервис mailer ####
REST-сервис, через kafka получающий от core сообщения о просроченных паспортах.
#### Технологии проекта ####
![badge](https://img.shields.io/badge/PostgreSQL-12-blue)
![badge](https://img.shields.io/badge/Java-14-green)
![badge](https://img.shields.io/badge/Maven-3.6-green)
![badge](https://img.shields.io/badge/SpringBot-2.6-yellow)
![badge](https://img.shields.io/badge/Kafka-3.1.0-indigo)