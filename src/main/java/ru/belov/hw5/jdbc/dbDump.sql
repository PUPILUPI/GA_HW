CREATE SCHEMA IF NOT EXISTS homework;
drop table homework."user";
CREATE TABLE IF NOT EXISTS homework.user
(
    id          SERIAL PRIMARY KEY,
    second_name VARCHAR,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    birth_date  DATE,
    salary      NUMERIC(1000,2)
);
