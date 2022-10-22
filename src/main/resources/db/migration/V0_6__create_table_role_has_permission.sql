SET SCHEMA 'public';

CREATE TABLE role_has_permission
(
    "role_id"       BIGINT NOT NULL,
    "permission_id" BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT FK_role_id_93246867234 FOREIGN KEY ("permission_id") REFERENCES "permission" ("id"),
    CONSTRAINT FK_permission_id_968250047 FOREIGN KEY ("role_id") REFERENCES "account_role" ("id")
);

CREATE INDEX IF NOT EXISTS idx_role_id_9423953452126 ON role_has_permission USING hash ("role_id");

CREATE INDEX IF NOT EXISTS idx_permission_id_9423952342126 ON role_has_permission USING hash ("permission_id");