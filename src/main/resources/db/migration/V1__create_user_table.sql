create table t_user(
    id serial PRIMARY KEY,
    external_id VARCHAR(128) UNIQUE NOT NULL,
    full_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);
