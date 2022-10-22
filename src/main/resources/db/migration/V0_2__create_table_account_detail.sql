SET SCHEMA 'public';

CREATE TABLE "account_detail"
(
    "id"                BIGSERIAL,
    "account_id"        BIGINT NOT NULL UNIQUE,
    "gender"            TEXT,
    "birth_date"        DATE,
    "created_timestamp" TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY ("id"),
    CONSTRAINT UK_account_id UNIQUE ("account_id"),
    CONSTRAINT fk_account_details_to_account_werlwfsdf
        FOREIGN KEY (account_id) REFERENCES "account" (id)
);

CREATE INDEX IF NOT EXISTS idx_id_28234535325
    ON "account_detail" USING hash ("id");
