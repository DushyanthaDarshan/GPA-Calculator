package com.TeamPhoenix.gpaCalculator.service.dao.Impl;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.Result;
import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.beans.Student;
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
    public Student getUserDetailsByUsername(String username) {

        String query = "SELECT USER_ID, USER_TYPE, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USERNAME='" + username + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Student> studentList = new ArrayList<>();
        populateUser(resultSet, studentList);

        Student student = null;
        if (studentList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (studentList.size() == 1) {
            student = studentList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return student;
    }

    @Override
    public Student getUserDetailsByUsernameAndPassword(String username, String pw) {

        String query = "SELECT USER_ID, USER_TYPE, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USERNAME='" + username + "' AND PASSWORD='" + pw + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Student> studentList = new ArrayList<>();
        populateUser(resultSet, studentList);

        Student student = null;
        if (studentList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (studentList.size() == 1) {
            student = studentList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return student;
    }

    @Override
    public Student getUserDetailsByIndexNumber(String indexNumber) {
        String query = "SELECT USER_ID, USER_TYPE, NAME, INDEX_NUMBER, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE INDEX_NUMBER='"
                + indexNumber + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Student> studentList = new ArrayList<>();
        populateUser(resultSet, studentList);

        Student student = null;
        if (studentList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (studentList.size() == 1) {
            student = studentList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return student;
    }

    @Override
    public void saveUserDetails(Student student) {
        String query = "INSERT INTO USER (NAME, INDEX_NUMBER, BATCH, PASSWORD, USERNAME, STREAM, COMBINATION, DEGREE, " +
                "USER_STATUS, USER_CREATED_TS) VALUES ('" + student.getName() + "', '" + student.getIndexNumber() + "', '" +
                student.getBatch() + "', '" + student.getPassword() + "', '" + student.getUsername() + "', '" + student.getStream() +
                "', '" + student.getCombination() + "', '" + student.getDegree() + "', '" + student.getStatus() + "', '" + student.getCreatedTs() + "')";
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
    public Student getAllSubjectAndUserDetailsBySemNumber(Integer semNumber, Long userId) {
        String query = "SELECT S.SUBJECT_ID, S.SUBJECT_NAME, S.SUBJECT_BASE_CATEGORY_ID, S.SUBJECT_CODE, S.SUBJECT_TYPE, " +
                "S.SUBJECT_CREDITS, S.SEMESTER_NUMBER, S.SUBJECT_STATUS, S.SUBJECT_CREATED_TS, U.USER_ID, U.NAME, U.INDEX_NUMBER, " +
                "U.BATCH, U.PASSWORD, U.USERNAME, U.STREAM, U.COMBINATION, U.DEGREE, U.USER_STATUS, U.USER_CREATED_TS, " +
                "R.RESULT_ID, R.RESULT_GRADE, R.RESULT_MARK, R.RESULT_STATUS, R.RESULT_CREATED_TS " +
                "FROM SUBJECT S LEFT JOIN USER_SUBJECT US ON (S.SUBJECT_ID = US.SUBJECT_ID) " +
                "LEFT JOIN USER U ON (US.USER_ID = U.USER_ID) " +
                "LEFT JOIN RESULT R ON (US.USER_SUBJECT_ID=R.USER_SUBJECT_ID) " +
                "WHERE S.SEMESTER_NUMBER='" + semNumber + "' AND U.USER_ID='" + userId + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        Map<Long, Student> userMap = new HashMap<>();
        List<Long> subjectIds = new ArrayList<>();
        populateUserAndSubject(resultSet, userMap, subjectIds);

        final List<Student> listOfStudents = new ArrayList<>();
        if (!userMap.isEmpty()) {
            listOfStudents.addAll(userMap.values());
        }

        Student student = null;
        if (listOfStudents.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (listOfStudents.size() == 1) {
            student = listOfStudents.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return student;
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
    public List<Course> getAllSubjectsBySemNo(Integer semNumber) {
        String query = "SELECT SUBJECT_ID, SUBJECT_NAME, SUBJECT_BASE_CATEGORY_ID, SUBJECT_CODE, SUBJECT_TYPE, SUBJECT_CREDITS, " +
                "SEMESTER_NUMBER, SUBJECT_STATUS, SUBJECT_CREATED_TS FROM SUBJECT WHERE SEMESTER_NUMBER='" + semNumber + "'";
        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Course> courseList = new ArrayList<>();
        populateSubject(resultSet, courseList);
        return courseList;
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
        String query = "UPDATE GPA SET GPA='" + gpa + "' WHERE USER_ID='"  + userId + "' AND GPA_TYPE='" + gpaType + "'";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public Student getUserByUserId(Long userId) {
        String query = "SELECT USER_ID, USER_TYPE, INDEX_NUMBER, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USER_ID='" + userId + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Student> studentList = new ArrayList<>();
        populateUser(resultSet, studentList);

        Student student = null;
        if (studentList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (studentList.size() == 1) {
            student = studentList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return student;
    }


    @Override
    public Course getCoreSubject(String subjectCode){
        String query = "SELECT dcs.DEGREE_CATEGORY_ID, dcs.SUBJECT_ID, dc.DEGREE_CATEGORY_NAME, s.SUBJECT_NAME, s.SUBJECT_CREDITS," +
                "s.SUBJECT_CODE, us.USER_ID"+
                "FROM phoenix_gpa_calculator.degree_category_subject dcs" +
                "left join phoenix_gpa_calculator.degree_category dc on (dcs.DEGREE_CATEGORY_ID = DC.DEGREE_CATEGORY_ID)" +
                "left join phoenix_gpa_calculator.subject s on (dcs.SUBJECT_ID = s.SUBJECT_ID)" +
                "right join phoenix_gpa_calculator.user_subject us on (dcs.SUBJECT_ID = us.SUBJECT_ID)" +
                "where DEGREE_CATEGORY_SUBJECT_DESCRIPTION = \"CORE\" and sc.USER_ID = '" + subjectCode + "'";

        ResultSet resultSet = commonDb.getDataFromDb(query);
        List<Course> courseList = new ArrayList<>();
        populateCoreSubject(resultSet, courseList);

        Course course = null;
        if (courseList.isEmpty()) {
            System.err.println(NO_OBJECT_FOUND);
        } else if (courseList.size() == 1) {
            course = courseList.get(0);
        } else {
            System.err.println(MULTIPLE_OBJECTS_FOUND);
        }

        return course;
    }

    @Override
    public void deleteUserSubjectEnrollment(Long userId, String subjectCode) {
        String query = "DELETE FROM USER_SUBJECT WHERE USER_ID='"  + userId + "' AND SUBJECT_ID=(SELECT SUBJECT_ID FROM SUBJECT " +
                "WHERE SUBJECT_CODE='" + subjectCode + "')";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    @Override
    public void deleteResult(Long userId, String subjectCode) {
        String query = "DELETE FROM RESULT WHERE USER_SUBJECT_ID=(SELECT USER_SUBJECT_ID FROM USER_SUBJECT WHERE " +
                "USER_ID='"  + userId + "'AND SUBJECT_ID=(SELECT SUBJECT_ID FROM SUBJECT WHERE SUBJECT_CODE='" + subjectCode + "'))";
        System.out.println(query);
        commonDb.saveDataToDb(query);
    }

    private void populateUser(ResultSet resultSet, List<Student> studentList) {
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setUserId(resultSet.getLong(DbConstants.USER_ID));
                student.setName(resultSet.getString(DbConstants.NAME));
                student.setUserType(resultSet.getString(DbConstants.USER_TYPE));
                student.setBatch(resultSet.getString(DbConstants.BATCH));
                student.setPassword(resultSet.getString(DbConstants.PASSWORD));
                student.setUsername(resultSet.getString(DbConstants.USERNAME));
                student.setStream(resultSet.getString(DbConstants.STREAM));
                studentList.add(student);
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

    private void populateUserAndSubject(ResultSet resultSet, Map<Long, Student> userMap, List<Long> subjectIds) {
        try {
            while (resultSet.next()) {
                Long userId = resultSet.getLong(DbConstants.USER_ID);
                Student student;
                if (!userMap.containsKey(userId)) {
                    student = new Student();
                    student.setUserId(userId);
                    student.setName(resultSet.getString(DbConstants.NAME));
                    student.setBatch(resultSet.getString(DbConstants.BATCH));
                    student.setPassword(resultSet.getString(DbConstants.PASSWORD));
                    student.setUsername(resultSet.getString(DbConstants.USERNAME));
                    student.setStream(resultSet.getString(DbConstants.STREAM));
                } else {
                    student = userMap.get(userId);
                }
                long subjectId = resultSet.getInt(DbConstants.SUBJECT_ID);
                populateSubjects(resultSet, subjectIds, student, subjectId);

                userMap.put(userId, student);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void populateSubjects(ResultSet resultSet, List<Long> subjectIds, Student student, long subjectId) throws SQLException {
        if (subjectId > 0 && !subjectIds.contains(subjectId)) {
            Result result = new Result();
            result.setResultId(resultSet.getLong(DbConstants.RESULT_ID));
            result.setResultGrade(resultSet.getString(DbConstants.RESULT_GRADE));
            result.setResultMark(resultSet.getDouble(DbConstants.RESULT_MARK));
            result.setStatus(resultSet.getString(DbConstants.RESULT_STATUS));
            result.setCreatedTs(resultSet.getTimestamp(DbConstants.RESULT_CREATED_TS));

            Course course = new Course();
            course.setCourseId(resultSet.getInt(DbConstants.SUBJECT_ID));
            course.setResult(result);
            course.setCourseName(resultSet.getString(DbConstants.SUBJECT_NAME));
            course.setSubjectBaseCategoryId(resultSet.getInt(DbConstants.SUBJECT_BASE_CATEGORY_ID));
            course.setCourseCode(resultSet.getString(DbConstants.SUBJECT_CODE));
            course.setCourseType(resultSet.getString(DbConstants.SUBJECT_TYPE));
            course.setCourseCredits(resultSet.getInt(DbConstants.SUBJECT_CREDITS));
            course.setSemesterNumber(resultSet.getInt(DbConstants.SEMESTER_NUMBER));
            course.setStatus(resultSet.getString(DbConstants.SUBJECT_STATUS));
            course.setCreatedTs(resultSet.getTimestamp(DbConstants.SUBJECT_CREATED_TS));
            student.getSubjectList().add(course);
            subjectIds.add(subjectId);
        }
    }

    private void populateSubject(ResultSet resultSet, List<Course> courseList) {
        try {
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getInt(DbConstants.SUBJECT_ID));
                course.setCourseCode(resultSet.getString(DbConstants.SUBJECT_CODE));
                course.setCourseName(resultSet.getString(DbConstants.SUBJECT_NAME));
                course.setSubjectBaseCategoryId(resultSet.getInt(DbConstants.SUBJECT_BASE_CATEGORY_ID));
                course.setCourseType(resultSet.getString(DbConstants.SUBJECT_TYPE));
                course.setCourseCredits(resultSet.getInt(DbConstants.SUBJECT_CREDITS));
                course.setSemesterNumber(resultSet.getInt(DbConstants.SEMESTER_NUMBER));
                course.setStatus(resultSet.getString(DbConstants.SUBJECT_STATUS));
                course.setCreatedTs(resultSet.getTimestamp(DbConstants.SUBJECT_CREATED_TS));
                courseList.add(course);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void populateCoreSubject(ResultSet resultSet, List<Course> courseList) {
    }
}
