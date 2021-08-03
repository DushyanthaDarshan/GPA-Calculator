package com.TeamPhoenix.gpaCalculator.service.dao.Impl;

import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.Common;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GpaDaoImpl extends Common implements GpaDao {

    Common common = new Common();

    @Override
    public User getUserDetails(String username) {

        String query = "SELECT USER_ID, NAME, BATCH, PASSWORD, USERNAME, STREAM FROM USER WHERE USERNAME='" + username + "'";

        ResultSet resultSet = common.getDataFromDb(query);
        List<User> userList = new ArrayList<>();
        populateUser(resultSet, userList);

        return userList.get(0);
    }

    @Override
    public void saveUserDetails(User user) {

        String query = "INSERT INTO USER (NAME, INDEX_NUMBER, BATCH, PASSWORD, USERNAME, STREAM, COMBINATION, DEGREE, USER_STATUS) " +
                "VALUES ('" + user.getName() + "', '" + user.getIndexNumber() + "', '" + user.getBatch() + "', '" + user.getPassword() +
                "', '" + user.getUsername() + "', '" + user.getStream() + "', '" + user.getCombination() + "', '" + user.getDegree() +
                "', '" + user.getStatus() + "')";
        System.out.println(query);
        common.saveDataToDb(query);
    }

    private void populateUser(ResultSet resultSet, List<User> userList) {
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("USER_ID"));
                user.setName(resultSet.getString("NAME"));
                user.setBatch(resultSet.getString("BATCH"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setStream(resultSet.getString("STREAM"));
                userList.add(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
