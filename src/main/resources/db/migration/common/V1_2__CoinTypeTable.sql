CREATE TABLE "CURRCY_TYPE" (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    currency VARCHAR(10) NOT NULL,
    country VARCHAR(25) NOT NULL,
    symbol varchar(5) NOT NULL
);

INSERT INTO "CURRCY_TYPE" VALUES('dd71d381-ca98-44e4-bbff-fb374fc44fb9','BRL','BRAZIL','R$');
INSERT INTO "CURRCY_TYPE" VALUES('ff858f0e-5d96-43a5-8e2c-f327de4581a5','USD','UNITED STATES - DOLAR','$');
INSERT INTO "CURRCY_TYPE" VALUES('8656347c-a317-4873-bf35-a5adbbe7402e','EUR','EURO','€');
INSERT INTO "CURRCY_TYPE" VALUES('e9ec1c5d-ccd2-4ac5-b848-a021a457bdba','JPY','JAPAN','¥');
