SELECT
    ID,
    DOCUM_ID,
    ORG_ID,
    FGROUP_ID,
    FKIND_ID,
    CAST(CAST(FNAME as varchar(255) character set win1251) as varchar(255) character set utf8) AS FNAME,
    CAST(CAST(FEXT as varchar(10) character set win1251) as varchar(10) character set utf8) AS FEXT,
    FDATA,
    CAST(CAST(MD5SUM as varchar(32) character set win1251) as varchar(32) character set utf8) AS MD5SUM,
    ZDATE,
    CAST(CAST(DNUM as varchar(16) character set win1251) as varchar(16) character set utf8) AS DNUM,
    PAGE_COUNT,
    EDIT_DATE,
    CAST(CAST(EDITOR_LOGIN as varchar(24) character set win1251) as varchar(24) character set utf8) AS EDITOR_LOGIN,
    ZDATE_X,
    DNUM_X,
    CAST(CAST(FNAME_X as varchar(255) character set win1251) as varchar(255) character set utf8) AS FNAME_X,
    CAST(CAST(ZIPPED as varchar(1) character set win1251) as varchar(1) character set utf8) AS ZIPPED,
    CAST(CAST(FLAG_ARCH as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_ARCH,
    CAST(CAST(FLAG_LOCK as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_LOCK,
    CAST(CAST(FLAG_ECP as varchar(1) character set win1251) as varchar(1) character set utf8) AS FLAG_ECP,
    CAST(CAST(ECP_DESCR as varchar(255) character set win1251) as varchar(255) character set utf8) AS ECP_DESCR,
    CAST(CAST(STATUS as varchar(50) character set win1251) as varchar(50) character set utf8) AS STATUS,
    STATUS_CHANGE_TIMESTAMP,
    CAST(CAST(STATUS_CHANGE_TIMESTAMP_STR as varchar(24) character set win1251) as varchar(24) character set utf8) AS STATUS_CHANGE_TIMESTAMP_STR
FROM
    FILES
WHERE ID BETWEEN :start AND :end

