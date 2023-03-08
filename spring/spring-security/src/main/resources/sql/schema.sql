CREATE TABLE IF NOT EXISTS users
(
    user_name   character varying(255) NOT NULL,
    first_name  character varying(255),
    last_name   character varying(255),
    full_name   character varying(255),
    email       character varying(255),
    password    character varying(255),
    CONSTRAINT pk_user PRIMARY KEY (user_name)
);

CREATE TABLE IF NOT EXISTS role
(
    role_id     character varying(50),
    role_name   character varying(255),
    description character varying(500),
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_name character varying(255) NOT NULL,
    role_id   character varying(50)  NOT NULL,

    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_name FOREIGN KEY (user_name) REFERENCES users (user_name) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS persistent_logins
(
    username  character varying(64) NOT NULL,
    series    character varying(64) NOT NULL,
    token     character varying(64) NOT NULL,
    last_used timestamp,
    CONSTRAINT pk_series PRIMARY KEY (series)
);

INSERT INTO role VALUES ('ADMIN','admin',null);
INSERT INTO role VALUES ('GUEST','guest',null);