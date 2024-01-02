create table homework
(
    homework_id           serial
        constraint pk_homework
            primary key,
    class_id              integer
        constraint fk_homework_relations_class
            references course_class
            on update restrict on delete restrict,
    homework_content      text not null,
    homework_type         text not null,
    homework_title        text not null,
    homework_ddl          text,
    allow_resubmit        integer,
    homework_release_time text,
    max_score             double precision
);

alter table homework
    owner to postgres;

create unique index homework_pk
    on homework (homework_id);

create index relationship_26_fk
    on homework (class_id);

