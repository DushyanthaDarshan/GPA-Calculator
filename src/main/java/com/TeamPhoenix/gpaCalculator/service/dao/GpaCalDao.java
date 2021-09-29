package com.TeamPhoenix.gpaCalculator.service.dao;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.Result;
import com.TeamPhoenix.gpaCalculator.beans.Subject;
import com.TeamPhoenix.gpaCalculator.beans.User;

import java.util.List;

public interface GpaCalDao {

    /**
     * The method to get user details from database by username
     *
     * @param username
     * @return
     */
    User getUserDetailsByUsername(String username);

    /**
     * The method to get user details from database by username
     *
     * @param username
     * @return
     */
    User getUserDetailsByUsernameAndPassword(String username, String pw);

    /**
     * The method to get user details from database by index number
     *
     * @param indexNumber
     * @return
     */
    User getUserDetailsByIndexNumber(String indexNumber);

    /**
     * The method to save user
     *
     * @param user
     */
    void saveUserDetails(User user);

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
    User getAllSubjectAndUserDetailsBySemNumber(Integer semNumber, Long userId);

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
    List<Subject> getAllSubjectsBySemNo(Integer semNumber);

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
    User getUserByUserId(Long userId);


    /**
     * The method to get all core subject by userId
     *
     * @param subjectCode
     * @return
     */
    Subject getCoreSubject(String subjectCode);
}