create table if not exists USERS
(
    USERS_GUID      INTEGER not null
        constraint PK_USERS
            primary key,
    LOGIN           VARCHAR(18),
    PERSON_ID       INTEGER,
    PASSDATA        VARCHAR(100),
    FLASHSERIAL     VARCHAR(100),
    DNS_NAME        VARCHAR(255),
    ENTER_MODE      CHAR(1),
    ENTER_TIMESTAMP TIMESTAMP(19)
);


