create table grade_book
(
    gradebook_id      serial
        constraint pk_grade_book
            primary key,
    student_id        integer
        constraint fk_grade_bo_relations_student
            references student
            on update restrict on delete restrict,
    class_id          integer
        constraint fk_grade_bo_relations_class
            references course_class
            on update restrict on delete restrict,
    gradebook_content text
);

alter table grade_book
    owner to postgres;

create unique index grade_book_pk
    on grade_book (gradebook_id);

create index relationship_16_fk
    on grade_book (class_id);

create index relationship_17_fk
    on grade_book (student_id);

