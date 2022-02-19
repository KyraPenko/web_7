--liquibase formatted sql

--changeset rfatnev:v01
create table resource
(
    id   uuid not null primary key,
    name varchar(256)
)

--changeset ostap:v02
drop table resource;
create table products
(
    id   uuid not null primary key,
    name varchar(256)
);
