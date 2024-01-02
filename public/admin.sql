create table admin
(
    admin_id   serial
        constraint admin_pk
            primary key,
    admin_name integer,
    account_id integer
        constraint admin_account_account_id_fk
            references account
);

alter table admin
    owner to postgres;

