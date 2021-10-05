package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Student;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentMyAccount {

    private JFrame frame;
    private Long userId;
    private JTable table;
    private DefaultTableModel model;
    private List<Student> allStudents;
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

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentMyAccount window = new StudentMyAccount(1l);
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
    public StudentMyAccount(Long userId) {
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

        JLabel pageTitle = new JLabel("Manage Students");
        pageTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pageTitle.setBounds(380, 13, 300, 40);
        pageTitle.setForeground(new Color(255, 255, 255));
        pageTitle.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(pageTitle);

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

        //name related
        JLabel signupPageMainName = new JLabel("Sign Up");
        signupPageMainName.setBounds(640, 35, 200, 45);
        signupPageMainName.setForeground(new Color(255, 255, 255));
        signupPageMainName.setBackground(new Color(255, 255, 255));
        signupPageMainName.setFont(new Font("Dialog", Font.BOLD, 30));
        frame.getContentPane().add(signupPageMainName);

        //name related
        JLabel nameLabelForRegister = new JLabel("Name : ");
        nameLabelForRegister.setBounds(420, 110, 90, 15);
        nameLabelForRegister.setForeground(new Color(255, 255, 255));
        nameLabelForRegister.setBackground(new Color(255, 255, 255));
        nameLabelForRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(nameLabelForRegister);

        nameTextFieldRegister = new JTextField();
        nameTextFieldRegister.setBounds(535, 110, 400, 25);
        nameTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        nameTextFieldRegister.setForeground(new Color(255, 255, 255));
        nameTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        nameTextFieldRegister.setBackground(new Color(0, 46, 98));
        nameTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        nameTextFieldRegister.setColumns(10);
        frame.getContentPane().add(nameTextFieldRegister);

        nameRegisterError = new JLabel("");
        nameRegisterError.setBounds(420, 140, 500, 15);
        nameRegisterError.setForeground(new Color(255, 0, 0));
        nameRegisterError.setBackground(new Color(255, 255, 255));
        nameRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(nameRegisterError);

        //username related
        JLabel usernameLabelRegister = new JLabel("Username : ");
        usernameLabelRegister.setBounds(420, 160, 200, 15);
        usernameLabelRegister.setForeground(new Color(255, 255, 255));
        usernameLabelRegister.setBackground(new Color(255, 255, 255));
        usernameLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(usernameLabelRegister);

        usernameTextFieldRegister = new JTextField();
        usernameTextFieldRegister.setBounds(535, 160, 400, 25);
        usernameTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        usernameTextFieldRegister.setForeground(new Color(255, 255, 255));
        usernameTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        usernameTextFieldRegister.setBackground(new Color(0, 46, 98));
        usernameTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        usernameTextFieldRegister.setColumns(10);
        frame.getContentPane().add(usernameTextFieldRegister);

        usernameRegisterError = new JLabel("");
        usernameRegisterError.setBounds(420, 190, 500, 15);
        usernameRegisterError.setForeground(new Color(255, 0, 0));
        usernameRegisterError.setBackground(new Color(255, 255, 255));
        usernameRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(usernameRegisterError);

        //index number related
        JLabel indexNumberLabelRegister = new JLabel("Index No : ");
        indexNumberLabelRegister.setBounds(420, 210, 90, 15);
        indexNumberLabelRegister.setForeground(new Color(255, 255, 255));
        indexNumberLabelRegister.setBackground(new Color(255, 255, 255));
        indexNumberLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(indexNumberLabelRegister);

        indexNumberTextFieldRegister = new JTextField();
        indexNumberTextFieldRegister.setBounds(535, 210, 400, 25);
        indexNumberTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        indexNumberTextFieldRegister.setForeground(new Color(255, 255, 255));
        indexNumberTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        indexNumberTextFieldRegister.setBackground(new Color(0, 46, 98));
        indexNumberTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        indexNumberTextFieldRegister.setColumns(10);
        frame.getContentPane().add(indexNumberTextFieldRegister);

        indexNumberRegisterError = new JLabel("");
        indexNumberRegisterError.setBounds(420, 240, 500, 15);
        indexNumberRegisterError.setForeground(new Color(255, 0, 0));
        indexNumberRegisterError.setBackground(new Color(255, 255, 255));
        indexNumberRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(indexNumberRegisterError);

        //batch related
        JLabel batchLabelRegister = new JLabel("Batch : ");
        batchLabelRegister.setBounds(420, 270, 90, 15);
        batchLabelRegister.setForeground(new Color(255, 255, 255));
        batchLabelRegister.setBackground(new Color(255, 255, 255));
        batchLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(batchLabelRegister);

        String[] batchList = {"2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021"};
        batchSelectRegister = new JComboBox(batchList);
        batchSelectRegister.setSelectedIndex(2);
        batchSelectRegister.setEditable(true);
        batchSelectRegister.setBounds(535, 270, 400, 22);
        batchSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        batchSelectRegister.setForeground(new Color(255, 255, 255));
        batchSelectRegister.setBackground(new Color(0, 46, 98));
        batchSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(batchSelectRegister);

        batchRegisterError = new JLabel("");
        batchRegisterError.setBounds(420, 300, 500, 15);
        batchRegisterError.setForeground(new Color(255, 0, 0));
        batchRegisterError.setBackground(new Color(255, 255, 255));
        batchRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(batchRegisterError);

        //stream related
        JLabel streamLabelRegister = new JLabel("Stream : ");
        streamLabelRegister.setBounds(420, 320, 90, 15);
        streamLabelRegister.setForeground(new Color(255, 255, 255));
        streamLabelRegister.setBackground(new Color(255, 255, 255));
        streamLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(streamLabelRegister);

        String[] streamList = {"Physical Science", "Biological Science ", "Industrial Statistics & Mathematical Finance"};
        streamSelectRegister = new JComboBox(streamList);
        streamSelectRegister.setEditable(true);
        streamSelectRegister.setSelectedIndex(0);
        streamSelectRegister.setBounds(535, 320, 400, 22);
        streamSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        streamSelectRegister.setForeground(new Color(255, 255, 255));
        streamSelectRegister.setBackground(new Color(0, 46, 98));
        streamSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(streamSelectRegister);

        streamRegisterError = new JLabel("");
        streamRegisterError.setBounds(420, 350, 500, 15);
        streamRegisterError.setForeground(new Color(255, 0, 0));
        streamRegisterError.setBackground(new Color(255, 255, 255));
        streamRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(streamRegisterError);

        //Combination related
        JLabel combinationLabelRegister = new JLabel("Combination : ");
        combinationLabelRegister.setBounds(420, 370, 110, 15);
        combinationLabelRegister.setForeground(new Color(255, 255, 255));
        combinationLabelRegister.setBackground(new Color(255, 255, 255));
        combinationLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(combinationLabelRegister);

        String[] combinationList = {"P1", "P2", "P3", "P4", "P5", "P6"};
        combinationSelectRegister = new JComboBox(combinationList);
        combinationSelectRegister.setSelectedIndex(1);
        combinationSelectRegister.setEditable(true);
        combinationSelectRegister.setBounds(535, 370, 400, 22);
        combinationSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        combinationSelectRegister.setForeground(new Color(255, 255, 255));
        combinationSelectRegister.setBackground(new Color(0, 46, 98));
        combinationSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(combinationSelectRegister);

        combinationRegisterError = new JLabel("");
        combinationRegisterError.setBounds(420, 400, 500, 15);
        combinationRegisterError.setForeground(new Color(255, 0, 0));
        combinationRegisterError.setBackground(new Color(255, 255, 255));
        combinationRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(combinationRegisterError);

        //Degree related
        JLabel degreeLabelRegister = new JLabel("Degree : ");
        degreeLabelRegister.setBounds(420, 420, 110, 15);
        degreeLabelRegister.setForeground(new Color(255, 255, 255));
        degreeLabelRegister.setBackground(new Color(255, 255, 255));
        degreeLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(degreeLabelRegister);

        String[] degreeList = {"3G", "Physics(Hons)", "Engineering Physics(Hons)", "Computational Physics(Hons)", "Chemistry(Hons)",
                "Pharmacy(Hons)", "Computational Chemistry(Hons)", "Mathematics(Hons)", "Finance, Business & Computational Mathematics",
                "Statistics(Hons)", "Information Technology & Management(Hons)", "Applied Statistics(Hons)"};
        degreeSelectRegister = new JComboBox(degreeList);
        degreeSelectRegister.setSelectedIndex(10);
        degreeSelectRegister.setEditable(true);
        degreeSelectRegister.setBounds(535, 420, 400, 22);
        degreeSelectRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        degreeSelectRegister.setForeground(new Color(255, 255, 255));
        degreeSelectRegister.setBackground(new Color(0, 46, 98));
        degreeSelectRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(degreeSelectRegister);

        degreeRegisterError = new JLabel("");
        degreeRegisterError.setBounds(420, 450, 500, 15);
        degreeRegisterError.setForeground(new Color(255, 0, 0));
        degreeRegisterError.setBackground(new Color(255, 255, 255));
        degreeRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(degreeRegisterError);

        //Password related
        JLabel passwordLabelRegister = new JLabel("Password : ");
        passwordLabelRegister.setBounds(420, 470, 110, 15);
        passwordLabelRegister.setForeground(new Color(255, 255, 255));
        passwordLabelRegister.setBackground(new Color(255, 255, 255));
        passwordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(passwordLabelRegister);

        passwordTextFieldRegister = new JPasswordField();
        passwordTextFieldRegister.setBounds(535, 470, 400, 25);
        passwordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        passwordTextFieldRegister.setForeground(new Color(255, 255, 255));
        passwordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        passwordTextFieldRegister.setBackground(new Color(0, 46, 98));
        passwordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        passwordTextFieldRegister.setColumns(10);
        frame.getContentPane().add(passwordTextFieldRegister);

        passwordRegisterError = new JLabel("");
        passwordRegisterError.setBounds(420, 500, 500, 15);
        passwordRegisterError.setForeground(new Color(255, 0, 0));
        passwordRegisterError.setBackground(new Color(255, 255, 255));
        passwordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(passwordRegisterError);

        //Confirm Password related
        JLabel confirmPasswordLabelRegister = new JLabel("Confirm Pw : ");
        confirmPasswordLabelRegister.setBounds(420, 520, 110, 15);
        confirmPasswordLabelRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setBackground(new Color(255, 255, 255));
        confirmPasswordLabelRegister.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(confirmPasswordLabelRegister);

        confirmPasswordTextFieldRegister = new JPasswordField();
        confirmPasswordTextFieldRegister.setBounds(535, 520, 400, 25);
        confirmPasswordTextFieldRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        confirmPasswordTextFieldRegister.setForeground(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setCaretColor(new Color(255, 255, 255));
        confirmPasswordTextFieldRegister.setBackground(new Color(0, 46, 98));
        confirmPasswordTextFieldRegister.setFont(new Font("Dialog", Font.PLAIN, 14));
        confirmPasswordTextFieldRegister.setColumns(10);
        frame.getContentPane().add(confirmPasswordTextFieldRegister);

        confirmPasswordRegisterError = new JLabel("");
        confirmPasswordRegisterError.setBounds(420, 550, 500, 15);
        confirmPasswordRegisterError.setForeground(new Color(255, 0, 0));
        confirmPasswordRegisterError.setBackground(new Color(255, 255, 255));
        confirmPasswordRegisterError.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(confirmPasswordRegisterError);

        //Register Button
        JButton userRegisterButton = new JButton("Register");
        userRegisterButton.setBounds(630, 570, 117, 30);
        userRegisterButton.setFont(new Font("Dialog", Font.BOLD, 16));
        userRegisterButton.setForeground(Color.WHITE);
        userRegisterButton.setBackground(new Color(0, 46, 98));
        frame.getContentPane().add(userRegisterButton);

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
    }
}
