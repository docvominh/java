CREATE SEQUENCE generate_value
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 100000000;


CREATE TABLE IF NOT EXISTS employee
(
    employee_id integer NOT NULL,
    first_name  character varying(255),
    last_name   character varying(255),
    id_num      character varying(255),
    country     character varying(255),
    CONSTRAINT pk_employee PRIMARY KEY (employee_id)
);

CREATE TABLE IF NOT EXISTS customer
(
    customer_id integer NOT NULL,
    first_name  character varying(255),
    last_name   character varying(255),
    id_num      character varying(255),
    country     character varying(255),
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS employee_customer
(
    employee_id integer NOT NULL,
    customer_id integer NOT NULL,

    CONSTRAINT pk_employee_customer PRIMARY KEY (employee_id, customer_id),
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS account
(
    account_id   integer NOT NULL,
    customer_id  integer NOT NULL,
    account_type character varying(255),
    balance      bigint,
    CONSTRAINT pk_account PRIMARY KEY (account_id),
    CONSTRAINT fk_account_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE IF NOT EXISTS transaction_type
(
    type_id     integer NOT NULL,
    name        character varying(255),
    description character varying(255),
    CONSTRAINT pk_transaction_type PRIMARY KEY (type_id)
);

INSERT INTO transaction_type
VALUES (1, 'Withdraw', null);
INSERT INTO transaction_type
VALUES (2, 'Deposit', null);
INSERT INTO transaction_type
VALUES (3, 'Transfer', null);



CREATE TABLE IF NOT EXISTS transaction
(
    transaction_id         bigint  NOT NULL,
    source_account_id      integer NOT NULL,
    destination_account_id integer NULL,
    transaction_type       character varying(255),
    amount                 bigint,
    status                 character varying(255),
    create_date            timestamp,
    delete_date            timestamp,
    CONSTRAINT pk_transaction PRIMARY KEY (transaction_id),
    CONSTRAINT fk_source_account_id FOREIGN KEY (source_account_id) REFERENCES account (account_id),
    CONSTRAINT fk_destination_account_id FOREIGN KEY (destination_account_id) REFERENCES account (account_id)
);


CREATE TABLE IF NOT EXISTS transaction_detail
(
    transaction_id      integer NOT NULL,
    execute_employee_id integer NULL,
    atm_address         character varying(255),
    transaction_method  character varying(255),

    CONSTRAINT pk_transaction_detail PRIMARY KEY (transaction_id),
    CONSTRAINT fk_transaction FOREIGN KEY (transaction_id) REFERENCES transaction (transaction_id) ON DELETE CASCADE,
    CONSTRAINT fk_execute_employee FOREIGN KEY (execute_employee_id) REFERENCES employee (employee_id)
);


