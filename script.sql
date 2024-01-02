create sequence class_class_id_seq
    as integer;

create table account
(
    account_id       serial
        constraint pk_account
            primary key,
    teacher_id       integer,
    student_id       integer,
    account_name     text not null,
    account_password text not null,
    account_type     text not null,
    cookie           integer,
    email            text,
    admin_id         integer
);

create unique index account_pk
    on account (account_id);

create index relationship_3_fk
    on account (student_id);

create index relationship_5_fk
    on account (teacher_id);

create table course
(
    course_id   integer not null
        constraint pk_course
            primary key,
    course_name text    not null
);

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

alter sequence class_class_id_seq owned by course_class.class_id;

create unique index class_pk
    on course_class (class_id);

create index relationship_9_fk
    on course_class (course_id);

create unique index course_pk
    on course (course_id);

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

create unique index homework_pk
    on homework (homework_id);

create index relationship_26_fk
    on homework (class_id);

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

create unique index notice_pk
    on notice (notice_id);

create index relationship_13_fk
    on notice (class_id);

create table student
(
    student_id          serial
        constraint pk_student
            primary key,
    account_id          integer
        constraint fk_student_relations_account
            references account
            on update restrict on delete restrict,
    student_name        text,
    student_gender      char,
    technical_stack     text,
    programming_skills  text,
    student_department  text,
    student_information text,
    is_sa               integer,
    intended_teammates  text
);

alter table account
    add constraint fk_account_relations_student
        foreign key (student_id) references student
            on update restrict on delete restrict;

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

create unique index grade_book_pk
    on grade_book (gradebook_id);

create index relationship_16_fk
    on grade_book (class_id);

create index relationship_17_fk
    on grade_book (student_id);

create table relationship_16
(
    notice_id  integer not null
        constraint fk_relation_relations_notice
            references notice
            on update restrict on delete restrict,
    student_id integer not null
        constraint fk_relation_relations_student
            references student
            on update restrict on delete restrict,
    constraint pk_relationship_16
        primary key (notice_id, student_id)
);

create unique index relationship_16_pk
    on relationship_16 (notice_id, student_id);

create index relationship_19_fk
    on relationship_16 (notice_id);

create index relationship_20_fk
    on relationship_16 (student_id);

create table relationship_19
(
    homework_id integer not null
        constraint fk_relation_relations_homework
            references homework
            on update restrict on delete restrict,
    student_id  integer not null
        constraint fk_relation_relations_student
            references student
            on update restrict on delete restrict,
    constraint pk_relationship_19
        primary key (homework_id, student_id)
);

create unique index relationship_19_pk
    on relationship_19 (homework_id, student_id);

create index relationship_24_fk
    on relationship_19 (homework_id);

create index relationship_25_fk
    on relationship_19 (student_id);

create index relationship_4_fk
    on student (account_id);

create unique index student_pk
    on student (student_id);

create table teacher
(
    teacher_id          serial
        constraint pk_teacher
            primary key,
    account_id          integer
        constraint fk_teacher_relations_account
            references account
            on update restrict on delete restrict,
    teacher_name        varchar(30),
    teacher_gender      char,
    teacher_department  text,
    teacher_information text
);

alter table account
    add constraint fk_account_relations_teacher
        foreign key (teacher_id) references teacher
            on update restrict on delete restrict;

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

create unique index group_pk
    on groups (group_id);

create index relationship_15_fk
    on groups (class_id);

create index relationship_21_fk
    on groups (teacher_id);

create table relationship_18
(
    group_number integer not null
        constraint fk_relation_relations_group
            references groups
            on update restrict on delete restrict,
    homework_id  integer not null
        constraint fk_relation_relations_homework
            references homework
            on update restrict on delete restrict,
    constraint pk_relationship_18
        primary key (group_number, homework_id)
);

create unique index relationship_18_pk
    on relationship_18 (group_number, homework_id);

create index relationship_22_fk
    on relationship_18 (group_number);

create index relationship_23_fk
    on relationship_18 (homework_id);

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

create index relationship_18_fk
    on submission (group_id);

create index relationship_7_fk
    on submission (homework_id);

create index relationship_8_fk
    on submission (student_id);

create unique index submission_pk
    on submission (submission_id);

create index relationship_6_fk
    on teacher (account_id);

create unique index teacher_pk
    on teacher (teacher_id);

create table invite_code
(
    invite_code_id serial
        constraint invite_code_pk
            primary key,
    code           text    not null,
    identity       text    not null,
    is_used        integer not null
);

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

create unique index join_group_request_pk
    on join_group_request (join_group_request_id);

create table join_group_invitation
(
    join_group_invitation_id serial
        constraint pk_join_group_invitation
            primary key,
    receive_student_id       integer
        constraint fk_join_group_invitation_relations_student_1
            references student
            on update restrict on delete restrict,
    send_student_id          integer
        constraint fk_join_group_invitation_relations_student_2
            references student
            on update restrict on delete restrict,
    group_id                 integer
        constraint fk_join_group_invitation_relations_group
            references groups
            on update restrict on delete restrict,
    is_accepted              integer default 0 not null,
    send_time                timestamp,
    accept_time              timestamp
);

create unique index join_group_invitation_pk
    on join_group_invitation (join_group_invitation_id);

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

create index relationship_student_class_group_fk_1
    on relationship_student_group (student_id);

create index relationship_student_class_group_fk_3
    on relationship_student_group (group_id);

create table verify_code
(
    email          text,
    identity       text,
    verify_code    text,
    verify_code_id serial
        constraint pk_verify_code
            primary key
);

create table relationship_student_notice
(
    student_id                 integer not null
        constraint fk_relation_relations_student_1
            references student
            on update restrict on delete restrict,
    notice_id                  integer not null
        constraint fk_relation_relations_notice
            references notice
            on update restrict on delete restrict,
    relation_student_notice_id serial
        constraint pk_relationship_student_notice
            primary key
);

create index relationship_student_notice_fk_1
    on relationship_student_notice (student_id);

create index relationship_student_notice_fk_3
    on relationship_student_notice (notice_id);

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

create table admin
(
    admin_id   serial
        constraint admin_pk
            primary key,
    admin_name integer,
    account_id integer
        constraint admin_account_account_id_fk
            references account
);

alter table account
    add constraint account_admin_admin_id_fk
        foreign key (admin_id) references admin;


