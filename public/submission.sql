create table submission
(
    submission_id      serial
        constraint pk_submission
            primary key,
    group_id           integer
        constraint fk_submissi_relations_group
            references groups
            on update restrict on delete restrict,
    student_id         integer
        constraint fk_submissi_relations_student
            references student
            on update restrict on delete restrict,
    homework_id        integer
        constraint fk_submissi_relations_homework
            references homework
            on update restrict on delete restrict,
    submission_content text not null,
    submission_comment text,
    submission_time    text,
    submission_score   double precision,
    review_time        text
);

alter table submission
    owner to postgres;

create index relationship_18_fk
    on submission (group_id);

create index relationship_7_fk
    on submission (homework_id);

create index relationship_8_fk
    on submission (student_id);

create unique index submission_pk
    on submission (submission_id);

