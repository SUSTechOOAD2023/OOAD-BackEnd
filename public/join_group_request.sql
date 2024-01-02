create table join_group_request
(
    join_group_request_id serial
        constraint pk_join_group_request
            primary key,
    student_id            integer
        constraint fk_join_group_request_relations_student
            references student
            on update restrict on delete restrict,
    group_id              integer
        constraint fk_join_group_request_relations_group
            references groups
            on update restrict on delete restrict,
    request_content       text              not null,
    request_time          timestamp,
    is_accept             integer default 0 not null
);

alter table join_group_request
    owner to postgres;

create unique index join_group_request_pk
    on join_group_request (join_group_request_id);

