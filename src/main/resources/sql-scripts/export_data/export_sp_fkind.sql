SELECT
    ID,
    CAST(CAST(NAME as varchar(100) character set win1251) as varchar(100) character set utf8) AS NAME
FROM
    SP_FKIND
WHERE ID BETWEEN :start AND :end