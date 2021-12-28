create table if not exists DKIND
(
    ID    INTEGER not null
        constraint PK_DKIND
            primary key,
    NAME  VARCHAR(50),
    NAME2 VARCHAR(50)
);
