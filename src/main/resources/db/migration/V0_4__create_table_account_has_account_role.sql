USE `cdas`;

CREATE TABLE `cdas`.`account_has_account_role`
(
    `account_id` BIGINT NOT NULL,
    `role_id`    BIGINT NOT NULL,
    PRIMARY KEY (`account_id`, `role_id`),
    INDEX `FK_role_id_ACCOUNT_ROLE_id` (`role_id` ASC) VISIBLE,
    CONSTRAINT `FK_role_id_ACCOUNT_ROLE_id` FOREIGN KEY (`role_id`) REFERENCES `cdas`.`account_role` (`id`),
    CONSTRAINT `FK_account_id_ACCOUNT_id` FOREIGN KEY (`account_id`) REFERENCES `cdas`.`account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;