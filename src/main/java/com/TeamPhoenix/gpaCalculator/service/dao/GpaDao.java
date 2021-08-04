package com.TeamPhoenix.gpaCalculator.service.dao;

import com.TeamPhoenix.gpaCalculator.beans.User;

public interface GpaDao {

    User getUserDetailsByUsername(String username);

    User getUserDetailsByIndexNumber(String indexNumber);

    void saveUserDetails(User user);
}
