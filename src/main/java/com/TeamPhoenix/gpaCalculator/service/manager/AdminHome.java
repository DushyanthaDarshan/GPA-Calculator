package com.TeamPhoenix.gpaCalculator.service.manager;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome {

	private JFrame frame;
	private Long userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome(2l);
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
	public AdminHome(Long userId) {
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JLabel logoLabel = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/logo.png").getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
		logoLabel.setIcon(imageIcon);
		logoLabel.setBounds(5, 41, 300, 400);
		frame.getContentPane().add(logoLabel);
		
		JLabel lblleftimage = new JLabel("");
		lblleftimage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblleftimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
		lblleftimage.setBounds(0, 0, 276, 506);
		frame.getContentPane().add(lblleftimage);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(Color.BLACK);
		btnLogOut.setFont(new Font("Sitka Text", Font.BOLD, 20));
		btnLogOut.setBackground(new Color(255, 153, 204));
		btnLogOut.setBounds(435, 418, 221, 54);
		frame.getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new LoginPage();
			}
		});
		
		JButton manageCoursesBtn = new JButton("Manage Courses");
		manageCoursesBtn.setForeground(Color.BLACK);
		manageCoursesBtn.setFont(new Font("Sitka Text", Font.BOLD, 18));
		manageCoursesBtn.setBackground(Color.WHITE);
		manageCoursesBtn.setBounds(435, 96, 221, 54);
		frame.getContentPane().add(manageCoursesBtn);
		manageCoursesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new ManageCourses(userId);
			}
		});
		
		JButton btnViewAllStudents = new JButton("Manage Students");
		btnViewAllStudents.setForeground(Color.BLACK);
		btnViewAllStudents.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnViewAllStudents.setBackground(Color.WHITE);
		btnViewAllStudents.setBounds(435, 200, 221, 54);
		frame.getContentPane().add(btnViewAllStudents);
		btnViewAllStudents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new ManageStudents(userId);
			}
		});
		
		JButton btnMyAccount = new JButton("My account");
		btnMyAccount.setForeground(Color.BLACK);
		btnMyAccount.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnMyAccount.setBackground(new Color(255, 153, 204));
		btnMyAccount.setBounds(435, 353, 221, 54);
		frame.getContentPane().add(btnMyAccount);
		btnMyAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new AdminMyAccount(userId);
			}
		});
		
		JLabel lblrightimage = new JLabel("");
		lblrightimage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
		lblrightimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
		lblrightimage.setBounds(275, 0, 561, 506);
		frame.getContentPane().add(lblrightimage);
	}
}
