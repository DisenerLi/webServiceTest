CREATE TABLE `product_count` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `product_code` varchar(50) DEFAULT NULL COMMENT '产品code',
    `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
    `product_num` int(11) NOT NULL DEFAULT '0' COMMENT '产品数量',
    `sale_count` int(11) DEFAULT NULL COMMENT '已销售数量',
    `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，1-是，0-否',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;