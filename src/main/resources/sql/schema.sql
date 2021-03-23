-- ----------------------------
-- Table structure for tools
-- ----------------------------
DROP TABLE IF EXISTS `oktools`;
CREATE TABLE `oktools`
(
    "path"        varchar(255) PRIMARY KEY,
    "title"       varchar(255) NOT NULL,
    "icon"        varchar(255),
    "category"    int          NOT NULL DEFAULT 0,
    "usage_count" int          NOT NULL DEFAULT 0
);
