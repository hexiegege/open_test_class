create table USER (
   id varchar(32) PRIMARY KEY NOT NULL,
  authority varchar(20)  NOT NULL,
  birth_date date NULL DEFAULT NULL,
  name varchar(20),
  cell_phone_no varchar(11)  NULL DEFAULT NULL,
  sex varchar(2)  NOT NULL,
  created_time datetime(0) NOT NULL,
  last_login_time datetime(0) NULL DEFAULT NULL,
  memo varchar(255)  NULL DEFAULT NULL,
  nickname varchar(10)  NULL DEFAULT NULL,
  password varchar(100)  NULL DEFAULT NULL,
  significance_tag bit(1) NOT NULL
);

