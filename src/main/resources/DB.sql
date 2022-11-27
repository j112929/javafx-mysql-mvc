create table user
(
    id            int auto_increment
        primary key,
    username      varchar(64)  null,
    password      varchar(64)  null,
    hash_password varchar(256) null,
    constraint user_id_uindex
        unique (id)
);

create unique index user_id_uindex
    on user (id);

