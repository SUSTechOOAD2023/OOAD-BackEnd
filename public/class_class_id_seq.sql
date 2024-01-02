create sequence class_class_id_seq
    as integer;

alter sequence class_class_id_seq owner to postgres;

alter sequence class_class_id_seq owned by course_class.class_id;

