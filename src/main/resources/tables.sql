drop table if exists global_contact CASCADE;
drop table if exists global_address CASCADE;
drop table if exists global_user CASCADE; 

create table global_user (
   id bigint auto_increment, 
   name varchar(255) not null,   
   primary key (id)
);
   
create table global_address (
   id bigint auto_increment,   
   user_id bigint,
   address_type varchar(100),
   country varchar(100),
   city varchar(100),
   place_name varchar(255),
   street_number varchar(50),
   primary key (id)
);

alter table global_address add constraint fk_user_id_address foreign key (user_id) references global_user (id);

create table global_contact (
   id bigint auto_increment,
   address_id bigint,
   email varchar(255),
   phone_number varchar(100),
   primary key (id)
);

alter table global_contact add constraint fk_address_id_contact foreign key (address_id) references global_address (id);
