drop table if exists `chocolate` CASCADE;
CREATE TABLE chocolate (
    id BIGINT AUTO_INCREMENT,
    brand VARCHAR(255),
    name VARCHAR(255),
    sugar_content INTEGER NOT NULL,
    tastiness INTEGER NOT NULL,
    texture VARCHAR(255),
    type VARCHAR(255),
    PRIMARY KEY (id)
);