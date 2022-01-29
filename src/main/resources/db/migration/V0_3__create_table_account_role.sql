USE `cdas`;
CREATE TABLE `cdas`.`account_role`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `role`              VARCHAR(200) NOT NULL UNIQUE,
    `created_timestamp` DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_role` (`role` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;