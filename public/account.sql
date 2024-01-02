create table account
(
    account_id       serial
        constraint pk_account
            primary key,
    teacher_id       integer
        constraint fk_account_relations_teacher
            references teacher
            on update restrict on delete restrict,
    student_id       integer
        constraint fk_account_relations_student
            references student
            on update restrict on delete restrict,
    account_name     text not null,
    account_password text not null,
    account_type     text not null,
    cookie           integer,
    email            text,
    admin_id         integer
        constraint account_admin_admin_id_fk
            references admin
);

alter table account
    owner to postgres;

create unique index account_pk
    on account (account_id);

create index relationship_3_fk
    on account (student_id);

create index relationship_5_fk
    on account (teacher_id);

