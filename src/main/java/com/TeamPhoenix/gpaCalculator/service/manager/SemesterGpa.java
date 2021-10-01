package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.*;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class SemesterGpa {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private Long userId;
	private int semNumber;
	private User allCoursesWithResultsBySemNumberAndUserIdFromDb;
	private List<Course> courseListFromDb;
	private JComboBox comboBox_1;
	private Map<String, Double> gradeWithGpvMap = new HashMap<>();

	GpaCalDao gpaCalDao = new GpaCalDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SemesterGpa window = new SemesterGpa(1l, 1);
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
	public SemesterGpa(Long userId, int semNumber) {
		this.userId = userId;
		this.semNumber = semNumber;
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

		JLabel semesterNumberLabel = new JLabel("Semester " + semNumber);
		semesterNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		semesterNumberLabel.setBounds(359, 13, 300, 40);
		semesterNumberLabel.setForeground(new Color(255, 255, 255));
		semesterNumberLabel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(semesterNumberLabel);

		JLabel semesterOverallGpa = new JLabel("");
		semesterOverallGpa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		semesterOverallGpa.setBounds(104, 68, 300, 23);
		semesterOverallGpa.setForeground(new Color(255, 255, 255));
		semesterOverallGpa.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(semesterOverallGpa);

		Gpa overallSemGpa = gpaCalDao.getOverallGpa(userId, "sem" + semNumber);
		if (overallSemGpa != null) {
			semesterOverallGpa.setText("Semester " + semNumber + " Gpa = " + overallSemGpa.getGpa());
		}

		JLabel courseUnit = new JLabel("Course unit");
		courseUnit.setBounds(54, 120, 83, 16);
		courseUnit.setForeground(new Color(255, 255, 255));
		courseUnit.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(courseUnit);

		JLabel gradeLabel = new JLabel("Grade");
		gradeLabel.setBounds(209, 120, 56, 16);
		gradeLabel.setForeground(new Color(255, 255, 255));
		gradeLabel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(gradeLabel);

		model = new DefaultTableModel();
		model.addColumn("Course Unit");
		model.addColumn("Course Name");
		model.addColumn("Result");

		comboBox_1 = new JComboBox(populateAllCoursesForSem().toArray());
		comboBox_1.setBounds(54, 149, 100, 22);
		frame.getContentPane().add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select", "D-", "D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A", "A+"}));
		comboBox_2.setBounds(209, 149, 86, 22);
		frame.getContentPane().add(comboBox_2);

		JButton addButton = new JButton("ADD");
		addButton.setBounds(482, 148, 97, 25);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				User userFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
				List<String> alreadySavedSubjectCodesList = new ArrayList<>();
				for (Course course1 : userFromDb.getSubjectList()) {
					if (course1.getCourseCode() != null) {
						alreadySavedSubjectCodesList.add(course1.getCourseCode());
					}
				}
				for (Course course : courseListFromDb) {
					if (course.getCourseCode().equals(comboBox_1.getSelectedItem())) {
						if (!comboBox_2.getSelectedItem().equals("Select")) {
							if (!alreadySavedSubjectCodesList.contains((String) comboBox_1.getSelectedItem())) {
								gpaCalDao.saveUserSubject(userId, course.getCourseId());
								Result result = new Result();
								result.setResultGrade((String) comboBox_2.getSelectedItem());
								result.setStatus("ACTIVE");
								gpaCalDao.saveResultPreviouslySelectedSubjects(userId, course.getCourseId(), result);

								model.addRow(new Object[]{course.getCourseCode(), course.getCourseName(), (String) comboBox_2.getSelectedItem()});
								while (model.getRowCount() != 0) {
									model.removeRow(0);
								}
							} else {
								JOptionPane.showMessageDialog(frame, "Already saved", "Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
				comboBox_1.removeAllItems();
				for (String courseCode : populateAllCoursesForSem()) {
					comboBox_1.addItem(courseCode);
				}
			}
		});
		frame.getContentPane().add(addButton);

		JButton btnCalculate = new JButton("CALCULATE");
		btnCalculate.setBounds(600, 148, 140, 25);
		btnCalculate.setBackground(new Color(203, 165, 39, 249));
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				gradeWithGpvMap.put("A+", 4.0);
				gradeWithGpvMap.put("A", 4.0);
				gradeWithGpvMap.put("A-", 3.7);
				gradeWithGpvMap.put("B+", 3.3);
				gradeWithGpvMap.put("B", 3.0);
				gradeWithGpvMap.put("B-", 2.7);
				gradeWithGpvMap.put("C+", 2.4);
				gradeWithGpvMap.put("C", 2.1);
				gradeWithGpvMap.put("D+", 1.8);
				gradeWithGpvMap.put("D", 1.5);
				gradeWithGpvMap.put("D-", 1.2);

				User user = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
				if (user != null) {
					List<PredictReportResult> semResult = new ArrayList<>();
					for (Course course : user.getSubjectList()) {
						if (course != null) {
							PredictReportResult predictReportResult = new PredictReportResult();
							predictReportResult.setSubjectName(course.getCourseName());
							predictReportResult.setSubjectCode(course.getCourseCode());
							predictReportResult.setSubjectCredit(course.getCourseCredits());
							predictReportResult.setGpv(gradeWithGpvMap.get(course.getResult().getResultGrade()));
							predictReportResult.setResultGrade(course.getResult().getResultGrade());

							semResult.add(predictReportResult);
						}
					}
					Double semGpa = Math.round(calculateGpa(semResult) * 100.0) / 100.0;
					if (overallSemGpa != null) {
						if (!Objects.equals(overallSemGpa.getGpa(), semGpa)) {
							gpaCalDao.updateGpa(userId, "sem" + semNumber, semGpa);
						}
					} else {
						gpaCalDao.saveGpa(userId, "sem" + semNumber, semGpa);
					}
					semesterOverallGpa.setText("Semester " + semNumber + " Gpa = " + semGpa);
				}
			}
		});
		frame.getContentPane().add(btnCalculate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(763, 148, 97, 25);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int row = table.getSelectedRow();
				String courseCode = (String) table.getValueAt(row, 0);
				gpaCalDao.deleteResult(userId, courseCode);
				gpaCalDao.deleteUserSubjectEnrollment(userId, courseCode);
				model.removeRow(row);
				comboBox_1.removeAllItems();
				for (String courseCode1 : populateAllCoursesForSem()) {
					comboBox_1.addItem(courseCode1);
				}
			}
		});
		frame.getContentPane().add(btnDelete);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Courses with Results", TitledBorder.CENTER, TitledBorder.TOP));
		panel.setBounds(50, 200, 900, 500);

		populateAlreadyAddedSem();

		table = new JTable(model);
		TableColumnModel columnModel = table.getColumnModel();
		table.setRowHeight(30);
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(400);
		columnModel.getColumn(2).setPreferredWidth(200);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		panel.add(new JScrollPane(table));
		frame.add(panel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				String oldGrade = (String) table.getValueAt(row, column);
				if (column == 2) {
					String[] choices = { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", " " };
					String newGrade = (String) JOptionPane.showInputDialog(frame, "Choose course grade...",
							"Edit course grade", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					table.setValueAt(newGrade, row, column);

					if (newGrade != null) {
						User userFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
						if (userFromDb != null) {
							for (Course course : userFromDb.getSubjectList()) {
								if (course.getCourseCode().equals((String) table.getValueAt(row, 0))) {
									if (!oldGrade.equals("NOT ADD")) {
										gpaCalDao.updateResult(userId, course.getCourseId(), newGrade);
									} else {
										Result result = new Result();
										result.setResultGrade(newGrade);
										result.setStatus("ACTIVE");
										gpaCalDao.saveResultPreviouslySelectedSubjects(userId, course.getCourseId(), result);
									}
								}
							}
						}
					} else {
						table.setValueAt(oldGrade, row, column);
					}
				}
			}
		});

		JLabel rightSideBackground = new JLabel("");
		rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
		rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
		rightSideBackground.setBounds(0, 0, 1000, 750);
		frame.getContentPane().add(rightSideBackground);
	}

	private void populateAlreadyAddedSem() {
		allCoursesWithResultsBySemNumberAndUserIdFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
		if (allCoursesWithResultsBySemNumberAndUserIdFromDb != null) {
			while (model.getRowCount() != 0) {
				model.removeRow(0);
			}
			for (Course course : allCoursesWithResultsBySemNumberAndUserIdFromDb.getSubjectList()) {
				String CourseCode = course.getCourseCode();
				String CourseName = course.getCourseName();
				String resultGrade;
				if (course.getResult().getResultGrade() != null) {
					resultGrade = course.getResult().getResultGrade();
				} else {
					resultGrade = "NOT ADD";
				}
				model.addRow(new Object[]{CourseCode, CourseName, resultGrade});
			}
		}
	}

	private List<String> populateAllCoursesForSem() {
		courseListFromDb = gpaCalDao.getAllSubjectsBySemNo(semNumber);
		User userFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
		List<String> CourseCodesList = new ArrayList<>();
		List<String> alreadySavedCourseCodesList = new ArrayList<>();
		if (userFromDb != null) {
			while (model.getRowCount() != 0) {
				model.removeRow(0);
			}
			for (Course course1 : userFromDb.getSubjectList()) {
				if (course1 != null) {
					if (course1.getCourseCode() != null) {
						alreadySavedCourseCodesList.add(course1.getCourseCode());
						model.addRow(new Object[]{course1.getCourseCode(), course1.getCourseName(), course1.getResult().getResultGrade()});
					}
				}
			}
		}
		for (Course course : courseListFromDb) {
			if (course != null) {
				if (course.getCourseCode() != null) {
					if (userFromDb != null) {
						if (!alreadySavedCourseCodesList.contains(course.getCourseCode())) {
							CourseCodesList.add(course.getCourseCode());
						}
					} else {
						CourseCodesList.add(course.getCourseCode());
					}
				}
			}
		}
		return CourseCodesList;
	}

	private Double calculateGpa(List<PredictReportResult> semResults) {
		int creditsCount = 0;
		double multiplicationOFCreditAndGpv = 0.0;
		for (PredictReportResult predictReportResult : semResults) {
			creditsCount = creditsCount + predictReportResult.getSubjectCredit();
			multiplicationOFCreditAndGpv = multiplicationOFCreditAndGpv + (predictReportResult.getSubjectCredit() *
					predictReportResult.getGpv());
		}
		return multiplicationOFCreditAndGpv / creditsCount;
	}
}
