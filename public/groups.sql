create table groups
(
    group_id       serial
        constraint pk_group
            primary key,
    teacher_id     integer
        constraint fk_group_relations_teacher
            references teacher
            on update restrict on delete restrict,
    class_id       integer
        constraint fk_group_relations_class
            references course_class
            on update restrict on delete restrict,
    group_name     varchar(30)       not null,
    group_size     integer default 0 not null,
    group_task     text,
    group_deadline timestamp,
    group_visible  integer
);

alter table groups
    owner to postgres;

create unique index group_pk
    on groups (group_id);

create index relationship_15_fk
    on groups (class_id);

create index relationship_21_fk
    on groups (teacher_id);

