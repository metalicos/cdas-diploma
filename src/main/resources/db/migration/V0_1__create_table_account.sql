SET SCHEMA 'public';

CREATE TABLE "account"
(
    "id"                         BIGSERIAL,
    "first_name"                 TEXT   NOT NULL,
    "last_name"                  TEXT   NOT NULL,
    "patronymic"                 TEXT   NOT NULL,
    "username"                   TEXT   NOT NULL UNIQUE,
    "password"                   TEXT   NOT NULL,
    "photo"                      OID,
    "is_credentials_non_expired" BOOLEAN NULL DEFAULT false,
    "is_enabled"                 BOOLEAN NULL DEFAULT false,
    "is_non_expired"             BOOLEAN NULL DEFAULT false,
    "is_non_locked"              BOOLEAN NULL DEFAULT false,
    "created_timestamp"          TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY ("id"),
    CONSTRAINT UK_username UNIQUE ("username")
);

CREATE INDEX IF NOT EXISTS idx_id_28234535468
    ON "account" USING hash ("id");

CREATE INDEX IF NOT EXISTS idx_username_28234537868
    ON "account" USING hash ("username");

CREATE INDEX IF NOT EXISTS idx_password_28234537268
    ON "account" USING hash ("password");
