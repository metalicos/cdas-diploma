USE `cdas`;
CREATE TABLE `cdas`.`role_has_permission`
(
    `role_id`       BIGINT NOT NULL,
    `permission_id` BIGINT NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`),
    INDEX `FK_role_id_PERMISSION_id` (`permission_id` ASC) VISIBLE,
    CONSTRAINT `FK_role_id_PERMISSION_id` FOREIGN KEY (`permission_id`) REFERENCES `cdas`.`permission` (`id`),
    CONSTRAINT `FK_permission_id_ACCOUNT_ROLE_id` FOREIGN KEY (`role_id`) REFERENCES `cdas`.`account_role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;