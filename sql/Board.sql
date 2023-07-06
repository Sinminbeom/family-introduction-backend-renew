DROP TABLE Board;
CREATE TABLE `Board` (
                         `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `title` LONGTEXT CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci',
                         `text` LONGTEXT CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci',
                         `createUserId` INT(10) NULL DEFAULT NULL,
                         `createTime` DATETIME NULL DEFAULT NULL,
                         `updateUserId` INT(10) NULL DEFAULT NULL,
                         `updateTime` DATETIME NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;