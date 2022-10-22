SET SCHEMA 'public';

ALTER TABLE "account" ADD COLUMN "deleted" BOOLEAN DEFAULT false;