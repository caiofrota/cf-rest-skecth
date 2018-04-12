insert into cf_rest_user (created_by, created_on, updated_by, updated_on, email, enabled, name, password)
                  values (1, now(), 1, now(), 'admin@cftechsol.com', 1, 'Admin', '$2a$10$Lmz/VVefxOTeZmUK8/Tg4uHVmh0nmHz1kaTg0gMkxkkYo/rJcXBwi');

insert into cf_rest_role (created_by, created_on, updated_by, updated_on, cod)
                  values (1, now(), 1, now(), 'ADMIN');

insert into cf_rest_permission (created_by, created_on, updated_by, updated_on, cod)
                        values (1, now(), 1, now(), 'ADMIN');

insert into cf_rest_user_role (created_by, created_on, updated_by, updated_on, user_id, role_id)
                       values (1, now(), 1, now(), 1, 1);

insert into cf_rest_role_permission (created_by, created_on, updated_by, updated_on, role_id, permission_id)
                             values (1, now(), 1, now(), 1, 1);
