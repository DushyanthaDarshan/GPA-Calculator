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
	private User allSubjectsWithResultsBySemNumberAndUserIdFromDb;
	private List<Subject> subjectListFromDb;
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

		comboBox_1 = new JComboBox(populateAllSubjectsForSem().toArray());
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
				for (Subject subject1 : userFromDb.getSubjectList()) {
					if (subject1.getSubjectCode() != null) {
						alreadySavedSubjectCodesList.add(subject1.getSubjectCode());
					}
				}
				for (Subject subject : subjectListFromDb) {
					if (subject.getSubjectCode().equals(comboBox_1.getSelectedItem())) {
						if (!comboBox_2.getSelectedItem().equals("Select")) {
							if (!alreadySavedSubjectCodesList.contains((String) comboBox_1.getSelectedItem())) {
								gpaCalDao.saveUserSubject(userId, subject.getSubjectId());
								Result result = new Result();
								result.setResultGrade((String) comboBox_2.getSelectedItem());
								result.setStatus("ACTIVE");
								gpaCalDao.saveResultPreviouslySelectedSubjects(userId, subject.getSubjectId(), result);

								model.addRow(new Object[]{subject.getSubjectCode(), subject.getSubjectName(), (String) comboBox_2.getSelectedItem()});
								populateAllSubjectsForSem();
							} else {
								JOptionPane.showMessageDialog(frame, "Already saved", "Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
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

				User user = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
				if (user != null) {
					List<PredictReportResult> semResult = new ArrayList<>();
					for (Subject subject : user.getSubjectList()) {
						if (subject != null) {
							PredictReportResult predictReportResult = new PredictReportResult();
							predictReportResult.setSubjectName(subject.getSubjectName());
							predictReportResult.setSubjectCode(subject.getSubjectCode());
							predictReportResult.setSubjectCredit(subject.getSubjectCredits());
							predictReportResult.setGpv(gradeWithGpvMap.get(subject.getResult().getResultGrade()));
							predictReportResult.setResultGrade(subject.getResult().getResultGrade());

							semResult.add(predictReportResult);
						}
					}
					Double semGpa = calculateGpa(semResult);
					if (overallSemGpa != null) {
						if (!Objects.equals(overallSemGpa.getGpa(), semGpa)) {

						}
					} else {
						gpaCalDao.saveGpa(userId, "sem" + semNumber, semGpa);
					}
					semesterOverallGpa.setText("Semester " + semNumber + " Gpa = " + semGpa);
				}
			}
		});
		frame.getContentPane().add(btnCalculate);

		JButton btnDelet = new JButton("DELETE");
		btnDelet.setBounds(763, 148, 97, 25);
		frame.getContentPane().add(btnDelet);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Courses with Results", TitledBorder.CENTER, TitledBorder.TOP));
		panel.setBounds(50, 200, 900, 500);

		model = new DefaultTableModel();
		model.addColumn("Course Unit");
		model.addColumn("Course Name");
		model.addColumn("Result");

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
							for (Subject subject : userFromDb.getSubjectList()) {
								if (subject.getSubjectCode().equals((String) table.getValueAt(row, 0))) {
									if (!oldGrade.equals("NOT ADD")) {
										gpaCalDao.updateResult(userId, subject.getSubjectId(), newGrade);
									} else {
										Result result = new Result();
										result.setResultGrade(newGrade);
										result.setStatus("ACTIVE");
										gpaCalDao.saveResultPreviouslySelectedSubjects(userId, subject.getSubjectId(), result);
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

//		DefaultTableModel tableModel = new DefaultTableModel();
//		JTable table = new JTable(tableModel);
//		table.setVisible(true);
//		tableModel.addColumn("Languages");
//		tableModel.insertRow(0, new Object[] { "CSS" });
//		tableModel.insertRow(0, new Object[] { "HTML5" });
//		tableModel.insertRow(0, new Object[] { "JavaScript" });
//		tableModel.insertRow(0, new Object[] { "jQuery" });
//		tableModel.insertRow(0, new Object[] { "AngularJS" });
//		// adding a new row
//		tableModel.insertRow(tableModel.getRowCount(), new Object[] { "ExpressJS" });


//		JLabel lblNewLabel_3 = new JLabel("GPA");
//		lblNewLabel_3.setBounds(44, 359, 56, 16);
//		frame.getContentPane().add(lblNewLabel_3);
//
//		textField = new JTextField();
//		textField.setBounds(115, 356, 116, 22);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);

		JLabel rightSideBackground = new JLabel("");
		rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
		rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
		rightSideBackground.setBounds(0, 0, 1000, 750);
		frame.getContentPane().add(rightSideBackground);
	}

	private void populateAlreadyAddedSem() {
		allSubjectsWithResultsBySemNumberAndUserIdFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
		if (allSubjectsWithResultsBySemNumberAndUserIdFromDb != null) {
			for (Subject subject : allSubjectsWithResultsBySemNumberAndUserIdFromDb.getSubjectList()) {
				String subjectCode = subject.getSubjectCode();
				String subjectName = subject.getSubjectName();
				String resultGrade;
				if (subject.getResult().getResultGrade() != null) {
					resultGrade = subject.getResult().getResultGrade();
				} else {
					resultGrade = "NOT ADD";
				}
				model.addRow(new Object[]{subjectCode, subjectName, resultGrade});
			}
		}
	}

	private List<String> populateAllSubjectsForSem() {
		subjectListFromDb = gpaCalDao.getAllSubjectsBySemNo(semNumber);
		User userFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
		List<String> subjectCodesList = new ArrayList<>();
		List<String> alreadySavedSubjectCodesList = new ArrayList<>();
		if (userFromDb != null) {
			for (Subject subject1 : userFromDb.getSubjectList()) {
				if (subject1 != null) {
					if (subject1.getSubjectCode() != null) {
						alreadySavedSubjectCodesList.add(subject1.getSubjectCode());
					}
				}
			}
		}
		for (Subject subject : subjectListFromDb) {
			if (subject != null) {
				if (subject.getSubjectCode() != null) {
					if (userFromDb != null) {
						if (!alreadySavedSubjectCodesList.contains(subject.getSubjectCode())) {
							subjectCodesList.add(subject.getSubjectCode());
						}
					} else {
						subjectCodesList.add(subject.getSubjectCode());
					}
				}
			}
		}
		return subjectCodesList;
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
