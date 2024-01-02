create table relationship_16
(
    notice_id  integer not null
        constraint fk_relation_relations_notice
            references notice
            on update restrict on delete restrict,
    student_id integer not null
        constraint fk_relation_relations_student
            references student
            on update restrict on delete restrict,
    constraint pk_relationship_16
        primary key (notice_id, student_id)
);

alter table relationship_16
    owner to postgres;

create unique index relationship_16_pk
    on relationship_16 (notice_id, student_id);

create index relationship_19_fk
    on relationship_16 (notice_id);

create index relationship_20_fk
    on relationship_16 (student_id);

