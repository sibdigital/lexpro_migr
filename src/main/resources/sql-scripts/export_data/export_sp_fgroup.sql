SELECT
    ID,
    CAST(CAST(NAME as varchar(100) character set win1251) as varchar(100) character set utf8) AS NAME,
    CAST(CAST(SHORT_NAME as varchar(10) character set win1251) as varchar(10) character set utf8) AS SHORT_NAME,
    SORT_ORDER
FROM
    SP_FGROUP
WHERE ID BETWEEN :start AND :end

