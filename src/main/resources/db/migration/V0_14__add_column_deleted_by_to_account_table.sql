USE `cdas`;
ALTER TABLE account
    ADD COLUMN `deleted_by` BIGINT AFTER `created_by`;