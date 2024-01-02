create table student
(
    student_id          serial
        constraint pk_student
            primary key,
    account_id          integer
        constraint fk_student_relations_account
            references account
            on update restrict on delete restrict,
    student_name        text,
    student_gender      char,
    technical_stack     text,
    programming_skills  text,
    student_department  text,
    student_information text,
    is_sa               integer,
    intended_teammates  text
);

alter table student
    owner to postgres;

create index relationship_4_fk
    on student (account_id);

create unique index student_pk
    on student (student_id);

