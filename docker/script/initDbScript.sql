CREATE TABLE IF NOT EXISTS language
(
    id   bigint not null
        constraint language_pkey
            primary key,
    name varchar(20)
);

CREATE TABLE IF NOT EXISTS stock
(
    id       bigint not null
        constraint stock_pkey
            primary key,
    name     varchar(255),
    quantity integer
);

CREATE TABLE IF NOT EXISTS usr
(
    id          bigint not null
        constraint usr_pkey
            primary key,
    active      boolean,
    avatar_path text,
    birth_date  date,
    country     varchar(20),
    email       varchar(50),
    password    varchar(200),
    state       varchar(20),
    username    varchar(20)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id bigint not null
        constraint fkfpm8swft53ulq2hl11yplpr5
            references usr,
    roles   varchar(255)
);

INSERT INTO public.language (id, name)
VALUES (1, 'ru'),
       (2, 'en');

INSERT INTO public.usr (id, active, avatar_path, birth_date, country, email, password, state, username)
VALUES (1, true, null, '2021-09-01', 'Canada', 'admin@admin.com',
        '$2a$08$Vd/oShEmjbOsBXgsHjWBrOhg2.FwF8S/rB6Zw5pMpCSYEoKsOkgE.', 'Cariboo', 'admin');

INSERT INTO public.user_role (user_id, roles)
VALUES (1, 'ADMIN');

INSERT INTO public.stock (id, name, quantity)
VALUES (1, 'Milk', 99999),
       (2, 'Nuts', 99999);