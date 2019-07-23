/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/22 ����һ ���� 2:46:47                     */
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
   customer_name        varchar(64) comment '����',
   phone                varchar(32) comment '��ϵ��ʽ',
   contact              varchar(32) comment '��ϵ��',
   address              varchar(128) comment '��ϵ��ַ',
   status               int default 0 comment '״̬ 0�����ã�1������',
   parent_id            char(32) comment '�ϼ�',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_customer comment '�ͻ���Ϣ��';

/*==============================================================*/
/* Table: t_dictionary                                          */
/*==============================================================*/
create table t_dictionary
(
   id                   char(32) not null,
   dict_name            varchar(64) comment '�ֵ�����',
   data                 varchar(128) comment '�ֵ�ֵ',
   parent_id            char(32) comment '�ϼ�id',
   status               int default 0 comment '״̬��0���ã�1����',
   create_id            char(32) comment '������id',
   create_time          datetime comment '����ʱ��',
   update_id            char(32) comment '����id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_dictionary comment '�ֵ�';

/*==============================================================*/
/* Table: t_discount                                            */
/*==============================================================*/
create table t_discount
(
   id                   char(32) not null,
   disc_name            varchar(64) comment '����',
   limit_count          tinyint default 0 comment '��������,0��ʾ����������',
   limit_begin          int comment '�����������俪ʼֵ',
   limit_end            int comment '���������������ֵ',
   discount_way         int default 0 comment '���۷�ʽ��0�����ۣ��磺8�ۡ����ۣ���1�����⣨�磺��10/�ˣ���15/�ˣ���2�������Żݽ�����ۣ��磺ֱ�ӣ�50��',
   discount_scale       decimal(10,2) comment '�Żݵı���',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_discount comment '�Ż����ñ�';

/*==============================================================*/
/* Table: t_discount_customer                                   */
/*==============================================================*/
create table t_discount_customer
(
   id                   char(32) not null comment '����',
   discount_id          char(32) comment '�Ż�����id',
   customer_id          char(32) comment '�ͻ�id',
   primary key (id)
);

alter table t_discount_customer comment '�Ż����ÿͻ�������';

/*==============================================================*/
/* Table: t_discount_source_area                                */
/*==============================================================*/
create table t_discount_source_area
(
   id                   char(32) not null comment '����',
   discount_id          char(32) comment '�Ż�����id',
   source_area_id       char(32) comment '��Դ��id',
   primary key (id)
);

alter table t_discount_source_area comment '�Ż����ÿ�Դ�ع�����';

/*==============================================================*/
/* Table: t_discount_ticket                                     */
/*==============================================================*/
create table t_discount_ticket
(
   id                   char(32) not null comment '����',
   discount_id          char(32) comment '�Ż�����id',
   ticket_id            char(32) comment '����id',
   primary key (id)
);

alter table t_discount_ticket comment '�Ż�������Ʊ������';

/*==============================================================*/
/* Table: t_discount_tourist                                    */
/*==============================================================*/
create table t_discount_tourist
(
   id                   char(32) not null comment '����',
   discount_id          char(32) comment '�Ż�����id',
   tourist_id           char(32) comment '�ο�����id',
   primary key (id)
);

alter table t_discount_tourist comment '�Ż������ο����͹�����';

/*==============================================================*/
/* Table: t_inspection                                          */
/*==============================================================*/
create table t_inspection
(
   id                   char(32) not null,
   inspect_name         varchar(64) comment '�豸����',
   scenic_spot_id       char(32) comment '��������id',
   ip                   varchar(32) comment '���۴���ip',
   description          varchar(512) comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_inspection comment '��Ʊ�豸��Ϣ��';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   id                   char(32) not null,
   menu_name            varchar(64) comment '�˵�����',
   icon                 varchar(128) comment '�˵�ͼ��',
   url                  varchar(512) comment '�˵�url',
   sort                 int default 0 comment '����',
   category             varchar(16) comment '�˵�����',
   status               int comment '״̬',
   parent_id            char(32) comment '��������ID',
   create_id            char(32) comment '������id',
   update_id            char(32) comment '������ID',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_menu comment '�˵���';

/*==============================================================*/
/* Table: t_pay_method                                          */
/*==============================================================*/
create table t_pay_method
(
   id                   char(32) not null,
   pay_name             varchar(64) comment '����',
   sort                 int default 0 comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_pay_method comment '֧����ʽ��';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   char(32) not null,
   role_name            varchar(64) comment '��ɫ����',
   description          varchar(256) comment '��ɫ����',
   status               int default 0 comment '״̬,0���ã�1����',
   create_id            char(32) comment '�����û�ID',
   update_id            char(32) comment '�����û�id',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_role comment '��ɫ��';

/*==============================================================*/
/* Table: t_role_menu                                           */
/*==============================================================*/
create table t_role_menu
(
   id                   char(32) not null,
   role_id              char(32) comment '��ɫID',
   menu_id              char(32) comment '�˵�id',
   primary key (id)
);

alter table t_role_menu comment '��ɫ�˵�������';

/*==============================================================*/
/* Table: t_sale_order                                          */
/*==============================================================*/
create table t_sale_order
(
   id                   char(32) not null comment '����',
   customer_id          char(32) comment '�ͻ�id',
   customer_name        varchar(64) comment '�ͻ�����',
   pay_method           varchar(32) comment '֧����ʽ',
   source_area_id       char(32) comment '��Դ��id',
   source_area_name     varchar(64) comment '��Դ������',
   sale_site_id         char(32) comment '����վ��id',
   sale_site_name       varchar(64) comment '����վ������',
   sale_window_id       char(32) comment '���۴���id',
   sale_window_name     varchar(64) comment '���۴�������',
   create_id            char(32) comment '��ƱԱid',
   create_name          varchar(64) comment '��������',
   create_time          datetime comment '����ʱ��',
   update_id            char(32) comment '�����û�id',
   update_time          datetime comment '����ʱ��',
   state                int comment '����״̬ö��',
   total_amount         decimal(10,2) default 0.00 comment '�ܽ��',
   ticket_count         int default 0 comment '��Ʊ��',
   person_count         int default 0 comment '������',
   primary key (id)
);

alter table t_sale_order comment '���۶���';

/*==============================================================*/
/* Table: t_sale_order_item                                     */
/*==============================================================*/
create table t_sale_order_item
(
   id                   char(32) not null,
   sale_order_id        char(32) comment '����������',
   ticket_id            char(32) comment 'Ʊ��id',
   ticket_name          varchar(64) comment 'Ʊ����',
   ticket_print_price   decimal(10,2) comment '��Ʊ��ӡ�۸�',
   tourist_id           char(32) comment '�ο�id',
   tourist_name         varchar(64) comment '�ο�����',
   discount_id          char(32) comment '�ۿ�id',
   discount_name        varchar(64) comment '�ۿ�����',
   bar_code             varchar(24) comment '�����',
   price                decimal(10,2) comment '����',
   person_count         int default 0 comment '����',
   ticket_count         int default 1 comment 'Ʊ��',
   amount               decimal(10,2) default 0.00 comment '���С��',
   begin_date           date comment '��Ч�ڿ�ʼʱ��',
   end_date             date comment '��Ч�ڽ���ʱ��',
   create_id            char(32) comment '�����û�id',
   create_time          datetime comment '����ʱ��',
   update_id            datetime comment '�����û�id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_sale_order_item comment '���۶�����ϸ���';

/*==============================================================*/
/* Table: t_sale_order_type                                     */
/*==============================================================*/
create table t_sale_order_type
(
   id                   char(32) not null,
   ticket_id            char(32) comment 'Ʊ��id',
   ticket_name          varchar(64) comment 'Ʊ����',
   ticket_physical      int default 0 comment '��Ʊ���ʣ�0����ӡ����Ʊ��1��ic��Ʊ��2�����֤Ʊ',
   ticket_print_price   decimal(10,2) comment 'Ʊ���ӡ��',
   tourist_id           char(32) comment '�ο�id',
   tourist_name         varchar(64) comment '�ο�����',
   discount_id          char(32) comment '�ۿ�id',
   discount_name        varchar(64) comment '�ۿ�����',
   price                decimal(10,2) comment '����',
   person_count         int default 0 comment '����',
   ticket_count         int default 0 comment 'Ʊ��',
   amount               decimal(10,2) default 0.00 comment '���С��',
   begin_date           date comment '��Ч�ڿ�ʼʱ��',
   end_date             date comment '��Ч�ڽ���ʱ��',
   create_id            char(32) comment '�����û�id',
   create_time          datetime comment '����ʱ��',
   update_id            char(32) comment '�����û�id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_sale_order_type comment '���۶�������';

/*==============================================================*/
/* Table: t_sale_site                                           */
/*==============================================================*/
create table t_sale_site
(
   id                   char(32) not null,
   site_name            varchar(64) comment 'վ������',
   scenic_spot_id       char(32) comment '��������id',
   description          varchar(512) comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_sale_site comment '����վ����Ϣ��';

/*==============================================================*/
/* Table: t_sale_window                                         */
/*==============================================================*/
create table t_sale_window
(
   id                   char(32) not null,
   window_name          varchar(64) comment '��������',
   sale_site_id         char(32) comment '��������վ��id',
   window_ip            varchar(32) comment '���۴���ip',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_sale_window comment '���۴�����Ϣ��';

/*==============================================================*/
/* Table: t_sale_window_ticket                                  */
/*==============================================================*/
create table t_sale_window_ticket
(
   id                   char(32) not null comment '����',
   sale_window_id       char(32) comment '���۴���id',
   ticket_id            char(32) comment '����id',
   primary key (id)
);

alter table t_sale_window_ticket comment '���۴�����Ʊ������';

/*==============================================================*/
/* Table: t_scape                                               */
/*==============================================================*/
create table t_scape
(
   id                   char(32) not null,
   scape_name           varchar(64) comment '��������',
   scenic_spot_id       char(32) comment '��������',
   description          varchar(512) comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_scape comment '������Ϣ��';

/*==============================================================*/
/* Table: t_scenic_spot                                         */
/*==============================================================*/
create table t_scenic_spot
(
   id                   char(32) not null,
   spot_name            varchar(64) comment '��������',
   description          varchar(512) comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_scenic_spot comment '������Ϣ��';

/*==============================================================*/
/* Table: t_source_area                                         */
/*==============================================================*/
create table t_source_area
(
   id                   char(32) not null,
   name                 varchar(64) comment '����',
   parent_id            char(32) comment '�ϼ�',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_source_area comment '��Դ�ر�';

/*==============================================================*/
/* Table: t_ticket                                              */
/*==============================================================*/
create table t_ticket
(
   id                   char(32) not null,
   ticket_name          varchar(64) comment 'Ʊ����',
   scenic_spot_id       char(32) comment '��������',
   ticket_gategory_id   char(32) comment '��Ʊ����id',
   sale_price           decimal(10,2) default 0.00 comment '���´����ۼ�',
   print_price          decimal(10,2) default 0.00 comment 'Ʊ���ӡ��',
   network_price        decimal(10,2) comment '�����ۼ�',
   physical             int default 0 comment '��Ʊ���ʣ�0����ӡ����Ʊ��1��ic��Ʊ��2�����֤Ʊ',
   deadline             int default 1 comment '��Ʊ��Ч����',
   deadline_unid        varchar(4) comment '���޵�λ���졢�¡���',
   print_method         int default 0 comment '��Ʊ��ʽ; 0��һ��һƱ��1��һƱ����',
   begin_date           date comment '���ۿ�ʼ����',
   end_date             date comment '���۽�������',
   status               int default 0 comment '״̬��0���ã�1����',
   print_template       varchar(128) comment '��ӡģ��',
   sort                 int default 0 comment '����',
   description          varchar(512) comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_ticket comment '��Ʊ��Ϣ��';

/*==============================================================*/
/* Table: t_ticket_category                                     */
/*==============================================================*/
create table t_ticket_category
(
   id                   char(32) not null,
   category_name        varchar(64) comment '����',
   description          varchar(512) comment '����',
   sort                 int default 0 comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_ticket_category comment '��Ʊ�����';

/*==============================================================*/
/* Table: t_ticket_code                                         */
/*==============================================================*/
create table t_ticket_code
(
   id                   char(32) not null,
   sale_order_id        char(32) comment '���۶���id',
   ticket_id            char(32) comment 'Ʊ��id',
   ticket_name          varchar(64) comment 'Ʊ������',
   bar_code             varchar(24) comment '�����',
   person_count         int default 0 comment '����',
   tourist_name         varchar(64) comment '�ο���������',
   customer_name        varchar(64) comment '�ͻ�����',
   begin_date           date comment '��Ч����ʼ����',
   end_date             date comment '��Ч�ڽ�ֹ����',
   state                int default 0 comment '״̬ö��',
   create_id            char(32) comment '�����û�id',
   create_time          datetime comment '����ʱ��',
   update_id            char(32) comment '�����û�id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_ticket_code comment 'Ʊ���';

/*==============================================================*/
/* Table: t_ticket_scape                                        */
/*==============================================================*/
create table t_ticket_scape
(
   id                   char(32) not null,
   ticket_id            char(32) comment 'Ʊ��id',
   scape_id             char(32) comment '����id',
   all_in               int comment '�ܿɽ�����',
   day_in               int comment '�տɽ�����',
   create_id            char(32) comment '������id',
   create_time          datetime,
   update_id            char(32) comment '�����û�id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_ticket_scape comment '��Ʊ����ɽ���������';

/*==============================================================*/
/* Table: t_tourist                                             */
/*==============================================================*/
create table t_tourist
(
   id                   char(32) not null,
   tourist_name         varchar(64) comment '����',
   sort                 int default 0 comment '����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32),
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_tourist comment '�ο����ͱ�';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   char(32) not null comment '����',
   user_name            varchar(64) comment '�û���',
   real_name            varchar(64) comment '��ʵ����',
   password             varchar(32) comment '����',
   phone                varchar(16) comment '�ֻ�����',
   address              varchar(128) comment '��ַ',
   status               int default 0 comment '״̬��0���ã�1����',
   create_id            char(32),
   create_time          datetime comment '����ʱ��',
   update_id            char(32) comment '�����˻�id',
   update_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table t_user comment '�˻���';

/*==============================================================*/
/* Table: t_user_ticket                                         */
/*==============================================================*/
create table t_user_ticket
(
   id                   char(32) not null comment '����',
   user_id              char(32) comment '���۴���id',
   ticket_id            char(32) comment '����id',
   primary key (id)
);

alter table t_user_ticket comment '�˻���Ʊ������';

