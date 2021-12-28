create table if not exists FILES
(
    ID                          INTEGER not null
        constraint PK_FILES
            primary key,
    DOCUM_ID                    INTEGER,
    ORG_ID                      INTEGER,
    FGROUP_ID                   INTEGER,
    FKIND_ID                    INTEGER,
    FNAME                       VARCHAR(255),
    FEXT                        VARCHAR(10),
    FDATA                       bytea,
    MD5SUM                      VARCHAR(32),
    ZDATE                       DATE,
    DNUM                        VARCHAR(16),
    PAGE_COUNT                  INTEGER,
    EDIT_DATE                   TIMESTAMP(19),
    EDITOR_LOGIN                VARCHAR(24),
    ZDATE_X                     TEXT,
    DNUM_X                      TEXT,
    FNAME_X                     TEXT,
    ZIPPED                      CHAR(1) default 'F',
    FLAG_ARCH                   CHAR(1) default 'F',
    FLAG_LOCK                   CHAR(1) default 'F',
    FLAG_ECP                    CHAR(1) default 'F',
    ECP_DESCR                   VARCHAR(255),
    STATUS                      VARCHAR(50),
    STATUS_CHANGE_TIMESTAMP     TIMESTAMP(19),
    STATUS_CHANGE_TIMESTAMP_STR TEXT
);
