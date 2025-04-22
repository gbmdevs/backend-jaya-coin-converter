CREATE TABLE "USERS_HIST_CURRCY_CONVRT" (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    currency_origin VARCHAR(10) NOT NULL,
    currency_origin_value NUMERIC(10,2) DEFAULT 0,
    currency_destiny VARCHAR(10) NOT NULL,
    currency_destiny_value NUMERIC(10,2) DEFAULT 0,
    tax_conversion NUMERIC(10,5) DEFAULT 0,
    operation_date_time TIMESTAMP DEFAULT NULL,
    CONSTRAINT fk_user_hist_currcy_id FOREIGN KEY(user_id) REFERENCES "USERS"(id)
)