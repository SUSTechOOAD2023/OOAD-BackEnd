create table relationship_course
(
    relationship_id serial
        constraint relationship_course_pk
            primary key,
    student_id      integer
        constraint relationship_course_student_student_id_fk
            references student,
    teacher_id      integer
        constraint relationship_course_teacher_teacher_id_fk
            references teacher,
    course_id       integer,
    sa_id           integer
);

alter table relationship_course
    owner to postgres;

