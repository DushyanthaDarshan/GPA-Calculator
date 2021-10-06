package com.TeamPhoenix.gpaCalculator.service.dao;

/**
 * Database related constants
 *
 */
public final class DbConstants {

    //Common
    public static final String NO_OBJECT_FOUND = "No object found";
    public static final String MULTIPLE_OBJECTS_FOUND = "Multiple objects found";

    //User related
    public static final String USER_ID = "USER_ID";
    public static final String NAME = "NAME";
    public static final String USER_TYPE = "USER_TYPE";
    public static final String INDEX_NUMBER = "INDEX_NUMBER";
    public static final String BATCH = "BATCH";
    public static final String PASSWORD = "PASSWORD";
    public static final String USERNAME = "USERNAME";
    public static final String STREAM = "STREAM";
    public static final String COMBINATION = "COMBINATION";
    public static final String DEGREE = "DEGREE";
    public static final String USER_STATUS = "USER_STATUS";
    public static final String USER_CREATED_TS = "USER_CREATED_TS";

    //Subject related
    public static final String SUBJECT_ID = "SUBJECT_ID";
    public static final String SUBJECT_NAME = "SUBJECT_NAME";
    public static final String SUBJECT_BASE_CATEGORY_ID = "SUBJECT_BASE_CATEGORY_ID";
    public static final String SUBJECT_CODE = "SUBJECT_CODE";
    public static final String SUBJECT_TYPE = "SUBJECT_TYPE";
    public static final String SUBJECT_CREDITS = "SUBJECT_CREDITS";
    public static final String SEMESTER_NUMBER = "SEMESTER_NUMBER";
    public static final String SUBJECT_STATUS = "SUBJECT_STATUS";
    public static final String SUBJECT_CREATED_TS = "SUBJECT_CREATED_TS";

    //degree category related
    public static final String DEGREE_CATEGORY_ID = "DEGREE_CATEGORY_ID";
    public static final String DEGREE_CATEGORY_NAME = "DEGREE_CATEGORY_NAME";
    public static final String SUB_CATEGORY_NAME = "SUB_CATEGORY_NAME";
    public static final String DEGREE_CATEGORY_STATUS = "DEGREE_CATEGORY_STATUS";
    public static final String DEGREE_CATEGORY_CREATED_TS = "DEGREE_CATEGORY_CREATED_TS";

    //gpa related
    public static final String GPA_ID = "GPA_ID";
    public static final String GPA = "GPA";
    public static final String GPA_TYPE = "GPA_TYPE";
    public static final String GPA_STATUS = "GPA_STATUS";
    public static final String GPA_CREATED_TS = "GPA_CREATED_TS";

    //Subject and degree related
    public static final String DEGREE_CATEGORY_SUBJECT_DESCRIPTION = "DEGREE_CATEGORY_SUBJECT_DESCRIPTION";
    public static final String DEGREE_CATEGORY_SUBJECT_STATUS = "DEGREE_CATEGORY_SUBJECT_STATUS";
    public static final String DEGREE_CATEGORY_SUBJECT_CREATED_TS = "DEGREE_CATEGORY_SUBJECT_CREATED_TS";

    //gpa related
    public static final String RESULT_ID = "RESULT_ID";
    public static final String RESULT_GRADE = "RESULT_GRADE";
    public static final String RESULT_MARK = "RESULT_MARK";
    public static final String RESULT_STATUS = "RESULT_STATUS";
    public static final String RESULT_CREATED_TS = "RESULT_CREATED_TS";

    //user and subject related
    public static final String USER_SUBJECT_ID = "USER_SUBJECT_ID";
    public static final String USER_SUBJECT_STATUS = "USER_SUBJECT_STATUS";
    public static final String USER_SUBJECT_CREATED_TS = "USER_SUBJECT_CREATED_TS";
}
