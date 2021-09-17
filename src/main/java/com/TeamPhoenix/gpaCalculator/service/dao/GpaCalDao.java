package com.TeamPhoenix.gpaCalculator.service.dao;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
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
}