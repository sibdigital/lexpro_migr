SELECT
    USERGROUPS_GUID,
    CAST(CAST(LOGIN as varchar(16) character set win1251) as varchar(16) character set utf8) AS LOGIN,
    CAST(CAST(RGROUP as varchar(16) character set win1251) as varchar(16) character set utf8) AS RGROUP
FROM
    USERGROUPS
WHERE USERGROUPS_GUID BETWEEN :start AND :end