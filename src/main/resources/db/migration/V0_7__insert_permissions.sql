USE `cdas`;
START TRANSACTION;
INSERT INTO `cdas`.`permission` (id, name, value)
VALUES (1, 'Read All', 'r_all'),
       (2, 'Write All', 'w_all'),
       (3, 'Update All', 'u_all'),
       (4, 'Delete All', 'd_all'),

       (5, 'Read Permissions', 'r_permissions'),
       (6, 'Write Permissions', 'w_permissions'),
       (7, 'Update Permissions', 'u_permissions'),
       (8, 'Delete Permissions', 'd_permissions'),

       (9, 'Read Accounts', 'r_accounts'),
       (10, 'Write Accounts', 'w_accounts'),
       (11, 'Update Accounts', 'u_accounts'),
       (12, 'Delete Accounts', 'd_accounts'),

       (13, 'Read Admins', 'r_admins'),
       (14, 'Write Admins', 'w_admins'),
       (15, 'Update Admins', 'u_admins'),
       (16, 'Delete Admins', 'd_admins'),

       (17, 'Read Super Admins', 'r_super_admins'),
       (18, 'Write Super Admins', 'w_super_admins'),
       (19, 'Update Super Admins', 'u_super_admins'),
       (20, 'Delete Super Admins', 'd_super_admins'),

       (21, 'Read Self', 'r_self'),
       (22, 'Write Self', 'w_self'),
       (23, 'Update Self', 'u_self'),
       (24, 'Delete Self', 'd_self'),

       (25, 'Read Account Details', 'r_account_details'),
       (26, 'Add Account Details', 'w_account_details'),
       (27, 'Update Account Details', 'u_account_details'),
       (28, 'Delete Account Details', 'd_account_details'),

       (29, 'Read Role', 'r_role'),
       (30, 'Write Role', 'w_role'),
       (31, 'Update Role', 'u_role'),
       (32, 'Delete Role', 'd_role'),

       (33, 'Read Account Role', 'r_account_role'),
       (34, 'Add Account Role', 'w_account_role'),
       (35, 'Update Account Role', 'u_account_role'),
       (36, 'Delete Account Role', 'd_account_role'),

       (37, 'Read Device Metadata', 'r_device_metadata'),
       (38, 'Write Device Metadata', 'w_device_metadata'),
       (39, 'Update Device Metadata', 'u_device_metadata'),
       (40, 'Delete Device Metadata', 'd_device_metadata');
COMMIT;
