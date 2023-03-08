CREATE TABLE device
(
    id      int,
    serial  int,
    name    character varying(255),
    version int,
    CONSTRAINT "pk_device" PRIMARY KEY (id)
)