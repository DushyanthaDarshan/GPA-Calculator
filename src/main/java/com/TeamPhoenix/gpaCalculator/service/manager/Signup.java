package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup {

    private JLabel signupTitle;
    private JTextField nameTextFieldRegister;
    private JTextField usernameTextFieldRegister;
    private JTextField indexNumberTextFieldRegister;
    private JTextField batchTextFieldRegister;
    private JTextField streamTextFieldRegister;
    private JTextField combinationTextFieldRegister;
    private JTextField degreeTextFieldRegister;
    private JTextField passwordTextFieldRegister;
    private JTextField confirmPasswordTextFieldRegister;
    private JButton userRegisterButton;
    private JLabel nameLabelForRegister;
    private JLabel usernameLabelRegister;
    private JLabel indexNumberLabelRegister;
    private JLabel batchLabelRegister;
    private JLabel streamLabelRegister;
    private JLabel combinationLabelRegister;
    private JLabel degreeLabelRegister;
    private JLabel passwordLabelRegister;
    private JLabel confirmPasswordLabelRegister;
    private JPanel signupPage;
    private JLabel signInLabelRegisterPage;
    private JLabel nameRegisterError;
    private JLabel usernameRegisterError;
    private JLabel indexNumberRegisterError;
    private JLabel batchRegisterError;
    private JLabel streamRegisterError;
    private JLabel combinationRegisterError;
    private JLabel degreeRegisterError;
    private JLabel passwordRegisterError;
    private JLabel confirmPasswordRegisterError;

    public Signup() {

        userRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GpaDao gpaDao = new GpaDaoImpl();

                String name = nameTextFieldRegister.getText();
                String username = usernameTextFieldRegister.getText();
                String indexNumber = indexNumberTextFieldRegister.getText();
                String batch = batchTextFieldRegister.getText();
                String stream = streamTextFieldRegister.getText();
                String combination = combinationTextFieldRegister.getText();
                String degree = degreeTextFieldRegister.getText();
                String pw = passwordTextFieldRegister.getText();
                String confirmPw = confirmPasswordTextFieldRegister.getText();

                Color redColor = new Color(255, 0, 0);
                User user = new User();
                user.setName(name);
                user.setIndexNumber(indexNumber);
                user.setUsername(username);
                user.setCombination(combination);
                user.setPassword(pw);
                user.setDegree(degree);
                user.setStream(stream);
                user.setBatch(batch);
                user.setStatus("ACTIVE");

                clearErrorsInErrorFields();
                boolean status = validateInputs(user, gpaDao, confirmPw, redColor);
                if (status) {
                    gpaDao.saveUserDetails(user);
                    User savedUser = gpaDao.getUserDetailsByUsername(username);
                    if (savedUser != null) {
                        clearTextFields();
                        System.out.println("Successfully registered");
                    }
                }
            }
        });
    }

    private void clearTextFields() {
        nameTextFieldRegister.setText("");
        usernameTextFieldRegister.setText("");
        indexNumberTextFieldRegister.setText("");
        batchTextFieldRegister.setText("");
        streamTextFieldRegister.setText("");
        combinationTextFieldRegister.setText("");
        degreeTextFieldRegister.setText("");
        passwordTextFieldRegister.setText("");
        confirmPasswordTextFieldRegister.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Signup Page");
        frame.setContentPane(new Signup().signupPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Signup signup = new Signup();

//        JFrame f=new JFrame();//creating instance of JFrame
//
//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(400,500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible
    }

    private void clearErrorsInErrorFields() {
        nameRegisterError.setVisible(false);
        usernameRegisterError.setVisible(false);
        indexNumberRegisterError.setVisible(false);
        batchRegisterError.setVisible(false);
        streamRegisterError.setVisible(false);
        combinationRegisterError.setVisible(false);
        degreeRegisterError.setVisible(false);
        passwordRegisterError.setVisible(false);
        confirmPasswordRegisterError.setVisible(false);
    }

    private boolean validateInputs(User user, GpaDao gpaDao, String confirmPw, Color redColor) {
        boolean isValidInputs = true;
        if (StringUtils.isBlank(user.getName())) {
            nameRegisterError.setText("Name should not be empty");
            nameRegisterError.setForeground(redColor);
            nameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (user.getName().length() > 500) {
            nameRegisterError.setText("Name length should not be more than 500 characters");
            nameRegisterError.setForeground(redColor);
            nameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isBlank(user.getUsername())) {
            usernameRegisterError.setText("Username should not be empty");
            usernameRegisterError.setForeground(redColor);
            usernameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (user.getUsername().length() > 100) {
            usernameRegisterError.setText("Username length should not be more than 100 characters");
            usernameRegisterError.setForeground(redColor);
            usernameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isNotBlank(user.getUsername())) {
            User userFromDb = gpaDao.getUserDetailsByUsername(user.getUsername());
            if (userFromDb != null) {
                usernameRegisterError.setText("Provided username is already registered. Please try using another username");
                usernameRegisterError.setForeground(redColor);
                usernameRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        if (StringUtils.isBlank(user.getIndexNumber())) {
            indexNumberRegisterError.setText("Index number should not be empty");
            indexNumberRegisterError.setForeground(redColor);
            indexNumberRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (user.getIndexNumber().length() > 100) {
            indexNumberRegisterError.setText("Index number length should not be more than 15 characters");
            indexNumberRegisterError.setForeground(redColor);
            indexNumberRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isNotBlank(user.getIndexNumber())) {
            User userFromDb = gpaDao.getUserDetailsByIndexNumber(user.getIndexNumber());
            if (userFromDb != null) {
                indexNumberRegisterError.setText("Provided index number is already registered. Please check");
                indexNumberRegisterError.setForeground(redColor);
                indexNumberRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        if (StringUtils.isBlank(user.getBatch())) {
            batchRegisterError.setText("Batch should not be empty");
            batchRegisterError.setForeground(redColor);
            batchRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (user.getBatch().length() > 45) {
            batchRegisterError.setText("Batch length should not be more than 45 characters");
            batchRegisterError.setForeground(redColor);
            batchRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isBlank(user.getStream())) {
            streamRegisterError.setText("Stream should not be empty");
            streamRegisterError.setForeground(redColor);
            streamRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (user.getStream().length() > 500) {
            streamRegisterError.setText("Stream length should not be more than 100 characters");
            streamRegisterError.setForeground(redColor);
            streamRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isNotBlank(user.getCombination())) {
            if (user.getCombination().length() > 45) {
                combinationRegisterError.setText("Combination length should not be more than 100 characters");
                combinationRegisterError.setForeground(redColor);
                combinationRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        if (StringUtils.isNotBlank(user.getDegree())) {
            if (user.getDegree().length() > 45) {
                degreeRegisterError.setText("Degree length should not be more than 250 characters");
                degreeRegisterError.setForeground(redColor);
                degreeRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        if (StringUtils.isBlank(user.getPassword())) {
            passwordRegisterError.setText("Password should not be empty");
            passwordRegisterError.setForeground(redColor);
            passwordRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isBlank(confirmPw)) {
            confirmPasswordRegisterError.setText("Confirm password should not be empty");
            confirmPasswordRegisterError.setForeground(redColor);
            confirmPasswordRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(confirmPw)) {
            if (!user.getPassword().equals(confirmPw)) {
                passwordRegisterError.setText("Passwords should be matched");
                passwordRegisterError.setForeground(redColor);
                passwordRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        return isValidInputs;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void setData(Signup data) {
    }

    public void getData(Signup data) {
    }

    public boolean isModified(Signup data) {
        return false;
    }
}
