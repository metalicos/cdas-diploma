USE `cdas`;
CREATE TABLE `cdas`.`account_detail`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `account_id`        BIGINT       NOT NULL,
    `gender`            VARCHAR(255) NULL DEFAULT NULL,
    `birth_date`        DATE         NULL DEFAULT NULL,
    `created_timestamp` DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_account_id` (account_id ASC) VISIBLE,
    CONSTRAINT `FK_ACCOUNT_DETAILS_account_id_ACCOUNT_id` FOREIGN KEY (`account_id`) REFERENCES `cdas`.`account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;