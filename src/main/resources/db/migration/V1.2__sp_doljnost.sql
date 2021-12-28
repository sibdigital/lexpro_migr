create table IF NOT EXISTS SP_DOLJNOST
(
    ID               INTEGER      not null
        constraint PK_SP_DOLJNOST
            primary key,
    NAME             VARCHAR(100) not null,
    FLAG_SEEALLTASKS CHAR(1) default 'F'
);
