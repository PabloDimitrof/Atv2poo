CREATE TABLE account (
    id varchar(36) NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    hash_password varchar(255) NOT NULL,
    created_at timestamp NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
);