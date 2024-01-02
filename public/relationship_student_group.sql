create table relationship_student_group
(
    student_id  integer not null
        constraint fk_relation_relations_student
            references student
            on update restrict on delete restrict,
    group_id    integer not null
        constraint fk_relation_relations_group
            references groups
            on update restrict on delete restrict,
    relation_id serial
        constraint pk_relationship_student__group
            primary key
);

alter table relationship_student_group
    owner to postgres;

create index relationship_student_class_group_fk_1
    on relationship_student_group (student_id);

create index relationship_student_class_group_fk_3
    on relationship_student_group (group_id);

