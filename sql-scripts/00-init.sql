create table country
(
    id        int auto_increment
        primary key,
    iso       varchar(2)  not null,
    iso3      varchar(3)  null,
    name      varchar(80) not null,
    nicename  varchar(80) not null,
    numcode   smallint    null,
    phonecode int         not null
);

create table university
(
    id             bigint auto_increment
        primary key,
    alpha_two_code varchar(255)   null,
    country        varchar(255)   null,
    domains        varbinary(255) null,
    name           varchar(255)   null,
    state_province varchar(255)   null,
    web_pages      varbinary(255) null
);

