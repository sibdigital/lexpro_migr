create table if not exists MESSAGES
(
    ID                INTEGER not null
        constraint PK_MESSAGES
            primary key,
    TEXT              VARCHAR(1024),
    SENDER_ID         INTEGER,
    RECIEVER_ID       INTEGER,
    MESSAGE_DATE_TIME TIMESTAMP(19)
);

