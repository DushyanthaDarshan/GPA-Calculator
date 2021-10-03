package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.awt.event.*;
import java.util.Objects;
import java.awt.event.*;
import java.util.Objects;

public class SignupPage {

    private JFrame frame;
    private JTextField nameTextFieldRegister;
    private JTextField usernameTextFieldRegister;
    private JTextField indexNumberTextFieldRegister;
    private JComboBox batchSelectRegister;
    private JComboBox streamSelectRegister;
    private JComboBox combinationSelectRegister;
    private JComboBox degreeSelectRegister;
    private JPasswordField passwordTextFieldRegister;
    private JPasswordField confirmPasswordTextFieldRegister;
    private JLabel nameRegisterError;
    private JLabel usernameRegisterError;
    private JLabel indexNumberRegisterError;
    private JLabel batchRegisterError;
    private JLabel streamRegisterError;
    private JLabel combinationRegisterError;
    private JLabel degreeRegisterError;
    private JLabel passwordRegisterError;
    private JLabel confirmPasswordRegisterError;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignupPage window = new SignupPage();
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
    public SignupPage() {
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

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1000, 750);
        panel.add(panel_1);
        panel_1.setLayout(null);

        //left side
        JLabel leftSideApplicationName = new JLabel("");
        leftSideApplicationName.setForeground(new Color(255, 255, 255));
        leftSideApplicationName.setBackground(new Color(0, 0, 128));
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/GPAcal1.png").getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT));
        leftSideApplicationName.setIcon(imageIcon);
        leftSideApplicationName.setFont(new Font("Dialog", Font.BOLD, 22));
        leftSideApplicationName.setHorizontalAlignment(SwingConstants.CENTER);
        leftSideApplicationName.setBounds(20, 170, 350, 350);
        panel_1.add(leftSideApplicationName);

        //name related
        JLabel signupPageMainName = new JLabel("Sign Up");
        signupPageMainName.setBounds(640, 35, 200, 45);
        signupPageMainName.setForeground(new Color(255, 255, 255));
        signupPageMainName.setBackground(new Color(255, 255, 255));
        signupPageMainName.setFont(new Font("Dialog", Font.BOLD, 30));
        panel_1.add(signupPageMainName);

        //name related
        JLabel nameLabelForRegister = new JLabel("Name : ");
        nameLabelForRegister.setBounds(420, 110, 90, 15);
        nameLabelForRegister.setForeground(new Color(255, 255, 255));
        nameLabelForRegister.setBackground(new Color(255, 255, 255));
        nameLabelForRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(nameLabelForRegister);

        nameTextFieldRegister = new JTextField();
        nameTextFieldRegister.setBounds(535, 110, 400, 25);
        nameTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        nameTextFieldRegister.setForeground(new Color(255, 255, 255));
        nameTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        nameTextFieldRegister.setBackground(new Color(0, 46, 98));
        nameTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(nameTextFieldRegister);
        nameTextFieldRegister.setColumns(10);

        nameRegisterError = new JLabel("");
        nameRegisterError.setBounds(420, 140, 500, 15);
        nameRegisterError.setForeground(new Color(255, 0, 0));
        nameRegisterError.setBackground(new Color(255, 255, 255));
        nameRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(nameRegisterError);

        //username related
        JLabel usernameLabelRegister = new JLabel("Username : ");
        usernameLabelRegister.setBounds(420, 160, 200, 15);
        usernameLabelRegister.setForeground(new Color(255, 255, 255));
        usernameLabelRegister.setBackground(new Color(255, 255, 255));
        usernameLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(usernameLabelRegister);

        usernameTextFieldRegister = new JTextField();
        usernameTextFieldRegister.setBounds(535, 160, 400, 25);
        usernameTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        usernameTextFieldRegister.setForeground(new Color(255, 255, 255));
        usernameTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        usernameTextFieldRegister.setBackground(new Color(0, 46, 98));
        usernameTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(usernameTextFieldRegister);
        usernameTextFieldRegister.setColumns(10);

