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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome();
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
	public AdminHome() {
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
		
		JLabel lbllogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/logo.png").getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
		lbllogo.setIcon(imageIcon);
		lbllogo.setBounds(5, 41, 300, 400);
		frame.getContentPane().add(lbllogo);
		
		JLabel lblleftimage = new JLabel("");
		lblleftimage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblleftimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
		lblleftimage.setBounds(0, 0, 276, 506);
		frame.getContentPane().add(lblleftimage);
		
		JButton btnaddCouses = new JButton("Add Courses");
		btnaddCouses.setBackground(Color.WHITE);
		btnaddCouses.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnaddCouses.setForeground(Color.BLACK);
		btnaddCouses.setBounds(435, 31, 221, 54);
		frame.getContentPane().add(btnaddCouses);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(Color.BLACK);
		btnLogOut.setFont(new Font("Sitka Text", Font.BOLD, 20));
		btnLogOut.setBackground(new Color(255, 153, 204));
		btnLogOut.setBounds(435, 418, 221, 54);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnupdateCourses = new JButton("Update Courses");
		btnupdateCourses.setForeground(Color.BLACK);
		btnupdateCourses.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnupdateCourses.setBackground(Color.WHITE);
		btnupdateCourses.setBounds(435, 96, 221, 54);
		frame.getContentPane().add(btnupdateCourses);
		
		JButton btnDeleteCourses = new JButton("Delete Courses");
		btnDeleteCourses.setForeground(Color.BLACK);
		btnDeleteCourses.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnDeleteCourses.setBackground(Color.WHITE);
		btnDeleteCourses.setBounds(435, 158, 221, 54);
		frame.getContentPane().add(btnDeleteCourses);
		
		JButton btnViewAllStudents = new JButton("View all Students");
		btnViewAllStudents.setForeground(Color.BLACK);
		btnViewAllStudents.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnViewAllStudents.setBackground(Color.WHITE);
		btnViewAllStudents.setBounds(435, 223, 221, 54);
		frame.getContentPane().add(btnViewAllStudents);
		
		JButton btnDeleteStudents = new JButton("Delete Students");
		btnDeleteStudents.setForeground(Color.BLACK);
		btnDeleteStudents.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnDeleteStudents.setBackground(Color.WHITE);
		btnDeleteStudents.setBounds(435, 288, 221, 54);
		frame.getContentPane().add(btnDeleteStudents);
		
		JButton btnMyAccount = new JButton("My account");
		btnMyAccount.setForeground(Color.BLACK);
		btnMyAccount.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnMyAccount.setBackground(new Color(255, 153, 204));
		btnMyAccount.setBounds(435, 353, 221, 54);
		frame.getContentPane().add(btnMyAccount);
		
		JLabel lblrightimage = new JLabel("");
		lblrightimage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
		lblrightimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
		lblrightimage.setBounds(275, 0, 561, 506);
		frame.getContentPane().add(lblrightimage);
	}
}
