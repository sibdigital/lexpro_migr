SELECT
    ID
    DOCUM_ID,
    CAST(CAST("TYPE" as varchar(1) character set win1251) as varchar(1) character set utf8) AS "TYPE",
    CAST(CAST(FIELD_NAME as varchar(512) character set win1251) as varchar(512) character set utf8) AS FIELD_NAME,
    CAST(CAST(OLD_VALUE as varchar(1024) character set win1251) as varchar(1024) character set utf8) AS OLD_VALUE,
    CAST(CAST(NEW_VALUE as varchar(1024) character set win1251) as varchar(1024) character set utf8) AS NEW_VALUE,
    EDIT_DATE,
    CAST(CAST(EDITOR_LOGIN as varchar(24) character set win1251) as varchar(24) character set utf8) AS EDITOR_LOGIN,
    CAST(CAST(DNS_NAME as varchar(255) character set win1251) as varchar(255) character set utf8) AS DNS_NAME,
    CAST(CAST(ENTER_MODE as varchar(1) character set win1251) as varchar(1) character set utf8) AS ENTER_MODE
FROM
    PROTOKOL
WHERE ID BETWEEN :start AND :end
