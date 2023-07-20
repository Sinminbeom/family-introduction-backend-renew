CREATE TABLE Comment
(
    id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    parentId bigint,
    boardId bigint,
    TEXT LongText,
    createUserId bigint,
    createTime DATETIME,
    updateUserId bigint,
    updateTime DATETIME,
    primary key (id)
);