        usernameRegisterError = new JLabel("");
        usernameRegisterError.setBounds(420, 190, 500, 15);
        usernameRegisterError.setForeground(new Color(255, 0, 0));
        usernameRegisterError.setBackground(new Color(255, 255, 255));
        usernameRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(usernameRegisterError);

        //index number related
        JLabel indexNumberLabelRegister = new JLabel("Index No : ");
        indexNumberLabelRegister.setBounds(420, 210, 90, 15);
        indexNumberLabelRegister.setForeground(new Color(255, 255, 255));
        indexNumberLabelRegister.setBackground(new Color(255, 255, 255));
        indexNumberLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(indexNumberLabelRegister);

        indexNumberTextFieldRegister = new JTextField();
        indexNumberTextFieldRegister.setBounds(535, 210, 400, 25);
        indexNumberTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        indexNumberTextFieldRegister.setForeground(new Color(255, 255, 255));
        indexNumberTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        indexNumberTextFieldRegister.setBackground(new Color(0, 46, 98));
        indexNumberTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(indexNumberTextFieldRegister);
        indexNumberTextFieldRegister.setColumns(10);

        indexNumberRegisterError = new JLabel("");
        indexNumberRegisterError.setBounds(420, 240, 500, 15);
        indexNumberRegisterError.setForeground(new Color(255, 0, 0));
        indexNumberRegisterError.setBackground(new Color(255, 255, 255));
        indexNumberRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(indexNumberRegisterError);

        //batch related
        JLabel batchLabelRegister = new JLabel("Batch : ");
        batchLabelRegister.setBounds(420, 270, 90, 15);
        batchLabelRegister.setForeground(new Color(255, 255, 255));
        batchLabelRegister.setBackground(new Color(255, 255, 255));
        batchLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(batchLabelRegister);

        String[] batchList = {"2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021"};
        batchSelectRegister = new JComboBox(batchList);
        batchSelectRegister.setSelectedIndex(2);
        batchSelectRegister.setEditable(true);
        batchSelectRegister.setBounds(535, 270, 400, 22);
        batchSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        batchSelectRegister.setForeground(new Color(255, 255, 255));
//        batchTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        batchSelectRegister.setBackground(new Color(0, 46, 98));
        batchSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(batchSelectRegister);
//        batchTextFieldRegister.setColumns(10);

        batchRegisterError = new JLabel("");
        batchRegisterError.setBounds(420, 300, 500, 15);
        batchRegisterError.setForeground(new Color(255, 0, 0));
        batchRegisterError.setBackground(new Color(255, 255, 255));
        batchRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(batchRegisterError);

        //stream related
        JLabel streamLabelRegister = new JLabel("Stream : ");
        streamLabelRegister.setBounds(420, 320, 90, 15);
        streamLabelRegister.setForeground(new Color(255, 255, 255));
        streamLabelRegister.setBackground(new Color(255, 255, 255));
        streamLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(streamLabelRegister);

        String[] streamList = {"Physical Science", "Biological Science ", "Industrial Statistics & Mathematical Finance"};
        streamSelectRegister = new JComboBox(streamList);
        streamSelectRegister.setEditable(true);
        streamSelectRegister.setSelectedIndex(0);
        streamSelectRegister.setBounds(535, 320, 400, 22);
        streamSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        streamSelectRegister.setForeground(new Color(255, 255, 255));
//        streamTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        streamSelectRegister.setBackground(new Color(0, 46, 98));
        streamSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(streamSelectRegister);
//        streamTextFieldRegister.setColumns(10);

        streamRegisterError = new JLabel("");
        streamRegisterError.setBounds(420, 350, 500, 15);
        streamRegisterError.setForeground(new Color(255, 0, 0));
        streamRegisterError.setBackground(new Color(255, 255, 255));
        streamRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(streamRegisterError);

