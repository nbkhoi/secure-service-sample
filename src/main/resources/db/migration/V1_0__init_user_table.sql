CREATE TABLE roles (
    id varchar(36) PRIMARY KEY,
    role varchar(255) NOT NULL,
);

CREATE TABLE role_authorities (
    role_id varchar(36) NOT NULL,
    authority varchar(255) NOT NULL,
    PRIMARY KEY (role_id, authority),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE user_profiles (
    username varchar(36) NOT NULL,
    email varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    middle_name varchar(255),
    last_name varchar(255) NOT NULL,
    citizen_id varchar(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE users_roles (
    user_id varchar(36) NOT NULL,
    role_id varchar(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles (role) VALUES ('ROLE_USER');