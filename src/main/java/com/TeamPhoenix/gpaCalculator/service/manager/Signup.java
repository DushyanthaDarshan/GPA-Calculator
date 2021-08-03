package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaDaoImpl;

public class Signup {

    public Signup() {
        GpaDao gpaDao = new GpaDaoImpl();
        User user1 = new User();
        user1.setName("Ramesh Shanuka Rubasinghe");
        user1.setBatch("2018/2019");
        user1.setUsername("rvrs12rvrs@gmail.com");
        user1.setIndexNumber("s15850");
        user1.setPassword("123456");
        user1.setStream("Physical Science");
        user1.setDegree("EP");
        user1.setCombination("P2");
        user1.setStatus("ACTIVE");
        gpaDao.saveUserDetails(user1);
        System.out.println("Done.................");
    }

    public static void main(String[] args) {
        Signup signup = new Signup();
    }
}
