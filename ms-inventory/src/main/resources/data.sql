DROP TABLE IF EXISTS INVENTORY_ITEM;

CREATE TABLE INVENTORY_ITEM (
    uniq_id VARCHAR(250) PRIMARY KEY,
    quantity BIGINT NOT NULL
) AS
    SELECT uniq_id, FLOOR(RAND() * 10)
    FROM CSVREAD('file:dataset/jcpenney_com-ecommerce_sample.csv');