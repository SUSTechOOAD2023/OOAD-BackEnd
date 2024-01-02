create table course
(
    course_id   integer not null
        constraint pk_course
            primary key,
    course_name text    not null
);

alter table course
    owner to postgres;

create unique index course_pk
    on course (course_id);

