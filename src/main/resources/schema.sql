create table users_auth(
   id int not null,
   username varchar(255) not null,
   encoded_password varchar(255) not null,
   role varchar(16) not null,
   primary key (id)
);

-- create table users(
--     id int not null,
--     username varchar(255) not null,
--     user_auth_id int,
--     primary key (id),
--     foreign key (user_auth_id) references users_auth(id)
-- );
