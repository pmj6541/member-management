CREATE TABLE PRODUCT(
        id serial4 PRIMARY KEY,
        unit_id int4 ,
        ins_date timestamp DEFAULT now(),
        name varchar(100) ,
        mod_date timestamp  NULL
);

CREATE TABLE UNIT(
        id serial4 PRIMARY KEY,
        name varchar(100) ,
        ins_date timestamp DEFAULT now(),
        mod_date timestamp  NULL
);