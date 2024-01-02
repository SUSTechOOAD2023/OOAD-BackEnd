create table course_class
(
    class_id           integer default nextval('class_class_id_seq'::regclass) not null
        constraint pk_class
            primary key,
    course_id          integer
        constraint fk_class_relations_course
            references course
            on update restrict on delete restrict,
    course_name        text                                                    not null,
    minimum_group_size integer default 0                                       not null,
    maximum_group_size integer default 0                                       not null,
    course_title       text,
    course_description text
);

alter table course_class
    owner to postgres;

create unique index class_pk
    on course_class (class_id);

create index relationship_9_fk
    on course_class (course_id);

