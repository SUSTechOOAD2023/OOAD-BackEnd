create table teacher
(
    teacher_id          serial
        constraint pk_teacher
            primary key,
    account_id          integer
        constraint fk_teacher_relations_account
            references account
            on update restrict on delete restrict,
    teacher_name        varchar(30),
    teacher_gender      char,
    teacher_department  text,
    teacher_information text
);

alter table teacher
    owner to postgres;

create index relationship_6_fk
    on teacher (account_id);

create unique index teacher_pk
    on teacher (teacher_id);

