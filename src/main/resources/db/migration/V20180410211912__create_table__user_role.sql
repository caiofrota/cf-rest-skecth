-- Create table
create table user_role (
  created_by bigint,
  created_on datetime,
  updated_by bigint,
  updated_on datetime,
  role_id    bigint    not null,
  user_id    bigint    not null,
  primary key (role_id, user_id)
)
engine=MyISAM
DEFAULT CHARSET=UTF8
;

-- Relationships
alter table user_role add constraint user_role_fk2
                         foreign key (role_id)
                          references role (id)
;

alter table user_role add constraint user_role_fk1
                         foreign key (user_id)
                          references user (id)
;
