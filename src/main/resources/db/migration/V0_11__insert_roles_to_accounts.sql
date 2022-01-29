USE `cdas`;

START TRANSACTION;
INSERT INTO `cdas`.`account_has_account_role` (`account_id`, `role_id`)
VALUES (1, 1),
       (2, 5);
COMMIT;