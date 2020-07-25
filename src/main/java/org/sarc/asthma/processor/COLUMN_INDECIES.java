package org.sarc.asthma.processor;

interface COLUMN_INDECIES {

    String[] COLUMNS_HEADER = {
            "الرقم التسلسلي",
            "الاسم",
            "التصنيف العائلي",
            "الحالة الإجتماعية",
            "تاريخ الولادة",
            "الجنس",
            "يعمل",
            "المهنة",
            "الدخل",
            "عنوان السكن",
            "نازح من",
            "نوع السكن",
            "وصف السكن",
            "الحالة",
            "تاريخ التسجيل",
            "سجل المساعدات",
            "سجل المرافقين"
    };

    int COLUMN_FAMILY_NUMBER = 0;
    int COLUMN_BENEFICIARY_NAME = 1;
    int COLUMN_FAMILY_TYPE = 2;
    int COLUMN_BENEFICIARY_STATUS = 3;
    int COLUMN_BENEFICIARY_DATE_OF_BIRTH = 4;
    int COLUMN_BENEFICIARY_SEX = 5;
    int COLUMN_BENEFICIARY_IS_WORKING = 6;
    int COLUMN_BENEFICIARY_JOB = 7;
    int COLUMN_BENEFICIARY_INCOME = 8;
    int COLUMN_FAMILY_ADDRESS = 9;
    int COLUMN_FAMILY_SOURCE = 10;
    int COLUMN_FAMILY_HOUSING_TYPE = 11;
    int COLUMN_FAMILY_HOUSING_STATUS = 12;
    int COLUMN_FAMILY_STATUS = 13;
    int COLUMN_FAMILY_REGESTRATION_DATE = 14;
    int COLUMN_AID_RECORD = 15;
    int COLUMN_MEMBERS_RECORD = 16;

    int[] INDEX_ALL = {
            COLUMN_FAMILY_NUMBER, COLUMN_BENEFICIARY_NAME, COLUMN_FAMILY_TYPE
            , COLUMN_BENEFICIARY_STATUS, COLUMN_BENEFICIARY_DATE_OF_BIRTH,
            COLUMN_BENEFICIARY_SEX,
            COLUMN_BENEFICIARY_IS_WORKING,
            COLUMN_BENEFICIARY_JOB,
            COLUMN_BENEFICIARY_INCOME
            , COLUMN_FAMILY_ADDRESS
            , COLUMN_FAMILY_SOURCE,
            COLUMN_FAMILY_HOUSING_TYPE,
            COLUMN_FAMILY_HOUSING_STATUS,
            COLUMN_FAMILY_STATUS,
            COLUMN_FAMILY_REGESTRATION_DATE,
            COLUMN_AID_RECORD, COLUMN_MEMBERS_RECORD
    };
}
