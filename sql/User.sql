DROP TABLE User;
CREATE TABLE User (
                         `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `name` varchar(255),
                         `password` varchar(255),
                         `email` varchar(255),
                         `createTime` DATETIME NULL DEFAULT NULL,
                         `updateTime` DATETIME NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
);