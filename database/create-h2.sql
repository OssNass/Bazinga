    CREATE TABLE HOUSING_STATUS
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    CONSTRAINT PK_HOUSING_STATUS_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_HOUSING_STATUS_NAME UNIQUE (NAME)
);

CREATE TABLE HOUSING_TYPE
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    CONSTRAINT PK_HOUSING_TYPE_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_HOUSING_TYPE_NAME UNIQUE (NAME)
);

CREATE TABLE RELATIONSHIP
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    SEX  INTEGER                              NOT NULL,
    CONSTRAINT PK_RELATIONSHIP_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_RELATIONSHIP_NAME UNIQUE (NAME)
);

CREATE TABLE AID
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    CONSTRAINT PK_AID_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_AID_NAME UNIQUE (NAME)
);

CREATE TABLE FAMILY_STATUS
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    CONSTRAINT PK_FAMILY_STATUS_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_FAMILY_STATUS_NAME UNIQUE (NAME)
);

CREATE TABLE FAMILY_TYPE
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(100)                         NOT NULL,
    CONSTRAINT PK_FAMILY_TYPE_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_FAMILY_TYPE_NAME UNIQUE (NAME)
);

CREATE TABLE REGION
(
    ID        INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    SHORTNAME VARCHAR(100)                         NOT NULL,
    FULLNAME  VARCHAR(255)                         NOT NULL,
    CONSTRAINT PK_REGION_ID PRIMARY KEY (ID),
    CONSTRAINT IDX_REGION_FULLNAME UNIQUE (FULLNAME)
);

CREATE TABLE FAMILY
(
    ID                    INTEGER               NOT NULL,
    STATUS                INTEGER               NOT NULL,
    FAMILY_TYPE           INTEGER               NOT NULL,
    BENEFICARY_ID         INTEGER,
    PROVIDER_ID           INTEGER,
    ORIGIN                INTEGER,
    DATE_OF_REGISTERATION DATE                  NOT NULL,
    LEAD_BY_WOMAN         BOOLEAN DEFAULT FALSE NOT NULL,
    ADDRESS               VARCHAR(100),
    HOUSING_TYPE_ID       INTEGER,
    HOUSEING_STATUS_ID    INTEGER,
    MARRIED               BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT PK_FAMILY_ID PRIMARY KEY (ID)
);

CREATE INDEX IDX_FAMILY_STATUS ON FAMILY (STATUS);

CREATE INDEX IDX_FAMILY_TYPE ON FAMILY (FAMILY_TYPE);

CREATE INDEX IDX_FAMILY_ORIGIN ON FAMILY (ORIGIN);

CREATE INDEX IDX_FAMILY_DOR ON FAMILY (DATE_OF_REGISTERATION);

CREATE TABLE MEMBER_STATUS
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
    NAME VARCHAR(25)                          NOT NULL,
    CONSTRAINT PK_MEMBER_STATUS PRIMARY KEY (ID),
    CONSTRAINT IDX_NAME_UNIQUE UNIQUE (NAME)
);

CREATE INDEX IDX_FAMILY_LBW ON FAMILY (LEAD_BY_WOMAN);

CREATE TABLE MEMBER
(
    FAMILY_ID           INTEGER               NOT NULL,
    MEMEBER_ID          INTEGER               NOT NULL,
    BIRTHDAY            DATE                  NOT NULL,
    RELATIONSHIP        INTEGER               NOT NULL,
    SPECIAL_NEEDS       BOOLEAN DEFAULT FALSE NOT NULL,
    HAS_CHRONIC_DISEASE BOOLEAN DEFAULT FALSE NOT NULL,
    SEX                 INTEGER               NOT NULL,
    AGE                 INTEGER AS DATEDIFF('YY', "BIRTHDAY", CURRENT_DATE()),
    WORK                VARCHAR(100),
    INCOME              INTEGER DEFAULT 0,
    NAME                VARCHAR(20)           NOT NULL,
    FATHER              VARCHAR(100),
    MOTHER              VARCHAR(100),
    STATUS_ID           INTEGER               NOT NULL,
    CONSTRAINT IDX_MEMBER PRIMARY KEY (FAMILY_ID, MEMEBER_ID),
    CONSTRAINT UNQ_MEMBER_MEMEBER_ID UNIQUE (MEMEBER_ID)
);

