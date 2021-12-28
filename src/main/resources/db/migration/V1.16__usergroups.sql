create table if not exists USERGROUPS
(
    USERGROUPS_GUID INTEGER  not null
        constraint PK_USERGROUPS
            primary key,
    LOGIN           CHAR(16) not null,
    RGROUP          CHAR(16) not null
);