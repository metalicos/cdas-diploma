SET SCHEMA 'public';


START TRANSACTION;
INSERT INTO "account_role" ("id", "role")
VALUES (1, 'OWNER'),
       (2, 'SUPER_ADMIN'),
       (3, 'ADMIN'),
       (4, 'MANAGER'),
       (5, 'USER'),
       (6, 'SECURITY_DIRECTOR');
COMMIT;