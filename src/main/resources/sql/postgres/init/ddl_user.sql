CREATE TABLE TEAM(
        id serial4 PRIMARY KEY,
        name varchar(100) ,
        leader_uid varchar(500) ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE TEAM_MAPPING(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        team_id int4 ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE REPORT(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        report_id int4 ,
        date timestamp ,
        status varchar(50) ,
        sub_date timestamp ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE JOURNAL(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        report_id int4 ,
        date timestamp ,
        place_name varchar(100) ,
        description varchar(1000) ,
        memo varchar(1000) ,
        status varchar(50) ,
        sub_date timestamp ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE REFUND(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        report_id int4 ,
        place_name varchar(100) ,
        reason varchar(1000) ,
        memo varchar(1000) ,
        quantity int4 ,
        product_id int4 ,
        unit_id int4 ,
        sub_date timestamp ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE EXPENSE(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        report_id int4 ,
        amount numeric ,
        type varchar(1000) ,
        description varchar(1000) ,
        status varchar(50) ,
        sub_date timestamp ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);
CREATE TABLE NOTE(
        id serial4 PRIMARY KEY,
        uid varchar(500) ,
        report_id int4 ,
        status varchar(50) ,
        description varchar(1000) ,
        sub_date timestamp ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);