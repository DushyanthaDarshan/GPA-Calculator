package com.TeamPhoenix.gpaCalculator.service.dao;

import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.Result;
import com.TeamPhoenix.gpaCalculator.beans.Student;

import java.util.List;

public interface GpaCalDao {

    /**
     * The method to get user details from database by username
     *
     * @param username
     * @return
     */
    Student getUserDetailsByUsername(String username);

    /**
     * The method to get user details from database by username
     *
     * @param username
     * @return
     */
    Student getUserDetailsByUsernameAndPassword(String username, String pw);

    /**
     * The method to get user details from database by index number
     *
     * @param indexNumber
     * @return
     */
    Student getUserDetailsByIndexNumber(String indexNumber);

    /**
     * The method to save user
     *
     * @param student
     */
    void saveUserDetails(Student student);

    /**
     * The method to get all gpa values relevant to the user
     *
     * @param userId
     * @return
     */
    List<Gpa> getAllGpaByUserId(Long userId);

    /**
     * The method to get user with subject relevant to the sem number and user
     *
     * @param semNumber
     * @param userId
     * @return
     */
    Student getAllSubjectAndUserDetailsBySemNumber(Integer semNumber, Long userId);

    /**
     * The method to get overall gpa
     *
     * @param userId
     * @param gpaType
     * @return
     */
    Gpa getOverallGpa(Long userId, String gpaType);

    /**
     * The method to update result
     *
     * @param userId
     * @param subjectId
     * @param resultGrade
     * @return
     */
    void updateResult(Long userId, Integer subjectId, String resultGrade);

    /**
     * The method to save result for previously selected subjects
     *
     * @param userId
     * @param subjectId
     * @param result
     * @return
     */
    void saveResultPreviouslySelectedSubjects(Long userId, Integer subjectId, Result result);

    /**
     * The method to get all subjects by sem no
     *
     * @param semNumber
     * @return
     */
    List<Course> getAllSubjectsBySemNo(Integer semNumber);

    /**
     * The method to save user subject table
     *
     * @param userId
     * @param subjectId
     */
    void saveUserSubject(Long userId, Integer subjectId);

    /**
     * The method to save gpa
     *
     * @param userId
     * @param gpaType
     * @param gpa
     */
    void saveGpa(Long userId, String gpaType, Double gpa);

    /**
     * The method to update gpa
     *
     * @param userId
     * @param gpaType
     * @param gpa
     */
    void updateGpa(Long userId, String gpaType, Double gpa);

    /**
     * The method to get user details by user Id
     *
     * @param userId
     * @return
     */
    Student getUserByUserId(Long userId);

    /**
     * The method to get all core subject by userId
     *
     * @param subjectCode
     * @return
     */
    Course getCoreSubject(String subjectCode);

    /**
     * The method to delete user subject enrollment
     *
     * @param userId
     * @param subjectCode
     */
    void deleteUserSubjectEnrollment(Long userId, String subjectCode);

    /**
     * The method to delete result
     *
     * @param userId
     * @param subjectCode
     */
    void deleteResult(Long userId, String subjectCode);

    /**
     * The method to get user core subjects
     *
     * @param userId
     * @param degree
     * @param combination
     * @return
     */
    Student getUserCoreCourses(Long userId, String degree, String combination);

    /**
     * The method to save subject
     *
     * @param course
     */
    void saveCourse(Course course);

    /**
     * The method to get course
     *
     * @param code
     * @return
     */
    Course getCourseByCode(String code);

    /**
     * The method to delete course
     *
     * @param code
     */
    void deleteCourse(String code);

    /**
     * The method to update course
     *
     * @param course
     */
    void updateCourse(Course course);

    /**
     * The method to get all students
     *
     * @return
     */
    List<Student> getAllStudents();

    /**
     * The method to delete user
     *
     * @param userName
     */
    void deleteStudent(String userName);

    /**
     * The method to get user details from database by user Id
     *
     * @param userId
     * @return
     */
    Student getUserDetailsByUserId(Long userId);

    /**
     * The method to update user
     *
     * @param student
     */
    void updateUser(Student student);
}