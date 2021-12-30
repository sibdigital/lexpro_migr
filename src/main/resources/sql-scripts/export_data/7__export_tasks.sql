SELECT
    ID,
    DOCUM_ID,
    FKIND_ID,
    DEPART_ID,
    CAST(CAST(WHAT2DO as varchar(255) character set win1251) as varchar(255) character set utf8) AS WHAT2DO,
    RECENZ_PERSON,
    PLAN_DATE,
    REAL_DATE,
    CHILD_COUNT,
    NOT_EX_CHLD,
    CAST(CAST(EXECUTED as varchar(1) character set win1251) as varchar(1) character set utf8) AS EXECUTED,
    PARENT_TASK_ID,
    COLOR,
    PR_DATE,
    CAST(CAST(PR_DESCR as varchar(255) character set win1251) as varchar(255) character set utf8) AS PR_DESCR,
    CAST(CAST(LOGIN as varchar(24) character set win1251) as varchar(24) character set utf8) AS LOGIN,
    EDIT_DATE,
    CAST(CAST(EDITOR_LOGIN as varchar(24) character set win1251) as varchar(24) character set utf8) AS EDITOR_LOGIN
FROM
    TASKS
WHERE ID BETWEEN :start AND :end

