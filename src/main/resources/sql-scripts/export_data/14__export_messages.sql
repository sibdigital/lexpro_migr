SELECT
    ID,
    CAST(CAST(TEXT as varchar(1024) character set win1251) as varchar(1024) character set utf8) AS TEXT,
    SENDER_ID,
    RECIEVER_ID,
    MESSAGE_DATE_TIME
FROM
    MESSAGES
WHERE ID BETWEEN :start AND :end