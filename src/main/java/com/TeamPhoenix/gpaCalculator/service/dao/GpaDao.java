package com.TeamPhoenix.gpaCalculator.service.dao;

import com.TeamPhoenix.gpaCalculator.beans.User;

public interface GpaDao {

    User getUserDetails(String username);
}