CREATE INDEX IDX_MEMBER_SEX ON MEMBER (SEX);

CREATE INDEX IDX_MEMBER_RELATIONSHIP ON MEMBER (RELATIONSHIP);

CREATE INDEX IDX_MEMBER_SPECIAL_NEEDS ON MEMBER (SPECIAL_NEEDS);

CREATE INDEX IDX_MEMBER_CHRONIC_DISEASES ON MEMBER (HAS_CHRONIC_DISEASE);

CREATE INDEX IDX_MEMBER_AGE ON MEMBER (AGE);

CREATE TABLE DELIVERED_AID
(
    FAMILY_ID         INTEGER           NOT NULL,
    AID_ID            INTEGER           NOT NULL,
    DATE_OF_DELIVERED DATE              NOT NULL,
    AMOUNT            INTEGER DEFAULT 1 NOT NULL,
    CONSTRAINT IDX_DELIEVERIED_AID_PK PRIMARY KEY (FAMILY_ID, AID_ID, DATE_OF_DELIVERED)
);

CREATE INDEX IDX_DELIEVERIED_AID_AMOUNTS ON DELIVERED_AID (AMOUNT);

CREATE INDEX IDX_DELIEVERIED_AID_DATES ON DELIVERED_AID (DATE_OF_DELIVERED);

CREATE INDEX IDX_DELIEVERIED_AID_PER_FAMILY ON DELIVERED_AID (FAMILY_ID);

CREATE INDEX IDX_DELIEVERIED_AID_PER_AID_TYPE ON DELIVERED_AID (AID_ID);

