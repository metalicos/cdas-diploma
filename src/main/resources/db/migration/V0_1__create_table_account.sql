USE `cdas`;

CREATE TABLE `account`
(
    `id`                         BIGINT       NOT NULL AUTO_INCREMENT,
    `first_name`                 VARCHAR(25)  NOT NULL,
    `last_name`                  VARCHAR(25)  NOT NULL,
    `patronymic`                 VARCHAR(25)  NOT NULL,
    `username`                   VARCHAR(100) NOT NULL UNIQUE,
    `password`                   VARCHAR(300) NOT NULL,
    `photo`                      LONGBLOB     NULL DEFAULT NULL,
    `is_credentials_non_expired` BIT(1)       NULL DEFAULT 0,
    `is_enabled`                 BIT(1)       NULL DEFAULT 0,
    `is_non_expired`             BIT(1)       NULL DEFAULT 0,
    `is_non_locked`              BIT(1)       NULL DEFAULT 0,
    `created_timestamp`          DATETIME(6)       DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_username` (`username` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;