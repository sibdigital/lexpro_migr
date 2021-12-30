SELECT
    GROUPS_GUID,
    CAST(CAST(GROUP_NAME as varchar(18) character set win1251) as varchar(18) character set utf8) AS GROUP_NAME,
    CAST(CAST(GROUP_PRIM as varchar(100) character set win1251) as varchar(100) character set utf8) AS GROUP_PRIM
FROM
    GROUPS
WHERE GROUPS_GUID BETWEEN :start AND :end