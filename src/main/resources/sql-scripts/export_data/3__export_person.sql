SELECT
    ID,
    CAST(CAST(FIO as varchar(50) character set win1251) as varchar(50) character set utf8) AS FIO,
    ORG_ID,
    DEPART_ID,
    DOLJNOST_ID,
    CAST(CAST(POSTINDX as varchar(10) character set win1251) as varchar(10) character set utf8) AS POSTINDX,
    CAST(CAST(ADRES as varchar(255) character set win1251) as varchar(255) character set utf8) AS ADRES,
    CAST(CAST(PHONE as varchar(50) character set win1251) as varchar(50) character set utf8) AS PHONE,
    CAST(CAST(EMAIL as varchar(30) character set win1251) as varchar(30) character set utf8) AS EMAIL
FROM
    PERSON;