SELECT
    MODULES_GUID,
    CAST(CAST(MOD_NAME as varchar(30) character set win1251) as varchar(30) character set utf8) AS MOD_NAME,
    CAST(CAST(MOD_PRIM as varchar(60) character set win1251) as varchar(60) character set utf8) AS MOD_PRIM
FROM
    MODULES
WHERE MODULES_GUID BETWEEN :start AND :end
