SET SCHEMA 'public';


CREATE TABLE account_has_account_role
(
    "account_id" BIGINT NOT NULL,
    "role_id"    BIGINT NOT NULL,
    PRIMARY KEY (account_id, role_id),

    CONSTRAINT FK_role_id_235462374626374 FOREIGN KEY ("role_id") REFERENCES "account_role" ("id"),
    CONSTRAINT FK_account_id_68234572736456 FOREIGN KEY ("account_id") REFERENCES "account" ("id")
);

CREATE INDEX IF NOT EXISTS idx_role_id_28342735468
    ON account_has_account_role USING hash ("role_id");

CREATE INDEX IF NOT EXISTS idx_account_id_4950378463
    ON account_has_account_role USING hash ("account_id");
