create table if not exists PROTOKOL
(
    ID           INTEGER not null
        constraint PK_PROTOKOL
            primary key,
    DOCUM_ID     INTEGER,
    TYPE         CHAR(1),
    FIELD_NAME   CHAR(512),
    OLD_VALUE    VARCHAR(1024),
    NEW_VALUE    VARCHAR(1024),
    EDIT_DATE    TIMESTAMP(19),
    EDITOR_LOGIN VARCHAR(24),
    DNS_NAME     VARCHAR(255),
    ENTER_MODE   CHAR(1)
);