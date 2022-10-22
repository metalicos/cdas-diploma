SET SCHEMA 'public';

CREATE TABLE account_role
(
    "id"                BIGSERIAL,
    "role"              TEXT NOT NULL UNIQUE,
    "created_timestamp" TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY ("id"),
    CONSTRAINT UK_role UNIQUE ("role")
);

CREATE INDEX IF NOT EXISTS idx_id_28214535468
    ON account_role USING hash ("id");

CREATE INDEX IF NOT EXISTS idx_role_28214535468
    ON account_role USING hash ("role");