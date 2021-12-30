SELECT ID,
       cast(cast(NAME as varchar(50) character set win1251) as varchar(50) character set utf8) as NAME,
       cast(cast(NAME2 as varchar(50) character set win1251) as varchar(50) character set utf8) AS NAME2
FROM DKIND
WHERE ID BETWEEN :start AND :end