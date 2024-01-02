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

alter table join_group_invitation
    owner to postgres;

create unique index join_group_invitation_pk
    on join_group_invitation (join_group_invitation_id);

