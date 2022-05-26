create table t_seed(
    id serial PRIMARY KEY,
    external_id VARCHAR(128) UNIQUE NOT NULL,
    name VARCHAR NOT NULL,
    manufacturer VARCHAR NOT NULL,
    manufactured_at DATE NOT NULL,
    expires_in DATE NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    created_by integer NOT NULL REFERENCES t_user(id)
);
