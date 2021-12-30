create table if not exists TASKS
(
    ID             INTEGER not null
        constraint PK_TASKS
            primary key,
    DOCUM_ID       INTEGER,
    FKIND_ID       INTEGER,
    DEPART_ID      INTEGER,
    WHAT2DO        VARCHAR(255),
    RECENZ_PERSON  INTEGER,
    PLAN_DATE      DATE,
    REAL_DATE      DATE,
    CHILD_COUNT    INTEGER default 0,
    NOT_EX_CHLD    INTEGER default 0,
    EXECUTED       CHAR(1) default 'F',
    PARENT_TASK_ID INTEGER,
    COLOR          TEXT,
    PR_DATE        DATE,
    PR_DESCR       VARCHAR(255),
    LOGIN          VARCHAR(24),
    EDIT_DATE      TIMESTAMP(19),
    EDITOR_LOGIN   VARCHAR(24)
);
