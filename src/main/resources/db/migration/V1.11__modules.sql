create table if not exists MODULES
(
    MODULES_GUID INTEGER not null
        constraint PK_MODULES
            primary key,
    MOD_NAME     VARCHAR(30),
    MOD_PRIM     VARCHAR(60)
);