-- CREATE VIEW LED_BY_WOMAN AS
-- SELECT COUNT(FAMILY.ID)                          AS NUMBER_OF_FAMILIES,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_MALE
-- FROM FAMILY
--          INNER JOIN MEMBER ON FAMILY.ID = MEMBER.FAMILY_ID
-- WHERE FAMILY.LEAD_BY_WOMAN = TRUE;
--
-- CREATE VIEW MARRIED_MARTYR AS
-- SELECT COUNT(FAMILY.ID)                          AS NUMBER_OF_FAMILIES,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_MALE
-- FROM FAMILY
--          INNER JOIN FAMILY_TYPE ON FAMILY.FAMILY_TYPE = FAMILY_TYPE.ID
--          INNER JOIN MEMBER ON FAMILY.ID = MEMBER.FAMILY_ID
-- WHERE FAMILY_TYPE.NAME = 'عائلة شهيد'
--   AND FAMILY.MARRIED = TRUE;
--
-- CREATE VIEW SINGLE_MARTYR AS
-- SELECT COUNT(FAMILY.ID)                          AS NUMBER_OF_FAMILIES,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_MALE
-- FROM FAMILY
--          INNER JOIN FAMILY_TYPE ON FAMILY.FAMILY_TYPE = FAMILY_TYPE.ID
--          INNER JOIN MEMBER ON FAMILY.ID = MEMBER.FAMILY_ID
-- WHERE FAMILY_TYPE.NAME = 'عائلة شهيد'
--   AND FAMILY.MARRIED = FALSE;
--
-- CREATE VIEW SPECIAL_NEEDS AS
-- SELECT COUNT(FAMILY.ID)                          AS NUMBER_OF_FAMILIES,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5
--                      THEN MEMBER.MEMEBER_ID END) AS UNDER_5_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_5_TO_18_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60
--                      THEN MEMBER.MEMEBER_ID END) AS FROM_18_TO_60_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60
--                      THEN MEMBER.MEMEBER_ID END) AS OVER_60_MALE
-- FROM FAMILY
--          INNER JOIN MEMBER ON FAMILY.ID = MEMBER.FAMILY_ID
--          INNER JOIN FAMILY_TYPE ON FAMILY.FAMILY_TYPE = FAMILY_TYPE.ID
-- WHERE FAMILY_TYPE.NAME = 'عائلة ذوي الإحتياجات الخاصة';
--
-- CREATE VIEW SPECIAL_NEEDS_PER_FAMILY AS
-- SELECT COUNT(FAMILY.ID)                                                        AS NUMBER_OF_FAMILIES,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                      FROM MEMBER_STATUS
--                                                                                      WHERE NAME = 'مفقود'
--                                                                                         OR NAME = 'متوفى'
--                                                                                         OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS UNDER_5_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                      FROM MEMBER_STATUS
--                                                                                      WHERE NAME = 'مفقود'
--                                                                                         OR NAME = 'متوفى'
--                                                                                         OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS UNDER_5_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                                           FROM MEMBER_STATUS
--                                                                                                           WHERE NAME = 'مفقود'
--                                                                                                              OR NAME = 'متوفى'
--                                                                                                              OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_5_TO_18_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                                           FROM MEMBER_STATUS
--                                                                                                           WHERE NAME = 'مفقود'
--                                                                                                              OR NAME = 'متوفى'
--                                                                                                              OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_5_TO_18_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                                            FROM MEMBER_STATUS
--                                                                                                            WHERE NAME = 'مفقود'
--                                                                                                               OR NAME = 'متوفى'
--                                                                                                               OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_18_TO_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                                            FROM MEMBER_STATUS
--                                                                                                            WHERE NAME = 'مفقود'
--                                                                                                               OR NAME = 'متوفى'
--                                                                                                               OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_18_TO_60_MALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                        FROM MEMBER_STATUS
--                                                                                        WHERE NAME = 'مفقود'
--                                                                                           OR NAME = 'متوفى'
--                                                                                           OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS OVER_60_FEMALE,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60 AND MEMBER.STATUS_ID NOT IN (SELECT ID
--                                                                                        FROM MEMBER_STATUS
--                                                                                        WHERE NAME = 'مفقود'
--                                                                                           OR NAME = 'متوفى'
--                                                                                           OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS OVER_60_MALE,
--        COUNT(CASE WHEN MEMBER.SPECIAL_NEEDS = TRUE THEN MEMBER.MEMEBER_ID END) AS NUMBER_OF_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE < 5 AND MEMBER.SPECIAL_NEEDS = TRUE AND MEMBER.STATUS_ID NOT IN
--                                                                                             (SELECT ID
--                                                                                              FROM MEMBER_STATUS
--                                                                                              WHERE NAME = 'مفقود'
--                                                                                                 OR NAME = 'متوفى'
--                                                                                                 OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS UNDER_5_FEMALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE < 5 AND MEMBER.SPECIAL_NEEDS = TRUE AND MEMBER.STATUS_ID NOT IN
--                                                                                             (SELECT ID
--                                                                                              FROM MEMBER_STATUS
--                                                                                              WHERE NAME = 'مفقود'
--                                                                                                 OR NAME = 'متوفى'
--                                                                                                 OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS UNDER_5_MALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18 AND MEMBER.SPECIAL_NEEDS = TRUE AND
--                       MEMBER.STATUS_ID NOT IN
--                       (SELECT ID FROM MEMBER_STATUS WHERE NAME = 'مفقود' OR NAME = 'متوفى' OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_5_TO_18_FEMALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 5 AND MEMBER.AGE < 18 AND MEMBER.SPECIAL_NEEDS = TRUE AND
--                       MEMBER.STATUS_ID NOT IN
--                       (SELECT ID FROM MEMBER_STATUS WHERE NAME = 'مفقود' OR NAME = 'متوفى' OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_5_TO_18_MALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60 AND MEMBER.SPECIAL_NEEDS = TRUE AND
--                       MEMBER.STATUS_ID NOT IN
--                       (SELECT ID FROM MEMBER_STATUS WHERE NAME = 'مفقود' OR NAME = 'متوفى' OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_18_TO_60_FEMALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 18 AND MEMBER.AGE < 60 AND MEMBER.SPECIAL_NEEDS = TRUE AND
--                       MEMBER.STATUS_ID NOT IN
--                       (SELECT ID FROM MEMBER_STATUS WHERE NAME = 'مفقود' OR NAME = 'متوفى' OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS FROM_18_TO_60_MALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 0 AND MEMBER.AGE >= 60 AND MEMBER.SPECIAL_NEEDS = TRUE AND MEMBER.STATUS_ID NOT IN
--                                                                                               (SELECT ID
--                                                                                                FROM MEMBER_STATUS
--                                                                                                WHERE NAME = 'مفقود'
--                                                                                                   OR NAME = 'متوفى'
--                                                                                                   OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS OVER_60_FEMALE_SPECIAL_NEEDS,
--        COUNT(CASE
--                  WHEN MEMBER.SEX = 1 AND MEMBER.AGE >= 60 AND MEMBER.SPECIAL_NEEDS = TRUE AND MEMBER.STATUS_ID NOT IN
--                                                                                               (SELECT ID
--                                                                                                FROM MEMBER_STATUS
--                                                                                                WHERE NAME = 'مفقود'
--                                                                                                   OR NAME = 'متوفى'
--                                                                                                   OR NAME = 'غير موجود')
--                      THEN MEMBER.MEMEBER_ID END)                               AS OVER_60_MALE_SPECIAL_NEEDS
-- FROM FAMILY
--          INNER JOIN MEMBER ON FAMILY.ID = MEMBER.FAMILY_ID
--          INNER JOIN FAMILY_TYPE ON FAMILY.FAMILY_TYPE = FAMILY_TYPE.ID
-- WHERE FAMILY_TYPE.NAME = 'عائلة ذوي الإحتياجات الخاصة'
-- GROUP BY FAMILY.ID;

-- CREATE TRIGGER ISMARRIED
--     BEFORE UPDATE, INSERT
--     ON MEMBER
--     FOR EACH ROW
-- CALL 'ORG.SARC.BAZINGA.DATABASE.MARRIEDTRIGGER';

ALTER TABLE DELIVERED_AID
    ADD CONSTRAINT FK_DELIEVERIED_AID_AID FOREIGN KEY (AID_ID) REFERENCES AID (ID) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE DELIVERED_AID
    ADD CONSTRAINT FK_DELIEVERIED_AID_FAMILY FOREIGN KEY (FAMILY_ID) REFERENCES FAMILY (ID) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE FAMILY
    ADD CONSTRAINT FK_FAMILY FOREIGN KEY (ORIGIN) REFERENCES REGION (ID) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE FAMILY
    ADD CONSTRAINT FK_FAMILY_FAMILY_TYPE FOREIGN KEY (FAMILY_TYPE) REFERENCES FAMILY_TYPE (ID) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE FAMILY
    ADD CONSTRAINT FK_FAMILY_FAMILY_STATUS FOREIGN KEY (STATUS) REFERENCES FAMILY_STATUS (ID) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE FAMILY
    ADD CONSTRAINT FK_FAMILY_HOUSING_TYPE FOREIGN KEY (HOUSING_TYPE_ID) REFERENCES HOUSING_TYPE (ID) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE FAMILY
    ADD CONSTRAINT FK_FAMILY_HOUSING_STATUS FOREIGN KEY (HOUSING_TYPE_ID) REFERENCES HOUSING_STATUS (ID) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER FOREIGN KEY (FAMILY_ID) REFERENCES FAMILY (ID) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_RELATIONSHIP FOREIGN KEY (RELATIONSHIP) REFERENCES RELATIONSHIP (ID) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_STATUS FOREIGN KEY (STATUS_ID) REFERENCES MEMBER_STATUS (ID) ON DELETE RESTRICT ON UPDATE CASCADE;


INSERT INTO FAMILY_TYPE
    (NAME)
VALUES ('عائلة شهيد'),
       ('عائلة مريض'),
       ('عائلة ذوي الإحتياجات الخاصة'),
       ('عائلة متضررة'),
       ('غير محدد'),
       ('عائلة فقيرة'),
       ('عائلة مصاب'),
       ('عائلة معاق'),
       ('عائلة أيتام'),
       ('عائلة مفقود'),
       ('عائلة نازحين'),
       ('عائلة وافدين من مخيم الرقبان - مراكز إيواء'),
       ('عائلة فقيرة'),
       ('عائلة عائدين'),
       ('عائلة عاجز'),
       ('عائلة معتقل'),
       ('عائلة وافدين من مخيم الرقبان - افرادي');

INSERT INTO HOUSING_TYPE
    (NAME)
VALUES ('إعارة'),
       ('إيجار'),
       ('إيجار / مشترك'),
       ('رهن'),
       ('مركز ايواء'),
       ('مشترك'),
       ('ملك');

INSERT INTO HOUSING_STATUS
    (NAME)
VALUES ('عادي'),
       ('مشترك'),
       ('غرفتين'),
       ('سيء'),
       ('جيد'),
       ('غرفة واحدة'),
       ('سيء جدا'),
       ('متضرر'),
       ('توتياء'),
       ('ملحق');

INSERT INTO FAMILY_STATUS
    (NAME)
VALUES ('مقبول لم يستلم بطاقة'),
       ('مقبول - مساعدات غير غذائية'),
       ('مستعجل - استلام مرة واحدة'),
       ('ملغى'),
       ('الفريق الجوال'),
       ('مقبول'),
       ('تقييك - تم تقييمه'),
       ('تقييم - للدراسة'),
       ('جديد'),
       ('حالة1'),
       ('حالة2'),
       ('حالة3'),
       ('حالة'),
       ('قيد الدراسة'),
       ('مشاكل - نقص بيانات'),
       ('مقبول - تسجيل شهري'),
       ('مقبول - جيد'),
       ('مقبول - سيء'),
       ('مقبول - سيء جدا'),
       ('مقبول - مساعدات طبية'),
       ('مقبول - مساعدات طبية - دوائية'),
       ('ملغى - غير مستحق');

INSERT INTO RELATIONSHIP(NAME, SEX)
VALUES ('أخ', 1),
       ('ابن', 1),
       ('جد', 1),
       ('حفيد', 1),
       ('خال', 1),
       ('زوج', 1),
       ('عم', 1),
       ('قريب المستفيد', 1),
       ('قريب زوجة المستفيد', 1),
       ('والد المستفيد', 1),
       ('والد زوجة المستفيد', 1),
       ('أخت', 0),
       ('ابنة', 0),
       ('جدة', 0),
       ('حفيدة', 0),
       ('خالة', 0),
       ('زوجة', 0),
       ('عمة', 0),
       ('قريبة المستفيد', 0),
       ('قريبة زوجة المستفيد', 0),
       ('والدة المستفيد', 0),
       ('والدة زوجة المستفيد', 0);

INSERT INTO MEMBER_STATUS(NAME)
VALUES ('متوفى'),
       ('متزوج'),
       ('أعزب'),
       ('مفقود'),
       ('أرملة'),
       ('مطلقة'),
       ('متزوجة'),
       ('مطلق'),
       ('غير موجود'),
       ('أرمل'),
       ('عزبة'),
       ('غير محدد');
