DROP TABLE IF EXISTS `Calendar`;
CREATE TABLE `Calendar` (
                            `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `title` LONGTEXT CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci',
                            `description` LONGTEXT CHARACTER SET utf8mb4 COLLATE 'utf8mb4_unicode_ci',
                            `start` DATETIME NULL DEFAULT NULL,
                            `end` DATETIME NULL DEFAULT NULL,
                            `allDay` BOOLEAN NULL DEFAULT NULL,
                            `color` VARCHAR(10) NULL DEFAULT NULL,
                            `textColor` VARCHAR(10) NULL DEFAULT NULL,
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