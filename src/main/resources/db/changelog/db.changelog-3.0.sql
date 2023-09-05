--liquibase formatted sql

--changeset sharov:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset sharov:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);