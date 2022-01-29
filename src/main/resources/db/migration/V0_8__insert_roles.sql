USE `cdas`;
START TRANSACTION;
INSERT INTO `cdas`.`account_role`(`id`, `role`)
VALUES (1, 'OWNER'),
       (2, 'SUPER_ADMIN'),
       (3, 'ADMIN'),
       (4, 'MANAGER'),
       (5, 'USER');
COMMIT;