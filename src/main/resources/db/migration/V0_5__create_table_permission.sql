SET SCHEMA 'public';

CREATE TABLE permission
(
    "id"                BIGSERIAL,
    "name"              TEXT NOT NULL UNIQUE,
    "value"             TEXT NOT NULL UNIQUE,
    "created_timestamp" TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY ("id"),
    CONSTRAINT UK_name UNIQUE ("name"),
    CONSTRAINT UK_value UNIQUE ("value")
);

CREATE INDEX IF NOT EXISTS idx_name_2120938912 ON permission USING hash ("name");

CREATE INDEX IF NOT EXISTS idx_value_49879305234 ON permission USING hash ("value");

CREATE INDEX IF NOT EXISTS idx_id_49879305644 ON permission USING hash ("id");