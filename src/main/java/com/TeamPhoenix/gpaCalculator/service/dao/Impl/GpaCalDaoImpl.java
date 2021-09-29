package com.TeamPhoenix.gpaCalculator.service.dao.Impl;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.Result;
import com.TeamPhoenix.gpaCalculator.beans.Subject;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.CommonDb;
import com.TeamPhoenix.gpaCalculator.service.dao.DbConstants;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.TeamPhoenix.gpaCalculator.service.dao.DbConstants.MULTIPLE_OBJECTS_FOUND;
import static com.TeamPhoenix.gpaCalculator.service.dao.DbConstants.NO_OBJECT_FOUND;

public class GpaCalDaoImpl extends CommonDb implements GpaCalDao {

    CommonDb commonDb = new CommonDb();

    @Override
    public User getUserDetailsByUsername(String username) {

        String query = "SELECT USER_ID, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USERNAME='" + username + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<User> userList = new ArrayList<>();
        populateUser(resultSet, userList);

        User user = null;
        if (userList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (userList.size() == 1) {
            user = userList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return user;
    }

    @Override
    public User getUserDetailsByUsernameAndPassword(String username, String pw) {

        String query = "SELECT USER_ID, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USERNAME='" + username + "' AND PASSWORD='" + pw + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<User> userList = new ArrayList<>();
        populateUser(resultSet, userList);

        User user = null;
        if (userList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (userList.size() == 1) {
            user = userList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return user;
    }

    @Override
    public User getUserDetailsByIndexNumber(String indexNumber) {
        String query = "SELECT USER_ID, NAME, INDEX_NUMBER, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE INDEX_NUMBER='"
                + indexNumber + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<User> userList = new ArrayList<>();
        populateUser(resultSet, userList);

        User user = null;
        if (userList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (userList.size() == 1) {
            user = userList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return user;
    }

    @Override
    public void saveUserDetails(User user) {
        String query = "INSERT INTO USER (NAME, INDEX_NUMBER, BATCH, PASSWORD, USERNAME, STREAM, COMBINATION, DEGREE, " +
                "USER_STATUS, USER_CREATED_TS) VALUES ('" + user.getName() + "', '" + user.getIndexNumber() + "', '" +
                user.getBatch() + "', '" + user.getPassword() + "', '" + user.getUsername() + "', '" + user.getStream() +
                "', '" + user.getCombination() + "', '" + user.getDegree() + "', '" + user.getStatus() + "', '" + user.getCreatedTs() + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public List<Gpa> getAllGpaByUserId(Long userId) {
        String query = "SELECT GPA_ID, USER_ID, GPA, GPA_TYPE, GPA_STATUS, GPA_CREATED_TS FROM GPA WHERE USER_ID='" + userId + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Gpa> gpaList = new ArrayList<>();
        populateGpa(resultSet, gpaList);
        return gpaList;
    }

    @Override
    public User getAllSubjectAndUserDetailsBySemNumber(Integer semNumber, Long userId) {
        String query = "SELECT S.SUBJECT_ID, S.SUBJECT_NAME, S.SUBJECT_BASE_CATEGORY_ID, S.SUBJECT_CODE, S.SUBJECT_TYPE, " +
                "S.SUBJECT_CREDITS, S.SEMESTER_NUMBER, S.SUBJECT_STATUS, S.SUBJECT_CREATED_TS, U.USER_ID, U.NAME, U.INDEX_NUMBER, " +
                "U.BATCH, U.PASSWORD, U.USERNAME, U.STREAM, U.COMBINATION, U.DEGREE, U.USER_STATUS, U.USER_CREATED_TS, " +
                "R.RESULT_ID, R.RESULT_GRADE, R.RESULT_MARK, R.RESULT_STATUS, R.RESULT_CREATED_TS " +
                "FROM SUBJECT S LEFT JOIN USER_SUBJECT US ON (S.SUBJECT_ID = US.SUBJECT_ID) " +
                "LEFT JOIN USER U ON (US.USER_ID = U.USER_ID) " +
                "LEFT JOIN RESULT R ON (US.USER_SUBJECT_ID=R.USER_SUBJECT_ID) " +
                "WHERE S.SEMESTER_NUMBER='" + semNumber + "' AND U.USER_ID='" + userId + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        Map<Long, User> userMap = new HashMap<>();
        List<Long> subjectIds = new ArrayList<>();
        populateUserAndSubject(resultSet, userMap, subjectIds);

        final List<User> listOfUsers = new ArrayList<>();
        if (!userMap.isEmpty()) {
            listOfUsers.addAll(userMap.values());
        }

        User user = null;
        if (listOfUsers.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (listOfUsers.size() == 1) {
            user = listOfUsers.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return user;
    }

    @Override
    public Gpa getOverallGpa(Long userId, String gpaType) {
        String query = "SELECT GPA_ID, USER_ID, GPA, GPA_TYPE, GPA_STATUS, GPA_CREATED_TS FROM GPA WHERE USER_ID='" + userId +
                "' AND GPA_TYPE='" + gpaType + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Gpa> gpaList = new ArrayList<>();
        populateGpa(resultSet, gpaList);

        Gpa gpa = null;
        if (gpaList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (gpaList.size() == 1) {
            gpa = gpaList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return gpa;
    }

    @Override
    public void updateResult(Long userId, Integer subjectId, String resultGrade) {
        System.out.println("GpaCalDaoImpl - Entered to update the result");
        String query = "UPDATE RESULT SET RESULT_GRADE='" + resultGrade + "' WHERE USER_SUBJECT_ID=(SELECT USER_SUBJECT_ID " +
                "FROM USER_SUBJECT WHERE USER_ID='"  + userId + "' AND SUBJECT_ID='" + subjectId + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public void saveResultPreviouslySelectedSubjects(Long userId, Integer subjectId, Result result) {
        System.out.println("GpaCalDaoImpl - Entered to save the result for previously selected subjects");

        String query = "INSERT INTO RESULT (USER_SUBJECT_ID, RESULT_GRADE, RESULT_STATUS) " +
                "VALUES ((SELECT USER_SUBJECT_ID FROM USER_SUBJECT WHERE USER_ID='" + userId + "' AND SUBJECT_ID='" + subjectId + "'), " +
                " '" + result.getResultGrade() + "', '" + result.getStatus() + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public List<Subject> getAllSubjectsBySemNo(Integer semNumber) {
        String query = "SELECT SUBJECT_ID, SUBJECT_NAME, SUBJECT_BASE_CATEGORY_ID, SUBJECT_CODE, SUBJECT_TYPE, SUBJECT_CREDITS, " +
                "SEMESTER_NUMBER, SUBJECT_STATUS, SUBJECT_CREATED_TS FROM SUBJECT WHERE SEMESTER_NUMBER='" + semNumber + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Subject> subjectList = new ArrayList<>();
        populateSubject(resultSet, subjectList);
        return subjectList;
    }

    @Override
    public void saveUserSubject(Long userId, Integer subjectId) {
        System.out.println("GpaCalDaoImpl - Entered to save the user subject table for previously selected subjects");

        String query = "INSERT INTO USER_SUBJECT (USER_ID, SUBJECT_ID) VALUES ('" + userId + "', '" + subjectId + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public void saveGpa(Long userId, String gpaType, Double gpa) {
        String query = "INSERT INTO GPA (USER_ID, GPA, GPA_TYPE) VALUES ('" + userId + "', '" + gpa + "', '" + gpaType + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public void updateGpa(Long userId, String gpaType, Double gpa) {
        String query = "UPDATE GPA SET GPA='" + gpa + "' WHERE USER_ID='"  + userId + "' AND GPA_TYPE='" + gpaType + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public User getUserByUserId(Long userId) {
        String query = "SELECT USER_ID, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USER_ID='" + userId + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<User> userList = new ArrayList<>();
        populateUser(resultSet, userList);

        User user = null;
        if (userList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (userList.size() == 1) {
            user = userList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return user;
    }


    private void populateUser(ResultSet resultSet, List<User> userList) {
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong(DbConstants.USER_ID));
                user.setName(resultSet.getString(DbConstants.NAME));
                user.setBatch(resultSet.getString(DbConstants.BATCH));
                user.setPassword(resultSet.getString(DbConstants.PASSWORD));
                user.setUsername(resultSet.getString(DbConstants.USERNAME));
                user.setStream(resultSet.getString(DbConstants.STREAM));
                userList.add(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void populateGpa(ResultSet resultSet, List<Gpa> gpaList) {
        try {
            while (resultSet.next()) {
                Gpa gpa = new Gpa();
                gpa.setGpaId(resultSet.getLong(DbConstants.GPA_ID));
                gpa.setGpa(resultSet.getDouble(DbConstants.GPA));
                gpa.setGpaType(resultSet.getString(DbConstants.GPA_TYPE));
                gpa.setStatus(resultSet.getString(DbConstants.GPA_STATUS));
                gpa.setCreatedTs(resultSet.getTimestamp(DbConstants.GPA_CREATED_TS));
                gpaList.add(gpa);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void populateUserAndSubject(ResultSet resultSet, Map<Long, User> userMap, List<Long> subjectIds) {
        try {
            while (resultSet.next()) {
                Long userId = resultSet.getLong(DbConstants.USER_ID);
                User user;
                if (!userMap.containsKey(userId)) {
                    user = new User();
                    user.setUserId(userId);
                    user.setName(resultSet.getString(DbConstants.NAME));
                    user.setBatch(resultSet.getString(DbConstants.BATCH));
                    user.setPassword(resultSet.getString(DbConstants.PASSWORD));
                    user.setUsername(resultSet.getString(DbConstants.USERNAME));
                    user.setStream(resultSet.getString(DbConstants.STREAM));
                } else {
                    user = userMap.get(userId);
                }
                long subjectId = resultSet.getInt(DbConstants.SUBJECT_ID);
                populateSubjects(resultSet, subjectIds, user, subjectId);

                userMap.put(userId, user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void populateSubjects(ResultSet resultSet, List<Long> subjectIds, User user, long subjectId) throws SQLException {
        if (subjectId > 0 && !subjectIds.contains(subjectId)) {
            Result result = new Result();
            result.setResultId(resultSet.getLong(DbConstants.RESULT_ID));
            result.setResultGrade(resultSet.getString(DbConstants.RESULT_GRADE));
            result.setResultMark(resultSet.getDouble(DbConstants.RESULT_MARK));
            result.setStatus(resultSet.getString(DbConstants.RESULT_STATUS));
            result.setCreatedTs(resultSet.getTimestamp(DbConstants.RESULT_CREATED_TS));

            Subject subject = new Subject();
            subject.setSubjectId(resultSet.getInt(DbConstants.SUBJECT_ID));
            subject.setResult(result);
            subject.setSubjectName(resultSet.getString(DbConstants.SUBJECT_NAME));
            subject.setSubjectBaseCategoryId(resultSet.getInt(DbConstants.SUBJECT_BASE_CATEGORY_ID));
            subject.setSubjectCode(resultSet.getString(DbConstants.SUBJECT_CODE));
            subject.setSubjectType(resultSet.getString(DbConstants.SUBJECT_TYPE));
            subject.setSubjectCredits(resultSet.getInt(DbConstants.SUBJECT_CREDITS));
            subject.setSemesterNumber(resultSet.getInt(DbConstants.SEMESTER_NUMBER));
            subject.setStatus(resultSet.getString(DbConstants.SUBJECT_STATUS));
            subject.setCreatedTs(resultSet.getTimestamp(DbConstants.SUBJECT_CREATED_TS));
            user.getSubjectList().add(subject);
            subjectIds.add(subjectId);
        }
    }

    private void populateSubject(ResultSet resultSet, List<Subject> subjectList) {
        try {
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(DbConstants.SUBJECT_ID));
                subject.setSubjectCode(resultSet.getString(DbConstants.SUBJECT_CODE));
                subject.setSubjectName(resultSet.getString(DbConstants.SUBJECT_NAME));
                subject.setSubjectBaseCategoryId(resultSet.getInt(DbConstants.SUBJECT_BASE_CATEGORY_ID));
                subject.setSubjectType(resultSet.getString(DbConstants.SUBJECT_TYPE));
                subject.setSubjectCredits(resultSet.getInt(DbConstants.SUBJECT_CREDITS));
                subject.setSemesterNumber(resultSet.getInt(DbConstants.SEMESTER_NUMBER));
                subject.setStatus(resultSet.getString(DbConstants.SUBJECT_STATUS));
                subject.setCreatedTs(resultSet.getTimestamp(DbConstants.SUBJECT_CREATED_TS));
                subjectList.add(subject);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