        //Combination related
        JLabel combinationLabelRegister = new JLabel("Combination : ");
        combinationLabelRegister.setBounds(420, 370, 110, 15);
        combinationLabelRegister.setForeground(new Color(255, 255, 255));
        combinationLabelRegister.setBackground(new Color(255, 255, 255));
        combinationLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(combinationLabelRegister);

        String[] combinationList = {"P1", "P2", "P3", "P4", "P5", "P6"};
        combinationSelectRegister = new JComboBox(combinationList);
        combinationSelectRegister.setSelectedIndex(1);
        combinationSelectRegister.setEditable(true);
        combinationSelectRegister.setBounds(535, 370, 400, 22);
        combinationSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        combinationSelectRegister.setForeground(new Color(255, 255, 255));
//        combinationSelectRegister.setCaretColor(new Color(255, 255, 255));
        combinationSelectRegister.setBackground(new Color(0, 46, 98));
        combinationSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(combinationSelectRegister);
//        combinationSelectRegister.setColumns(10);

        combinationRegisterError = new JLabel("");
        combinationRegisterError.setBounds(420, 400, 500, 15);
        combinationRegisterError.setForeground(new Color(255, 0, 0));
        combinationRegisterError.setBackground(new Color(255, 255, 255));
        combinationRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(combinationRegisterError);

        //Degree related
        JLabel degreeLabelRegister = new JLabel("Degree : ");
        degreeLabelRegister.setBounds(420, 420, 110, 15);
        degreeLabelRegister.setForeground(new Color(255, 255, 255));
        degreeLabelRegister.setBackground(new Color(255, 255, 255));
        degreeLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(degreeLabelRegister);

        String[] degreeList = {"3G", "Physics(Hons)", "Engineering Physics(Hons)", "Computational Physics(Hons)", "Chemistry(Hons)",
                "Pharmacy(Hons)", "Computational Chemistry(Hons)", "Mathematics(Hons)", "Finance, Business & Computational Mathematics",
                "Statistics(Hons)", "Information Technology & Management(Hons)", "Applied Statistics(Hons)"};
        degreeSelectRegister = new JComboBox(degreeList);
        degreeSelectRegister.setSelectedIndex(10);
        degreeSelectRegister.setEditable(true);
        degreeSelectRegister.setBounds(535, 420, 400, 22);
        degreeSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        degreeSelectRegister.setForeground(new Color(255, 255, 255));
//        degreeSelectRegister.setCaretColor(new Color(255, 255, 255));
        degreeSelectRegister.setBackground(new Color(0, 46, 98));
        degreeSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(degreeSelectRegister);
//        degreeSelectRegister.setColumns(10);

        degreeRegisterError = new JLabel("");
        degreeRegisterError.setBounds(420, 450, 500, 15);
        degreeRegisterError.setForeground(new Color(255, 0, 0));
        degreeRegisterError.setBackground(new Color(255, 255, 255));
        degreeRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(degreeRegisterError);

        //Password related
        JLabel passwordLabelRegister = new JLabel("Password : ");
        passwordLabelRegister.setBounds(420, 470, 110, 15);
        passwordLabelRegister.setForeground(new Color(255, 255, 255));
        passwordLabelRegister.setBackground(new Color(255, 255, 255));
        passwordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(passwordLabelRegister);

        passwordTextFieldRegister = new JPasswordField();
        passwordTextFieldRegister.setBounds(535, 470, 400, 25);
        passwordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        passwordTextFieldRegister.setForeground(new Color(255, 255, 255));
        passwordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        passwordTextFieldRegister.setBackground(new Color(0, 46, 98));
        passwordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(passwordTextFieldRegister);
        passwordTextFieldRegister.setColumns(10);

        passwordRegisterError = new JLabel("");
        passwordRegisterError.setBounds(420, 500, 500, 15);
        passwordRegisterError.setForeground(new Color(255, 0, 0));
        passwordRegisterError.setBackground(new Color(255, 255, 255));
        passwordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(passwordRegisterError);

