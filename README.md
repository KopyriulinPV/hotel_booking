Бэкенд-составляющая сервиса бронирования отелей с возможностью управлять контентом. 

Используемые технологии: Spring Web MVC, Spring Data JPA, Spring security, MongoDB, Kafka, PostgreSQL. 

Для запуска приложения необходим Docker:

перейдите в папку - cd docker;

соберите образы и запустить контейнеры - start docker-start.cmd;

приложение hotel_booking запустится вместе с контейнерами: zookeeper, kafka, mongodb, postgres.

С помощью Postman можно отправлять запросы к контроллерам. 

