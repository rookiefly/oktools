-- ----------------------------
-- Table structure for tools
-- ----------------------------
DROP TABLE IF EXISTS `oktools`;
CREATE TABLE `oktools`
(
    `path`        varchar(255) PRIMARY KEY,
    `title`       varchar(255) NOT NULL,
    `icon`        varchar(255),
    `category`    int          NOT NULL DEFAULT 0,
    `usage_count` int          NOT NULL DEFAULT 0
);

CREATE TABLE clipboard
(
    `id`   BIGINT PRIMARY KEY AUTO_INCREMENT,
    `hash` VARCHAR(60) NOT NULL,
    `content` CLOB NOT NULL,
    UNIQUE KEY hash_unique (`hash`)
);