        //Confirm Password related
        JLabel confirmPasswordLabelRegister = new JLabel("Confirm Pw : ");
        confirmPasswordLabelRegister.setBounds(420, 520, 110, 15);
        confirmPasswordLabelRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setBackground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(confirmPasswordLabelRegister);

        confirmPasswordTextFieldRegister = new JPasswordField();
        confirmPasswordTextFieldRegister.setBounds(535, 520, 400, 25);
        confirmPasswordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        confirmPasswordTextFieldRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setBackground(new Color(0, 46, 98));
        confirmPasswordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(confirmPasswordTextFieldRegister);
        confirmPasswordTextFieldRegister.setColumns(10);

        confirmPasswordRegisterError = new JLabel("");
        confirmPasswordRegisterError.setBounds(420, 550, 500, 15);
        confirmPasswordRegisterError.setForeground(new Color(255, 0, 0));
        confirmPasswordRegisterError.setBackground(new Color(255, 255, 255));
        confirmPasswordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(confirmPasswordRegisterError);

        //Register Button
        JButton userRegisterButton = new JButton("Register");
        userRegisterButton.setBounds(630, 570, 117, 30);
        userRegisterButton.setFont(new Font("Dialog", Font.BOLD, 16));
        userRegisterButton.setForeground(Color.WHITE);
        userRegisterButton.setBackground(new Color(0, 46, 98));
        panel_1.add(userRegisterButton);

        //Already have an account related
        JLabel alreadyHaveAnAccountPart1 = new JLabel("Already have an account? ");
        alreadyHaveAnAccountPart1.setBounds(555, 618, 200, 15);
        alreadyHaveAnAccountPart1.setForeground(new Color(255, 255, 255));
        alreadyHaveAnAccountPart1.setBackground(new Color(255, 255, 255));
        alreadyHaveAnAccountPart1.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(alreadyHaveAnAccountPart1);

        JLabel alreadyHaveAnAccountPart2 = new JLabel("Sign in ");
        alreadyHaveAnAccountPart2.setBounds(740, 615, 110, 20);
        alreadyHaveAnAccountPart2.setForeground(new Color(255, 255, 255));
        alreadyHaveAnAccountPart2.setBackground(new Color(255, 255, 255));
        alreadyHaveAnAccountPart2.setFont(new Font("Dialog", Font.BOLD, 18));
        panel_1.add(alreadyHaveAnAccountPart2);

