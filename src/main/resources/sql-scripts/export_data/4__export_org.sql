SELECT
    ID,
    CAST(CAST(NAME as varchar(160) character set win1251) as varchar(160) character set utf8) AS NAME,
    CAST(CAST(SHORT_NAME as varchar(50) character set win1251) as varchar(50) character set utf8) AS SHORT_NAME,
    PERSON_ID,
    CAST(CAST(POSTINDX as varchar(10) character set win1251) as varchar(10) character set utf8) AS POSTINDX,
    CAST(CAST(ADRES as varchar(255) character set win1251) as varchar(255) character set utf8) AS ADRES,
    CAST(CAST(PHONE as varchar(50) character set win1251) as varchar(50) character set utf8) AS PHONE,
    CAST(CAST(EMAIL as varchar(30) character set win1251) as varchar(30) character set utf8) AS EMAIL,
    CAST(CAST(FLAG_KOMITET as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_KOMITET,
    CAST(CAST(FLAG_SUBJECT as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_SUBJECT,
    CAST(CAST(FLAG_ORG as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_ORG,
    CAST(CAST(FLAG_DEP as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_DEP,
    FLAG_REP,
    FLAG_SORT,
    CAST(CAST(NAME2 as varchar(50) character set win1251) as varchar(50) character set utf8) AS NAME2
FROM
    ORG
WHERE ID BETWEEN :start AND :end

