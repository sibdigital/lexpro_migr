create table IF NOT EXISTS EXECUTORS
(
    EXEC_GUID INTEGER not null
        constraint PK_EXECUTORS
            primary key,
    TASK_ID   INTEGER not null,
    PERSON_ID INTEGER not null,
    TASK_DATE DATE default 'now' not null
);