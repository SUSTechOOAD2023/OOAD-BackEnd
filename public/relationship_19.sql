create table relationship_19
(
    homework_id integer not null
        constraint fk_relation_relations_homework
            references homework
            on update restrict on delete restrict,
    student_id  integer not null
        constraint fk_relation_relations_student
            references student
            on update restrict on delete restrict,
    constraint pk_relationship_19
        primary key (homework_id, student_id)
);

alter table relationship_19
    owner to postgres;

create unique index relationship_19_pk
    on relationship_19 (homework_id, student_id);

create index relationship_24_fk
    on relationship_19 (homework_id);

create index relationship_25_fk
    on relationship_19 (student_id);

