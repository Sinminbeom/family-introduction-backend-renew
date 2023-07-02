create TABLE User
(
    id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    name varchar(255),
    password varchar(255),
    email varchar(255),
    image LONGBLOB,
    primary key (id)
);