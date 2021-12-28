SELECT ID,
       cast(cast(NAME as varchar(100) character set win1251) as varchar(100) character set utf8) as NAME,
       FLAG_SEEALLTASKS
FROM SP_DOLJNOST;