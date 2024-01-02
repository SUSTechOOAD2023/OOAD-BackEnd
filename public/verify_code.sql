create table verify_code
(
    email          text,
    identity       text,
    verify_code    text,
    verify_code_id serial
        constraint pk_verify_code
            primary key
);

alter table verify_code
    owner to postgres;

