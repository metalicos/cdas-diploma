SET SCHEMA 'public';

ALTER TABLE "account" ADD COLUMN "deleted_by" BIGINT;