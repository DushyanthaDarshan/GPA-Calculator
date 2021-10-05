package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Student;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminMyAccount {

    private JFrame frame;
    private Long userId;
    private JTable table;
    private DefaultTableModel model;
    private List<Student> allStudents;
    private JTextField nameTextFieldRegister;
    private JTextField oldPwTextField;
    private JPasswordField passwordTextFieldRegister;
    private JPasswordField confirmPasswordTextFieldRegister;
    private JLabel nameRegisterError;
    private JLabel oldPwError;
    private JLabel passwordRegisterError;
    private JLabel confirmPasswordRegisterError;

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminMyAccount window = new AdminMyAccount(1l);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AdminMyAccount(Long userId) {
        this.userId = userId;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 750);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        //left side
        JLabel leftSideApplicationName = new JLabel("");
        leftSideApplicationName.setForeground(new Color(255, 255, 255));
        leftSideApplicationName.setBackground(new Color(0, 0, 128));
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/GPAcal1.png").getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT));
        leftSideApplicationName.setIcon(imageIcon);
        leftSideApplicationName.setFont(new Font("Dialog", Font.BOLD, 22));
        leftSideApplicationName.setHorizontalAlignment(SwingConstants.CENTER);
        leftSideApplicationName.setBounds(20, 170, 350, 350);
        frame.getContentPane().add(leftSideApplicationName);

        JLabel signupPageMainName = new JLabel("My Account");
        signupPageMainName.setBounds(640, 35, 200, 45);
        signupPageMainName.setForeground(new Color(255, 255, 255));
        signupPageMainName.setBackground(new Color(255, 255, 255));
        signupPageMainName.setFont(new Font("Dialog", Font.BOLD, 30));
        frame.getContentPane().add(signupPageMainName);

        //name related
        JLabel nameLabelForRegister = new JLabel("Name : ");
        nameLabelForRegister.setBounds(420, 140, 90, 15);
        nameLabelForRegister.setForeground(new Color(255, 255, 255));
        nameLabelForRegister.setBackground(new Color(255, 255, 255));
        nameLabelForRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(nameLabelForRegister);

        nameTextFieldRegister = new JTextField();
        nameTextFieldRegister.setBounds(535, 140, 400, 25);
        nameTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        nameTextFieldRegister.setForeground(new Color(255, 255, 255));
        nameTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        nameTextFieldRegister.setBackground(new Color(0, 46, 98));
        nameTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        nameTextFieldRegister.setColumns(10);
        frame.getContentPane().add(nameTextFieldRegister);

        nameRegisterError = new JLabel("");
        nameRegisterError.setBounds(420, 180, 500, 15);
        nameRegisterError.setForeground(new Color(255, 0, 0));
        nameRegisterError.setBackground(new Color(255, 255, 255));
        nameRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(nameRegisterError);

        //old password related
        JLabel oldPassword = new JLabel("Old Pw : ");
        oldPassword.setBounds(420, 220, 90, 15);
        oldPassword.setForeground(new Color(255, 255, 255));
        oldPassword.setBackground(new Color(255, 255, 255));
        oldPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(oldPassword);

        oldPwTextField = new JTextField();
        oldPwTextField.setBounds(535, 220, 400, 25);
        oldPwTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        oldPwTextField.setForeground(new Color(255, 255, 255));
        oldPwTextField.setCaretColor(new Color(255, 255, 255));
        oldPwTextField.setBackground(new Color(0, 46, 98));
        oldPwTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
        oldPwTextField.setColumns(10);
        frame.getContentPane().add(oldPwTextField);

        oldPwError = new JLabel("");
        oldPwError.setBounds(420, 260, 500, 15);
        oldPwError.setForeground(new Color(255, 0, 0));
        oldPwError.setBackground(new Color(255, 255, 255));
        oldPwError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(oldPwError);

        //Password related
        JLabel passwordLabelRegister = new JLabel("New Pw : ");
        passwordLabelRegister.setBounds(420, 300, 110, 15);
        passwordLabelRegister.setForeground(new Color(255, 255, 255));
        passwordLabelRegister.setBackground(new Color(255, 255, 255));
        passwordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(passwordLabelRegister);

        passwordTextFieldRegister = new JPasswordField();
        passwordTextFieldRegister.setBounds(535, 300, 400, 25);
        passwordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        passwordTextFieldRegister.setForeground(new Color(255, 255, 255));
        passwordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        passwordTextFieldRegister.setBackground(new Color(0, 46, 98));
        passwordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        passwordTextFieldRegister.setColumns(10);
        frame.getContentPane().add(passwordTextFieldRegister);

        passwordRegisterError = new JLabel("");
        passwordRegisterError.setBounds(420, 340, 500, 15);
        passwordRegisterError.setForeground(new Color(255, 0, 0));
        passwordRegisterError.setBackground(new Color(255, 255, 255));
        passwordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(passwordRegisterError);

        //Confirm Password related
        JLabel confirmPasswordLabelRegister = new JLabel("Re-enter new Pw : ");
        confirmPasswordLabelRegister.setBounds(420, 380, 110, 15);
        confirmPasswordLabelRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setBackground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(confirmPasswordLabelRegister);

        confirmPasswordTextFieldRegister = new JPasswordField();
        confirmPasswordTextFieldRegister.setBounds(535, 380, 400, 25);
        confirmPasswordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        confirmPasswordTextFieldRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setBackground(new Color(0, 46, 98));
        confirmPasswordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        confirmPasswordTextFieldRegister.setColumns(10);
        frame.getContentPane().add(confirmPasswordTextFieldRegister);

        confirmPasswordRegisterError = new JLabel("");
        confirmPasswordRegisterError.setBounds(420, 420, 500, 15);
        confirmPasswordRegisterError.setForeground(new Color(255, 0, 0));
        confirmPasswordRegisterError.setBackground(new Color(255, 255, 255));
        confirmPasswordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(confirmPasswordRegisterError);

        //Update Button
        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(700, 480, 117, 30);
        updateBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(new Color(0, 46, 98));
        frame.getContentPane().add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.BLACK);
        backBtn.setFont(new Font("Sitka Text", Font.BOLD, 18));
        backBtn.setBackground(new Color(239, 205, 68));
        backBtn.setBounds(500, 480, 100, 30);
        frame.getContentPane().add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdminHome(userId);
                frame.dispose();
            }
        });

        //Main two labels
        JLabel leftSideBackground = new JLabel("");
        leftSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
        leftSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        leftSideBackground.setBounds(400, 0, 600, 750);
        frame.getContentPane().add(leftSideBackground);

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 400, 750);
        frame.getContentPane().add(rightSideBackground);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GpaCalDao gpaCalDao = new GpaCalDaoImpl();
                Student userFromDb = gpaCalDao.getUserDetailsByUserId(userId);

                String name = nameTextFieldRegister.getText();
                String oldPw = oldPwTextField.getText();
                String newPw = passwordTextFieldRegister.getText();
                String confirmPw = confirmPasswordTextFieldRegister.getText();

                Color redColor = new Color(255, 0, 0);
                Student student = new Student();
                student.setName(name);
                student.setPassword(newPw);
                student.setStatus("ACTIVE");

                clearErrorsInErrorFields();
                boolean status = validateInputs(student, confirmPw, userFromDb.getPassword(), oldPw, redColor);
                if (status) {
                    gpaCalDao.updateUser(student);
                    Student updatedUserFromDb = gpaCalDao.getUserDetailsByUserId(userId);
                    if (updatedUserFromDb.getName().equals(name) || updatedUserFromDb.getPassword().equals(newPw)) {
                        clearTextFields();
                        JOptionPane.showMessageDialog(frame, "Successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void clearErrorsInErrorFields() {
        nameRegisterError.setVisible(false);
        oldPwError.setVisible(false);
        passwordRegisterError.setVisible(false);
        confirmPasswordRegisterError.setVisible(false);
    }

    private void clearTextFields() {
        nameTextFieldRegister.setText("");
        oldPwTextField.setText("");
        passwordTextFieldRegister.setText("");
        confirmPasswordTextFieldRegister.setText("");
    }

    private boolean validateInputs(Student student, String confirmPw, String pwFromDb, String oldPw, Color redColor) {
        boolean isValidInputs = true;
        if (StringUtils.isBlank(student.getName())) {
            nameRegisterError.setText("Name should not be empty");
            nameRegisterError.setForeground(redColor);
            nameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (student.getName().length() > 500) {
            nameRegisterError.setText("Name length should not be more than 500 characters");
            nameRegisterError.setForeground(redColor);
            nameRegisterError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isBlank(student.getPassword())) {
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
        if (pwFromDb.equals(oldPw)) {
            oldPwError.setText("Old password should be matched");
            oldPwError.setForeground(redColor);
            oldPwError.setVisible(true);
            isValidInputs = false;
        }
        if (StringUtils.isNotBlank(student.getPassword()) && StringUtils.isNotBlank(confirmPw)) {
            if (!student.getPassword().equals(confirmPw)) {
                passwordRegisterError.setText("Passwords should be matched");
                passwordRegisterError.setForeground(redColor);
                passwordRegisterError.setVisible(true);
                isValidInputs = false;
            }
        }
        return isValidInputs;
    }
}
