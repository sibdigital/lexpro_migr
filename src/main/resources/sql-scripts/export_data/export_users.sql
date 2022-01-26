SELECT
    USERS_GUID,
    CAST(CAST(LOGIN as varchar(18) character set win1251) as varchar(18) character set utf8) AS LOGIN,
    PERSON_ID,
    CAST(CAST(PASSDATA as varchar(100) character set win1251) as varchar(100) character set utf8) AS PASSDATA,
    CAST(CAST(FLASHSERIAL as varchar(100) character set win1251) as varchar(100) character set utf8) AS FLASHSERIAL,
    CAST(CAST(DNS_NAME as varchar(255) character set win1251) as varchar(255) character set utf8) AS DNS_NAME,
    CAST(CAST(ENTER_MODE as varchar(1) character set win1251) as varchar(1) character set utf8) AS ENTER_MODE,
    ENTER_TIMESTAMP
FROM
    USERS
WHERE USERS_GUID BETWEEN :start AND :end