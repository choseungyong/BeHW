CREATE TABLE IF NOT EXISTS category (
    id          BIGINT       AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    color       VARCHAR(7)   NOT NULL,
    image_url   VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT uk_category_name UNIQUE (name)
);

CREATE TABLE products (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    price INT,
    image_url VARCHAR(1000),
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id)
    REFERENCES category(id)
);

CREATE TABLE member (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE wish (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT fk_wish_member  FOREIGN KEY (member_id)  REFERENCES member(id),
    CONSTRAINT fk_wish_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS options (
    id           BIGINT       AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50)  NOT NULL,
    quantity     INT          NOT NULL,
    product_id   BIGINT       NOT NULL,
    CONSTRAINT fk_option_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);