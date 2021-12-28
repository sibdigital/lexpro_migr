create table if not exists GROUPMODULES
(
    GROUPMODULES_GUID INTEGER not null
        constraint PK_GROUPMODULES
            primary key,
    GROUPS_GUID       INTEGER not null,
    MODULES_GUID      INTEGER not null
);