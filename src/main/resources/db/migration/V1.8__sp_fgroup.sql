create table if not exists SP_FGROUP
(
    ID         INTEGER      not null
        constraint PK_SP_FGROUP
            primary key,
    NAME       VARCHAR(100) not null,
    SHORT_NAME VARCHAR(10)  not null,
    SORT_ORDER SMALLINT
);