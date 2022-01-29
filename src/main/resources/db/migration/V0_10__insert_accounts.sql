USE `cdas`;

START TRANSACTION;
INSERT INTO `cdas`.`account`
(`id`, `first_name`, `last_name`, `patronymic`, `password`, `username`, `created_timestamp`,
 `is_credentials_non_expired`, `is_enabled`, `is_non_expired`, `is_non_locked`)
VALUES (1, 'Ostap', 'Komplikevych', 'Yaroslavovych',
        '$2a$13$KzVoeYOJftXgz5ManFeFK.rSBjB56EZE0l/hpn7WS.n/xxvYjzT/a', -- Qwert1234
        'ostap.ja@gmail.com', '2021-08-15 16:52:00', 1, 1, 1, 1),
       (2, 'Ostap', 'Komplikevych', 'Yaroslavovych',
        '$2a$13$KzVoeYOJftXgz5ManFeFK.rSBjB56EZE0l/hpn7WS.n/xxvYjzT/a', -- Qwert1234
        'user@gmail.com', '2021-08-15 16:52:00', 1, 1, 1, 1);
COMMIT;
