CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;

CREATE TABLE lcd_user (
  id uuid PRIMARY KEY ,
  username varchar(50) NOT NULL,
  email varchar (100) NOT NULL,
  password varchar(100) NOT NULL
);