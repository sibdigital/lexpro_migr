create table if not exists GROUPS
(
    GROUPS_GUID INTEGER not null
        constraint PK_GROUPS
            primary key,
    GROUP_NAME  VARCHAR(18),
    GROUP_PRIM  VARCHAR(100)
);