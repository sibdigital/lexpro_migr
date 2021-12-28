create table if not exists SP_FKIND
(
    ID   INTEGER      not null
        constraint PK_SP_FKIND
            primary key,
    NAME VARCHAR(100) not null
);
