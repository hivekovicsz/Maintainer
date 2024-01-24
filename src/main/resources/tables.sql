drop table if exists global_contact;
drop table if exists global_address;
drop table if exists global_user; 

create table global_user (
   id bigint IDENTITY(1,1), 
   name varchar(255) not null,   
   primary key (id)
);
   
create table global_address (
   id bigint IDENTITY(1,1),
   user_id bigint,
   address_type varchar(100),
   country varchar(100),
   city varchar(100),
   place_name varchar(255),
   street_number varchar(50),
   primary key (id)
);

alter table global_address add constraint fk_user_id_address foreign key (user_id) references global_user (id);
alter table global_address add constraint uk_address unique (user_id, address_type);

create table global_contact (
   id bigint IDENTITY(1,1),
   address_id bigint,
   email varchar(255),
   phone_number varchar(100),
   primary key (id)
);

alter table global_contact add constraint fk_address_id_contact foreign key (address_id) references global_address (id);