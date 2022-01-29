USE `cdas`;
CREATE TABLE `cdas`.`permission`
(
    `id`                BIGINT       NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(300) NOT NULL UNIQUE,
    `value`             VARCHAR(300) NOT NULL UNIQUE,
    `created_timestamp` DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_name` (`name` ASC) VISIBLE,
    UNIQUE INDEX `UK_value` (`name` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;