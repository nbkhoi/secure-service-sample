-- Users table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL
);
-- User Profile table
CREATE TABLE user_profile (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    id_number VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
-- Roles table
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT NOT NULL
);
-- User Roles table
CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
-- Permission table
CREATE TABLE permission (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    resource VARCHAR(255) NOT NULL,
    action VARCHAR(255) NOT NULL
);
-- Role Permissions table
CREATE TABLE role_permissions (
    role_id UUID NOT NULL,
    permission_id UUID NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);

-- Initial data
-- Roles
INSERT INTO roles (name, description) VALUES ('ADMIN', 'Admin role');
INSERT INTO roles (name, description) VALUES ('OPERATOR', 'Operator role');
-- Permissions
INSERT INTO permission (name, description, resource, action) VALUES ('CHECK_IN_VIEW', 'View check-ins', 'check-in', 'view');
INSERT INTO permission (name, description, resource, action) VALUES ('CHECK_IN_CREATE', 'Create check-ins', 'check-in', 'create');
INSERT INTO permission (name, description, resource, action) VALUES ('CHECK_IN_UPDATE', 'Update check-ins', 'check-in', 'update');
INSERT INTO permission (name, description, resource, action) VALUES ('CHECK_IN_DELETE', 'Delete check-ins', 'check-in', 'delete');
-- Role Permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permission WHERE name = 'CHECK_IN_VIEW'));
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permission WHERE name = 'CHECK_IN_CREATE'));
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permission WHERE name = 'CHECK_IN_UPDATE'));
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permission WHERE name = 'CHECK_IN_DELETE'));
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'OPERATOR'), (SELECT id FROM permission WHERE name = 'CHECK_IN_VIEW'));
INSERT INTO role_permissions (role_id, permission_id) VALUES ((SELECT id FROM roles WHERE name = 'OPERATOR'), (SELECT id FROM permission WHERE name = 'CHECK_IN_CREATE'));
-- Users
INSERT INTO users (id, email, phone_number) VALUES ('f9ca857c-e0d1-7063-619f-c6e3b9ad6bd4', 'khoinb@gotik.vn', '0987654321');
-- User Profiles
INSERT INTO user_profile (user_id, first_name, middle_name, last_name, date_of_birth, id_number) VALUES ('f9ca857c-e0d1-7063-619f-c6e3b9ad6bd4', 'Khoi', 'Bao', 'Nguyen', '1990-01-01', '123456789');
-- User Roles
INSERT INTO user_roles (user_id, role_id) VALUES ('f9ca857c-e0d1-7063-619f-c6e3b9ad6bd4', (SELECT id FROM roles WHERE name = 'ADMIN'));