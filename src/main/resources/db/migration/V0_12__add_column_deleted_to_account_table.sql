USE `cdas`;
ALTER TABLE account
    ADD COLUMN `deleted` BIT(1) DEFAULT 0 AFTER `id`;