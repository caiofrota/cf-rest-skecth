-- Create table
create table role_permission (
  created_by    bigint,
  created_on    datetime,
  updated_by    bigint,
  updated_on    datetime,
  permission_id bigint    not null,
  role_id       bigint    not null,
  primary key (permission_id, role_id)
)
engine=MyISAM
DEFAULT CHARSET=UTF8
;

-- Relationships
alter table role_permission add constraint role_permission_fk2
                               foreign key (permission_id)
                               references permission (id)
;

alter table role_permission add constraint role_permission_fk1
                               foreign key (role_id)
                                references role (id)
;
