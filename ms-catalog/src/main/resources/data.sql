DROP TABLE IF EXISTS CATALOG_ITEM;

CREATE TABLE CATALOG_ITEM (
    uniq_id VARCHAR(250) PRIMARY KEY ,
    sku VARCHAR(250),
    name_title VARCHAR(250),
    description TEXT,
    list_price VARCHAR(250),
    sale_price VARCHAR(250),
    category VARCHAR(250),
    category_tree VARCHAR(250),
    average_rating VARCHAR(250),
    url TEXT,
    image_urls TEXT,
    brand VARCHAR(250),
    total_number_reviews VARCHAR(250),
    reviews TEXT,
) AS
    SELECT uniq_id, sku, name_title, description, list_price, sale_price,
           category, category_tree, average_product_rating, product_url,
           product_image_urls, brand, total_number_reviews, Reviews
    FROM CSVREAD('file:dataset/jcpenney_com-ecommerce_sample.csv');
