CREATE TABLE manufacturers (
id UUID PRIMARY KEY,
name varchar(50)
);


CREATE TABLE products (
	id UUID PRIMARY KEY,
	name varchar(50),
	price numeric,
	manufacturer_id UUID references manufacturers
);

CREATE TABLE roles (
id UUID PRIMARY KEY,
name varchar(50)
);

ALTER TABLE roles
	ALTER COLUMN id SET DEFAULT gen_random_uuid ()

CREATE TABLE users (
	id UUID PRIMARY KEY,
	email varchar(50),
	password varchar(50),
	first_name varchar(50),
	last_name varchar(50)
);

ALTER TABLE users
	ALTER COLUMN id SET DEFAULT gen_random_uuid ()

CREATE TABLE users_roles (
	id UUID PRIMARY KEY,
	user_id UUID REFERENCES users,
	role_id UUID REFERENCES roles
);

ALTER TABLE users_roles
	ALTER COLUMN id SET DEFAULT gen_random_uuid ()

ALTER TABLE users
    ALTER COLUMN password TYPE varchar(200)