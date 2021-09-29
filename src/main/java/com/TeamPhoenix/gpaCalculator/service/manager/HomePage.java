package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class HomePage {

	private JFrame frame;
	private Long userId;
	private JLabel lblName;
	private JLabel lblOverallGpa;

	GpaCalDao gpaCalDao = new GpaCalDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage(1l);
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
	public HomePage(Long userId) {
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 983, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JLabel leftSideApplicationName = new JLabel("");
		leftSideApplicationName.setForeground(new Color(255, 255, 255));
		leftSideApplicationName.setBackground(new Color(0, 0, 128));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/logo.png").getImage().getScaledInstance(350, 450, Image.SCALE_DEFAULT));
		leftSideApplicationName.setIcon(imageIcon);
		leftSideApplicationName.setFont(new Font("Dialog", Font.BOLD, 22));
		leftSideApplicationName.setHorizontalAlignment(SwingConstants.CENTER);
		leftSideApplicationName.setBounds(0, 27, 300, 600);
		frame.getContentPane().add(leftSideApplicationName);
		
		lblName = new JLabel("");
		lblName.setForeground(new Color(0, 255, 153));
		lblName.setFont(new Font("Ink Free", Font.BOLD, 30));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(323, 34, 300, 57);
		frame.getContentPane().add(lblName);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(238, 39, 39));
		btnLogOut.setBounds(797, 34, 137, 46);
		frame.getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//TODO - connect login page
				frame.dispose();
//				new PredictionPage(userId);
			}
		});
		
		JButton btnsem1 = new JButton("Semester 1");
		btnsem1.setBackground(Color.WHITE);
		btnsem1.setForeground(Color.BLACK);
		btnsem1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnsem1.setBounds(309, 259, 137, 46);
		frame.getContentPane().add(btnsem1);
		btnsem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 1);
			}
		});
		
		JButton btnsem2 = new JButton("Semester 2");
		btnsem2.setBackground(Color.WHITE);
		btnsem2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnsem2.setBounds(470, 259, 137, 46);
		frame.getContentPane().add(btnsem2);
		btnsem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 2);
			}
		});
		
		JButton btnSem3 = new JButton("Semester 3");
		btnSem3.setBackground(Color.WHITE);
		btnSem3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem3.setBounds(635, 259, 137, 46);
		frame.getContentPane().add(btnSem3);
		btnSem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 3);
			}
		});
		
		JButton btnSem4 = new JButton("Semester 4");
		btnSem4.setBackground(Color.WHITE);
		btnSem4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem4.setBounds(797, 259, 137, 46);
		frame.getContentPane().add(btnSem4);
		btnSem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 4);
			}
		});
		
		JButton btnSem5 = new JButton("Semester 5");
		btnSem5.setForeground(new Color(0, 0, 0));
		btnSem5.setBackground(Color.WHITE);
		btnSem5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem5.setBounds(309, 350, 137, 46);
		frame.getContentPane().add(btnSem5);
		btnSem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 5);
			}
		});
		
		JButton btnSem6 = new JButton("Semester 6");
		btnSem6.setBackground(Color.WHITE);
		btnSem6.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem6.setBounds(470, 350, 137, 46);
		frame.getContentPane().add(btnSem6);
		btnSem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 6);
			}
		});
		
		JButton btnSem7 = new JButton("Semester 7");
		btnSem7.setBackground(Color.WHITE);
		btnSem7.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem7.setBounds(635, 350, 137, 46);
		frame.getContentPane().add(btnSem7);
		btnSem7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 7);
			}
		});
		
		JButton btnSem8 = new JButton("Semester 8");
		btnSem8.setBackground(Color.WHITE);
		btnSem8.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSem8.setBounds(797, 350, 137, 46);
		frame.getContentPane().add(btnSem8);
		btnSem8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new SemesterGpa(userId, 8);
			}
		});
		
		lblOverallGpa = new JLabel("");
		lblOverallGpa.setForeground(new Color(255, 255, 255));
		lblOverallGpa.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverallGpa.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblOverallGpa.setBounds(309, 140, 400, 71);
		frame.getContentPane().add(lblOverallGpa);
		
		JButton btnprediction = new JButton("Prediction");
		btnprediction.setBackground(Color.WHITE);
		btnprediction.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnprediction.setBounds(470, 438, 137, 46);
		frame.getContentPane().add(btnprediction);
		btnprediction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new PredictionPage(userId);
			}
		});

		JButton btnAllGpaPage = new JButton("All GPAs");
		btnAllGpaPage.setBackground(Color.WHITE);
		btnAllGpaPage.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAllGpaPage.setBounds(635, 438, 137, 46);
		frame.getContentPane().add(btnAllGpaPage);
		btnAllGpaPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//TODO - connect ALL gpa page
//				new PredictionPage(userId);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Welcome to GPA calculator");
		lblNewLabel.setForeground(new Color(0, 255, 102));
		lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 28));
		lblNewLabel.setBounds(323, 96, 500, 33);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblleftimage = new JLabel("");
		lblleftimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
		lblleftimage.setBounds(-12, 0, 280, 573);
		frame.getContentPane().add(lblleftimage);
		
		JLabel lblrightimage = new JLabel("");
		lblrightimage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblrightimage.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
		lblrightimage.setBounds(263, 0, 704, 573);
		frame.getContentPane().add(lblrightimage);

		populateUser();
		populateOverallGpa();
	}

	private void populateUser() {
		User userFromDb = gpaCalDao.getUserByUserId(userId);
		if (userFromDb != null) {
			lblName.setText("HI " + userFromDb.getName() + " !");
		}
	}

	private void populateOverallGpa() {
		Gpa gpaFromDb = gpaCalDao.getOverallGpa(userId, "OVERALL");
		if (gpaFromDb != null) {
			lblOverallGpa.setText("Overall GPA = " + gpaFromDb.getGpa());
		}
	}
}
