create table invite_code
(
    invite_code_id serial
        constraint invite_code_pk
            primary key,
    code           text    not null,
    identity       text    not null,
    is_used        integer not null
);

alter table invite_code
    owner to postgres;

