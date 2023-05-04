-- 指定数据库
use cmapi;

-- 接口信息
create table if not exists cmapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `request_header` text null comment '请求头',
    `response_header` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启））',
    `method` varchar(256) not null comment '请求类型',
    `user_id` bigint not null comment '创建人',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('杨越泽', 'Ius', 'www.daryl-gulgowski.io', '萧思淼', '郭修洁', 0, 'GET', 7);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('武耀杰', 'tIgj', 'www.minh-reynolds.biz', '田烨霖', '董彬', 0, 'GET', 31615);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('任天翊', 'D0PVZ', 'www.belinda-upton.com', '夏雨泽', '张乐驹', 0, 'GET', 55734066);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('潘文', 'PL', 'www.merrill-bernier.name', '张懿轩', '顾瑾瑜', 0, 'GET', 3732014);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('唐文轩', 'O7', 'www.josue-nienow.co', '黎泽洋', '张烨华', 0, 'GET', 744);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('江思源', 'RYSHb', 'www.jovan-daugherty.io', '王熠彤', '白博超', 0, 'GET', 3);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('白胤祥', '849', 'www.van-grant.io', '毛致远', '何鹏涛', 0, 'GET', 515);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('任涛', 'TTUc', 'www.georgiann-hill.info', '郭天翊', '朱浩宇', 0, 'GET', 49808414);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('熊博文', 'E7', 'www.elizebeth-rowe.info', '顾擎苍', '崔弘文', 0, 'GET', 0);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('唐思淼', 'B3', 'www.lonnie-koch.biz', '曾文轩', '陆懿轩', 0, 'GET', 38368);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('蔡鑫磊', 'kjs', 'www.maisie-brown.org', '武昊焱', '高浩轩', 0, 'GET', 82);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('曹晓啸', 'jcEIy', 'www.tyrell-streich.com', '韦文昊', '雷潇然', 0, 'GET', 58540);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('方航', 'JKNt', 'www.miquel-rogahn.info', '董雨泽', '孙修杰', 0, 'GET', 53);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('阎梓晨', 'oz8nU', 'www.isaias-ortiz.org', '熊泽洋', '崔煜城', 0, 'GET', 9367924696);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('毛煜祺', 'z01', 'www.christian-watsica.name', '洪熠彤', '魏博超', 0, 'GET', 8652412);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('黄立辉', '2PQM', 'www.beryl-schultz.info', '吴智宸', '吕绍齐', 0, 'GET', 967017);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('郑弘文', 'yv', 'www.elias-weissnat.co', '孔越彬', '朱昊然', 0, 'GET', 973);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('廖旭尧', 'zf', 'www.myrl-cole.name', '邱雨泽', '赵昊焱', 0, 'GET', 649374766);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('朱懿轩', 'ojCdN', 'www.tommye-russel.biz', '吴智辉', '蒋熠彤', 0, 'GET', 13);
insert into cmapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`) values ('史烨霖', 'H0oL4', 'www.marguerite-koelpin.name', '孔胤祥', '卢雪松', 0, 'GET', 580);