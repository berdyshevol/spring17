create table if not exists marathon
(
    id bigserial not null
        constraint marathon_pkey
        primary key,
    title varchar(255) not null
);

-- alter table marathon owner to postgres;

create table if not exists sprint
(
    id bigserial not null
        constraint sprint_pkey
        primary key,
    finish date,
    start_date date,
    title varchar(255),
    marathon_id bigint
        constraint sprint_marathon_id_fkey
        references marathon
);

-- alter table sprint owner to postgres;

create table if not exists task
(
    id bigserial not null
        constraint task_pkey
        primary key,
    created date not null,
    title varchar(255),
    updated date,
    sprint_id bigint
        constraint task_sprint_id_fkey
        references sprint
);

-- alter table task owner to postgres;

create table if not exists roles
(
    id bigserial not null
        constraint roles_pk
        primary key,
    name varchar not null
);

-- alter table roles owner to postgres;

create table if not exists users
(
    id bigserial not null
        constraint users_pkey
        primary key,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(20) not null,
    password varchar(255) not null,
    role_id bigint not null
        constraint users_roles_id_fk
        references roles
);

-- alter table users owner to postgres;

create table if not exists marathon_user
(
    marathon_id bigint
        constraint marathon_user_marathon_id_fkey
        references marathon,
    user_id bigint
        constraint marathon_user_user_id_fkey
        references users
);

-- alter table marathon_user owner to postgres;

create table if not exists progress
(
    id bigserial not null
        constraint progress_pkey
        primary key,
    started date,
    status varchar(255) not null,
    updated date,
    task_id bigint
        constraint progress_task_id_fkey
        references task,
    trainee_id bigint
        constraint progress_trainee_id_fkey
        references users,
    constraint uk2g4o6691pi6ls2bkdoo29875u
        unique (task_id, trainee_id)
);

-- alter table progress owner to postgres;

create unique index if not exists roles_id_uindex
    on roles (id);

insert into users (email, first_name, last_name, password, role_id) values ( 'petro@gmail.com', 'Petro', 'Markiv', '12345', '1' );

