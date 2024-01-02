create table notice
(
    notice_id      serial
        constraint pk_notice
            primary key,
    class_id       integer
        constraint fk_notice_relations_class
            references course_class
            on update restrict on delete restrict,
    notice_content text not null,
    notice_title   text,
    release_time   text
);

alter table notice
    owner to postgres;

create unique index notice_pk
    on notice (notice_id);

create index relationship_13_fk
    on notice (class_id);

