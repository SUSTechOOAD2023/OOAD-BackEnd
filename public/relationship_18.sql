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

alter table relationship_18
    owner to postgres;

create unique index relationship_18_pk
    on relationship_18 (group_number, homework_id);

create index relationship_22_fk
    on relationship_18 (group_number);

create index relationship_23_fk
    on relationship_18 (homework_id);

