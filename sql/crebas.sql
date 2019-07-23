/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/22 星期一 下午 2:46:47                     */
/*==============================================================*/


drop table if exists t_customer;

drop table if exists t_dictionary;

drop table if exists t_discount;

drop table if exists t_discount_customer;

drop table if exists t_discount_source_area;

drop table if exists t_discount_ticket;

drop table if exists t_discount_tourist;

drop table if exists t_inspection;

drop table if exists t_menu;

drop table if exists t_pay_method;

drop table if exists t_role;

drop table if exists t_role_menu;

drop table if exists t_sale_order;

drop table if exists t_sale_order_item;

drop table if exists t_sale_order_type;

drop table if exists t_sale_site;

drop table if exists t_sale_window;

drop table if exists t_sale_window_ticket;

drop table if exists t_scape;

drop table if exists t_scenic_spot;

drop table if exists t_source_area;

drop table if exists t_ticket;

drop table if exists t_ticket_category;

drop table if exists t_ticket_code;

drop table if exists t_ticket_scape;

drop table if exists t_tourist;

drop table if exists t_user;

drop table if exists t_user_ticket;

/*==============================================================*/
/* Table: t_customer                                            */
/*==============================================================*/
create table t_customer
(
   id                   char(32) not null,
   customer_name        varchar(64) comment '名称',
   phone                varchar(32) comment '联系方式',
   contact              varchar(32) comment '联系人',
   address              varchar(128) comment '联系地址',
   status               int default 0 comment '状态 0：启用，1：禁用',
   parent_id            char(32) comment '上级',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_customer comment '客户信息表';

/*==============================================================*/
/* Table: t_dictionary                                          */
/*==============================================================*/
create table t_dictionary
(
   id                   char(32) not null,
   dict_name            varchar(64) comment '字典名称',
   data                 varchar(128) comment '字典值',
   parent_id            char(32) comment '上级id',
   status               int default 0 comment '状态，0启用，1禁用',
   create_id            char(32) comment '创建人id',
   create_time          datetime comment '创建时间',
   update_id            char(32) comment '更新id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_dictionary comment '字典';

/*==============================================================*/
/* Table: t_discount                                            */
/*==============================================================*/
create table t_discount
(
   id                   char(32) not null,
   disc_name            varchar(64) comment '名称',
   limit_count          tinyint default 0 comment '人数限制,0表示无人数限制',
   limit_begin          int comment '人数限制区间开始值',
   limit_end            int comment '人数限制区间结束值',
   discount_way         int default 0 comment '打折方式，0：打折（如：8折、九折），1：减免（如：减10/人，减15/人），2：按照优惠金额销售（如：直接￥50）',
   discount_scale       decimal(10,2) comment '优惠的比例',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_discount comment '优惠配置表';

/*==============================================================*/
/* Table: t_discount_customer                                   */
/*==============================================================*/
create table t_discount_customer
(
   id                   char(32) not null comment '主键',
   discount_id          char(32) comment '优惠配置id',
   customer_id          char(32) comment '客户id',
   primary key (id)
);

alter table t_discount_customer comment '优惠配置客户关联表';

/*==============================================================*/
/* Table: t_discount_source_area                                */
/*==============================================================*/
create table t_discount_source_area
(
   id                   char(32) not null comment '主键',
   discount_id          char(32) comment '优惠配置id',
   source_area_id       char(32) comment '客源地id',
   primary key (id)
);

alter table t_discount_source_area comment '优惠配置客源地关联表';

/*==============================================================*/
/* Table: t_discount_ticket                                     */
/*==============================================================*/
create table t_discount_ticket
(
   id                   char(32) not null comment '主键',
   discount_id          char(32) comment '优惠配置id',
   ticket_id            char(32) comment '窗口id',
   primary key (id)
);

alter table t_discount_ticket comment '优惠配置门票关联表';

/*==============================================================*/
/* Table: t_discount_tourist                                    */
/*==============================================================*/
create table t_discount_tourist
(
   id                   char(32) not null comment '主键',
   discount_id          char(32) comment '优惠配置id',
   tourist_id           char(32) comment '游客类型id',
   primary key (id)
);

alter table t_discount_tourist comment '优惠配置游客类型关联表';

/*==============================================================*/
/* Table: t_inspection                                          */
/*==============================================================*/
create table t_inspection
(
   id                   char(32) not null,
   inspect_name         varchar(64) comment '设备名称',
   scenic_spot_id       char(32) comment '所属景点id',
   ip                   varchar(32) comment '销售窗口ip',
   description          varchar(512) comment '描述',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_inspection comment '检票设备信息表';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   id                   char(32) not null,
   menu_name            varchar(64) comment '菜单名称',
   icon                 varchar(128) comment '菜单图标',
   url                  varchar(512) comment '菜单url',
   sort                 int default 0 comment '排序',
   category             varchar(16) comment '菜单类型',
   status               int comment '状态',
   parent_id            char(32) comment '所属父类ID',
   create_id            char(32) comment '创建者id',
   update_id            char(32) comment '更新者ID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_menu comment '菜单表';

/*==============================================================*/
/* Table: t_pay_method                                          */
/*==============================================================*/
create table t_pay_method
(
   id                   char(32) not null,
   pay_name             varchar(64) comment '名称',
   sort                 int default 0 comment '排序',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_pay_method comment '支付方式表';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   char(32) not null,
   role_name            varchar(64) comment '角色名称',
   description          varchar(256) comment '角色描述',
   status               int default 0 comment '状态,0启用，1禁用',
   create_id            char(32) comment '创建用户ID',
   update_id            char(32) comment '更新用户id',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_role comment '角色表';

/*==============================================================*/
/* Table: t_role_menu                                           */
/*==============================================================*/
create table t_role_menu
(
   id                   char(32) not null,
   role_id              char(32) comment '角色ID',
   menu_id              char(32) comment '菜单id',
   primary key (id)
);

alter table t_role_menu comment '角色菜单关联表';

/*==============================================================*/
/* Table: t_sale_order                                          */
/*==============================================================*/
create table t_sale_order
(
   id                   char(32) not null comment '主键',
   customer_id          char(32) comment '客户id',
   customer_name        varchar(64) comment '客户名称',
   pay_method           varchar(32) comment '支付方式',
   source_area_id       char(32) comment '客源地id',
   source_area_name     varchar(64) comment '客源地名称',
   sale_site_id         char(32) comment '销售站点id',
   sale_site_name       varchar(64) comment '销售站点名称',
   sale_window_id       char(32) comment '销售窗口id',
   sale_window_name     varchar(64) comment '销售窗口名称',
   create_id            char(32) comment '售票员id',
   create_name          varchar(64) comment '销售名称',
   create_time          datetime comment '销售时间',
   update_id            char(32) comment '更新用户id',
   update_time          datetime comment '更新时间',
   state                int comment '订单状态枚举',
   total_amount         decimal(10,2) default 0.00 comment '总金额',
   ticket_count         int default 0 comment '总票数',
   person_count         int default 0 comment '总人数',
   primary key (id)
);

alter table t_sale_order comment '销售订表';

/*==============================================================*/
/* Table: t_sale_order_item                                     */
/*==============================================================*/
create table t_sale_order_item
(
   id                   char(32) not null,
   sale_order_id        char(32) comment '关联订单号',
   ticket_id            char(32) comment '票类id',
   ticket_name          varchar(64) comment '票名称',
   ticket_print_price   decimal(10,2) comment '门票打印价格',
   tourist_id           char(32) comment '游客id',
   tourist_name         varchar(64) comment '游客名称',
   discount_id          char(32) comment '折扣id',
   discount_name        varchar(64) comment '折扣名称',
   bar_code             varchar(24) comment '条码号',
   price                decimal(10,2) comment '单价',
   person_count         int default 0 comment '人数',
   ticket_count         int default 1 comment '票数',
   amount               decimal(10,2) default 0.00 comment '金额小计',
   begin_date           date comment '有效期开始时间',
   end_date             date comment '有效期结束时间',
   create_id            char(32) comment '创建用户id',
   create_time          datetime comment '创建时间',
   update_id            datetime comment '更新用户id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_sale_order_item comment '销售订单明细项表';

/*==============================================================*/
/* Table: t_sale_order_type                                     */
/*==============================================================*/
create table t_sale_order_type
(
   id                   char(32) not null,
   ticket_id            char(32) comment '票类id',
   ticket_name          varchar(64) comment '票名称',
   ticket_physical      int default 0 comment '门票介质，0：打印条码票，1：ic卡票，2：身份证票',
   ticket_print_price   decimal(10,2) comment '票面打印价',
   tourist_id           char(32) comment '游客id',
   tourist_name         varchar(64) comment '游客名称',
   discount_id          char(32) comment '折扣id',
   discount_name        varchar(64) comment '折扣名称',
   price                decimal(10,2) comment '单价',
   person_count         int default 0 comment '人数',
   ticket_count         int default 0 comment '票数',
   amount               decimal(10,2) default 0.00 comment '金额小计',
   begin_date           date comment '有效期开始时间',
   end_date             date comment '有效期结束时间',
   create_id            char(32) comment '创建用户id',
   create_time          datetime comment '创建时间',
   update_id            char(32) comment '更新用户id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_sale_order_type comment '销售订单类型';

/*==============================================================*/
/* Table: t_sale_site                                           */
/*==============================================================*/
create table t_sale_site
(
   id                   char(32) not null,
   site_name            varchar(64) comment '站点名称',
   scenic_spot_id       char(32) comment '所属景区id',
   description          varchar(512) comment '描述',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_sale_site comment '销售站点信息表';

/*==============================================================*/
/* Table: t_sale_window                                         */
/*==============================================================*/
create table t_sale_window
(
   id                   char(32) not null,
   window_name          varchar(64) comment '窗口名称',
   sale_site_id         char(32) comment '所属销售站点id',
   window_ip            varchar(32) comment '销售窗口ip',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_sale_window comment '销售窗口信息表';

/*==============================================================*/
/* Table: t_sale_window_ticket                                  */
/*==============================================================*/
create table t_sale_window_ticket
(
   id                   char(32) not null comment '主键',
   sale_window_id       char(32) comment '销售窗口id',
   ticket_id            char(32) comment '窗口id',
   primary key (id)
);

alter table t_sale_window_ticket comment '销售窗口门票关联表';

/*==============================================================*/
/* Table: t_scape                                               */
/*==============================================================*/
create table t_scape
(
   id                   char(32) not null,
   scape_name           varchar(64) comment '景点名称',
   scenic_spot_id       char(32) comment '所属景区',
   description          varchar(512) comment '描述',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_scape comment '景点信息表';

/*==============================================================*/
/* Table: t_scenic_spot                                         */
/*==============================================================*/
create table t_scenic_spot
(
   id                   char(32) not null,
   spot_name            varchar(64) comment '景区名称',
   description          varchar(512) comment '描述',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_scenic_spot comment '景区信息表';

/*==============================================================*/
/* Table: t_source_area                                         */
/*==============================================================*/
create table t_source_area
(
   id                   char(32) not null,
   name                 varchar(64) comment '名称',
   parent_id            char(32) comment '上级',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_source_area comment '客源地表';

/*==============================================================*/
/* Table: t_ticket                                              */
/*==============================================================*/
create table t_ticket
(
   id                   char(32) not null,
   ticket_name          varchar(64) comment '票名称',
   scenic_spot_id       char(32) comment '所属景区',
   ticket_gategory_id   char(32) comment '门票类型id',
   sale_price           decimal(10,2) default 0.00 comment '线下窗口售价',
   print_price          decimal(10,2) default 0.00 comment '票面打印价',
   network_price        decimal(10,2) comment '网络售价',
   physical             int default 0 comment '门票介质，0：打印条码票，1：ic卡票，2：身份证票',
   deadline             int default 1 comment '门票有效期限',
   deadline_unid        varchar(4) comment '期限单位：天、月、年',
   print_method         int default 0 comment '出票方式; 0：一人一票，1：一票多人',
   begin_date           date comment '参售开始日期',
   end_date             date comment '参售结束日期',
   status               int default 0 comment '状态，0启用，1禁用',
   print_template       varchar(128) comment '打印模板',
   sort                 int default 0 comment '排序',
   description          varchar(512) comment '描述',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_ticket comment '门票信息表';

/*==============================================================*/
/* Table: t_ticket_category                                     */
/*==============================================================*/
create table t_ticket_category
(
   id                   char(32) not null,
   category_name        varchar(64) comment '名称',
   description          varchar(512) comment '描述',
   sort                 int default 0 comment '排序',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_ticket_category comment '门票分类表';

/*==============================================================*/
/* Table: t_ticket_code                                         */
/*==============================================================*/
create table t_ticket_code
(
   id                   char(32) not null,
   sale_order_id        char(32) comment '销售订单id',
   ticket_id            char(32) comment '票类id',
   ticket_name          varchar(64) comment '票类名称',
   bar_code             varchar(24) comment '条码号',
   person_count         int default 0 comment '人数',
   tourist_name         varchar(64) comment '游客类型名称',
   customer_name        varchar(64) comment '客户名称',
   begin_date           date comment '有效期起始日期',
   end_date             date comment '有效期截止日期',
   state                int default 0 comment '状态枚举',
   create_id            char(32) comment '创建用户id',
   create_time          datetime comment '创建时间',
   update_id            char(32) comment '更新用户id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_ticket_code comment '票码表';

/*==============================================================*/
/* Table: t_ticket_scape                                        */
/*==============================================================*/
create table t_ticket_scape
(
   id                   char(32) not null,
   ticket_id            char(32) comment '票类id',
   scape_id             char(32) comment '景点id',
   all_in               int comment '总可进次数',
   day_in               int comment '日可进次数',
   create_id            char(32) comment '创建人id',
   create_time          datetime,
   update_id            char(32) comment '更新用户id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_ticket_scape comment '门票景点可进次数配置';

/*==============================================================*/
/* Table: t_tourist                                             */
/*==============================================================*/
create table t_tourist
(
   id                   char(32) not null,
   tourist_name         varchar(64) comment '名称',
   sort                 int default 0 comment '排序',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32),
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_tourist comment '游客类型表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   char(32) not null comment '主键',
   user_name            varchar(64) comment '用户名',
   real_name            varchar(64) comment '真实姓名',
   password             varchar(32) comment '密码',
   phone                varchar(16) comment '手机号码',
   address              varchar(128) comment '地址',
   status               int default 0 comment '状态；0启用，1禁用',
   create_id            char(32),
   create_time          datetime comment '创建时间',
   update_id            char(32) comment '更新账户id',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table t_user comment '账户表';

/*==============================================================*/
/* Table: t_user_ticket                                         */
/*==============================================================*/
create table t_user_ticket
(
   id                   char(32) not null comment '主键',
   user_id              char(32) comment '销售窗口id',
   ticket_id            char(32) comment '窗口id',
   primary key (id)
);

alter table t_user_ticket comment '账户门票关联表';