        //Main two labels
        JLabel leftSideBackground = new JLabel("");
        leftSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
        leftSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        leftSideBackground.setBounds(400, 0, 600, 750);
        panel_1.add(leftSideBackground);

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 400, 750);
        panel_1.add(rightSideBackground);

        streamSelectRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == streamSelectRegister) {
                    if (!Objects.equals(streamSelectRegister.getSelectedItem(), "Physical Science")) {
                        streamRegisterError.setBounds(420, 281, 500, 15);

                        combinationLabelRegister.setBounds(52, 52, 110, 15);
                        combinationLabelRegister.setVisible(false);
                        combinationSelectRegister.setBounds(52, 52, 400, 19);
                        combinationSelectRegister.setVisible(false);
                        combinationRegisterError.setBounds(52, 52, 500, 15);
                        combinationRegisterError.setVisible(false);

                        degreeLabelRegister.setBounds(420, 298, 110, 15);
                        degreeSelectRegister.setBounds(535, 298, 400, 19);
                        degreeRegisterError.setBounds(420, 328, 500, 15);

                        passwordLabelRegister.setBounds(420, 342, 110, 15);
                        passwordTextFieldRegister.setBounds(535, 342, 400, 19);
                        passwordRegisterError.setBounds(420, 372, 500, 15);

                        confirmPasswordLabelRegister.setBounds(420, 389, 110, 15);
                        confirmPasswordTextFieldRegister.setBounds(535, 389, 400, 19);
                        confirmPasswordRegisterError.setBounds(420, 419, 500, 15);

                        userRegisterButton.setBounds(630, 436, 117, 30);
                        alreadyHaveAnAccountPart1.setBounds(540, 486, 200, 15);
                        alreadyHaveAnAccountPart2.setBounds(740, 486, 110, 20);
                    } else {
                        streamRegisterError.setBounds(420, 281, 500, 15);

                        combinationLabelRegister.setBounds(420, 298, 110, 15);
                        combinationLabelRegister.setVisible(true);
                        combinationSelectRegister.setBounds(535, 298, 400, 19);
                        combinationSelectRegister.setVisible(true);
                        combinationRegisterError.setBounds(420, 328, 500, 15);
                        combinationRegisterError.setVisible(true);

                        degreeLabelRegister.setBounds(420, 345, 110, 15);
                        degreeSelectRegister.setBounds(535, 345, 400, 19);
                        degreeRegisterError.setBounds(420, 375, 500, 15);

                        passwordLabelRegister.setBounds(420, 392, 110, 15);
                        passwordTextFieldRegister.setBounds(535, 392, 400, 19);
                        passwordRegisterError.setBounds(420, 422, 500, 15);

                        confirmPasswordLabelRegister.setBounds(420, 439, 110, 15);
                        confirmPasswordTextFieldRegister.setBounds(535, 439, 400, 19);
                        confirmPasswordRegisterError.setBounds(420, 469, 500, 15);

                        userRegisterButton.setBounds(630, 509, 117, 30);
                        alreadyHaveAnAccountPart1.setBounds(540, 559, 200, 15);
                        alreadyHaveAnAccountPart2.setBounds(740, 559, 110, 20);
                    }
                }
            }
        });

        userRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GpaCalDao gpaCalDao = new GpaCalDaoImpl();

                String name = nameTextFieldRegister.getText();
                String username = usernameTextFieldRegister.getText();
                String indexNumber = indexNumberTextFieldRegister.getText();
                String batch = (String) batchSelectRegister.getSelectedItem();
                String stream = (String) streamSelectRegister.getSelectedItem();
                String combination = (String) combinationSelectRegister.getSelectedItem();
                String degree = (String) degreeSelectRegister.getSelectedItem();
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
                user.setCreatedTs(Timestamp.valueOf(LocalDateTime.now()));

                clearErrorsInErrorFields();
                boolean status = validateInputs(user, gpaCalDao, confirmPw, redColor);
                if (status) {
                    gpaCalDao.saveUserDetails(user);
                    User savedUser = gpaCalDao.getUserDetailsByUsername(username);
                    if (savedUser != null) {
                        clearTextFields();
                        JOptionPane.showMessageDialog(frame, "Successfully registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Successfully registered");
                        frame.dispose();
                        new HomePage(user.getUserId());
                    }
                }
            }
        });

        //already have an account
        alreadyHaveAnAccountPart2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new LoginPage();
            }
        });
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

    private void clearTextFields() {
        nameTextFieldRegister.setText("");
        usernameTextFieldRegister.setText("");
        indexNumberTextFieldRegister.setText("");
        batchSelectRegister.setSelectedIndex(2);
        streamSelectRegister.setSelectedIndex(0);
        combinationSelectRegister.setSelectedIndex(1);
        degreeSelectRegister.setSelectedIndex(10);
        passwordTextFieldRegister.setText("");
        confirmPasswordTextFieldRegister.setText("");
    }

    private boolean validateInputs(User user, GpaCalDao gpaCalDao, String confirmPw, Color redColor) {
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
            User userFromDb = gpaCalDao.getUserDetailsByUsername(user.getUsername());
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
            User userFromDb = gpaCalDao.getUserDetailsByIndexNumber(user.getIndexNumber());
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
}
