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

public class LoginPage{

    private JFrame frame;
    private JTextField usernameTextFieldLogin;
    private JPasswordField passwordTextFieldLogin;

    /** Login Page Launch**/

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage window = new LoginPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** Create the application **/
    public LoginPage() {
        initialize();
    }

    /** Initialize the contents of the frame **/

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
        JLabel loginPageMainName = new JLabel("Log In");
        loginPageMainName.setBounds(620, 170, 300, 45);
        loginPageMainName.setForeground(new Color(255, 255, 255));
        loginPageMainName.setBackground(new Color(255, 255, 255));
        loginPageMainName.setFont(new Font("Arial", Font.BOLD, 40));
        panel_1.add(loginPageMainName);

        //name related
        JLabel usernameLabelForLogin = new JLabel("Username : ");
        usernameLabelForLogin.setBounds(495, 270, 90, 15);
        usernameLabelForLogin.setForeground(new Color(255, 255, 255));
        usernameLabelForLogin.setBackground(new Color(255, 255, 255));
        usernameLabelForLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_1.add(usernameLabelForLogin);

        usernameTextFieldLogin = new JTextField();
        usernameTextFieldLogin.setBounds(495, 300, 400, 30);
        usernameTextFieldLogin.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        usernameTextFieldLogin.setForeground(new Color(255, 255, 255));
        usernameTextFieldLogin.setCaretColor(new Color(255, 255, 255));
        usernameTextFieldLogin.setBackground(new Color(0, 46, 98));
        usernameTextFieldLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_1.add(usernameTextFieldLogin);
        usernameTextFieldLogin.setColumns(10);

        JLabel passwordLabelLogin = new JLabel("Password : ");
        passwordLabelLogin.setBounds(495, 362, 110, 15);
        passwordLabelLogin.setForeground(new Color(255, 255, 255));
        passwordLabelLogin.setBackground(new Color(255, 255, 255));
        passwordLabelLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_1.add(passwordLabelLogin);

        passwordTextFieldLogin = new JPasswordField();
        passwordTextFieldLogin.setBounds(495, 392, 400, 30);
        passwordTextFieldLogin.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        passwordTextFieldLogin.setForeground(new Color(255, 255, 255));
        passwordTextFieldLogin.setCaretColor(new Color(255, 255, 255));
        passwordTextFieldLogin.setBackground(new Color(0, 46, 98));
        passwordTextFieldLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_1.add(passwordTextFieldLogin);
        passwordTextFieldLogin.setColumns(10);

        JButton userLoginButton = new JButton("Login");
        userLoginButton.setBounds(630, 450, 117, 30);
        userLoginButton.setFont(new Font("Dialog", Font.BOLD, 16));
        userLoginButton.setForeground(Color.WHITE);
        userLoginButton.setBackground(new Color(0, 46, 98));
        panel_1.add(userLoginButton);

        JLabel dontHaveAnAccountPart1 = new JLabel("Don't have an account? ");
        dontHaveAnAccountPart1.setBounds(530, 503, 200, 15);
        dontHaveAnAccountPart1.setForeground(new Color(255, 255, 255));
        dontHaveAnAccountPart1.setBackground(new Color(255, 255, 255));
        dontHaveAnAccountPart1.setFont(new Font("Dialog", Font.PLAIN, 15));
        panel_1.add(dontHaveAnAccountPart1);

        JLabel dontHaveAnAccountPart2 = new JLabel("Sign Up Now ");
        dontHaveAnAccountPart2.setBounds(716, 500, 300, 20);
        dontHaveAnAccountPart2.setForeground(new Color(255, 255, 255));
        dontHaveAnAccountPart2.setBackground(new Color(255, 255, 255));
        dontHaveAnAccountPart2.setFont(new Font("Dialog", Font.BOLD, 18));
        panel_1.add(dontHaveAnAccountPart2);

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

        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                GpaCalDao gpaCalDao = new GpaCalDaoImpl();
                String userName = usernameTextFieldLogin.getText();
                String password = String.valueOf(passwordTextFieldLogin.getPassword());

                User user = gpaCalDao.getUserDetailsByUsernameAndPassword(userName, password);
                if (user == null) {
                    JOptionPane.showMessageDialog(frame, "Your Username or Password is incorrect. Please try again.", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "You have logged in successfully.", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new HomePage(user.getUserId());
                }

            }
        });

        //don't have an account
        dontHaveAnAccountPart2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new SignupPage();
            }
        });
    }
}


