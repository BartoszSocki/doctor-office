create table registered_users(
   id identity not null,
   username varchar(255) not null,
   password varchar(255) not null,
   role varchar(16) not null,
   primary key (id)
);

create table doctor_info(
    id identity not null,
    registered_user_id int not null,

    name varchar(32) not null,
    surname varchar(32) not null,
    pesel varchar(20) null,
    pwz varchar(100) null,
    specialization varchar(32) not null,

    primary key (id),
    foreign key (registered_user_id) references registered_users(id)
);

-- create table users(
--     id int not null,
--     username varchar(255) not null,
--     user_auth_id int,
--     primary key (id),
--     foreign key (user_auth_id) references users_auth(id)
-- );
