create table relationship_student_notice
(
    student_id                 integer not null
        constraint fk_relation_relations_student_1
            references student
            on update restrict on delete restrict,
    notice_id                  integer not null
        constraint fk_relation_relations_notice
            references notice
            on update restrict on delete restrict,
    relation_student_notice_id serial
        constraint pk_relationship_student_notice
            primary key
);

alter table relationship_student_notice
    owner to postgres;

create index relationship_student_notice_fk_1
    on relationship_student_notice (student_id);

create index relationship_student_notice_fk_3
    on relationship_student_notice (notice_id);

