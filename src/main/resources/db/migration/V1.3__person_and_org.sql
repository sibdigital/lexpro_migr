create table IF NOT EXISTS ORG
(
    ID           INTEGER not null
        constraint PK_ORG
            primary key,
    NAME         VARCHAR(160),
    SHORT_NAME   VARCHAR(50),
    PERSON_ID    INTEGER,
    POSTINDX     VARCHAR(10),
    ADRES        VARCHAR(255),
    PHONE        VARCHAR(50),
    EMAIL        VARCHAR(30),
    FLAG_KOMITET CHAR(1) default 'F',
    FLAG_SUBJECT CHAR(1) default 'F',
    FLAG_ORG     CHAR(1) default 'F',
    FLAG_DEP     CHAR(1) default 'F',
    FLAG_REP     SMALLINT,
    FLAG_SORT    SMALLINT default 20,
    NAME2        VARCHAR(100)
);

create table IF NOT EXISTS  PERSON
(
    ID          INTEGER not null
        constraint PK_PERSON
            primary key,
    FIO         VARCHAR(50),
    ORG_ID      INTEGER,
    DEPART_ID   INTEGER unique,
    DOLJNOST_ID INTEGER unique,
    POSTINDX    VARCHAR(10),
    ADRES       VARCHAR(255),
    PHONE       VARCHAR(50),
    EMAIL       VARCHAR(30)
);
