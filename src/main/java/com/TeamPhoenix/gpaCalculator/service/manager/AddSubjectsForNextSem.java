package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddSubjectsForNextSem {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox comboBox_1;
    private Long userId;
    private Integer semNumber;
    private List<Course> courseListFromDb;

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddSubjectsForNextSem window = new AddSubjectsForNextSem(1l);
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
    public AddSubjectsForNextSem(Long userId) {
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

        populateUserNextSemesterNumber();

        JLabel semesterNumberLabel = new JLabel("Add Your Courses for Semester " + semNumber);
        semesterNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        semesterNumberLabel.setBounds(300, 25, 420, 40);
        semesterNumberLabel.setForeground(new Color(255, 255, 255));
        semesterNumberLabel.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(semesterNumberLabel);

        JLabel courseUnit = new JLabel("Course unit");
        courseUnit.setBounds(54, 120, 83, 16);
        courseUnit.setForeground(new Color(255, 255, 255));
        courseUnit.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(courseUnit);

        model = new DefaultTableModel();
        model.addColumn("Course Unit");
        model.addColumn("Course Name");
        model.addColumn("Course Credit");

        comboBox_1 = new JComboBox(populateAllSubjectsForSem().toArray());
        comboBox_1.setBounds(54, 149, 100, 22);
        frame.getContentPane().add(comboBox_1);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(700, 148, 97, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User userFromDb = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
                List<String> alreadySavedSubjectCodesList = new ArrayList<>();
                for (Course subject1 : userFromDb.getSubjectList()) {
                    if (subject1.getCourseCode() != null) {
                        alreadySavedSubjectCodesList.add(subject1.getCourseCode());
                    }
                }
                for (Course subject : courseListFromDb) {
                    if (subject.getCourseCode().equals(comboBox_1.getSelectedItem())) {
                        gpaCalDao.saveUserSubject(userId, subject.getCourseId());
                        model.addRow(new Object[]{subject.getCourseCode(), subject.getCourseName(), subject.getCourseCredits()});
                        while (model.getRowCount() != 0) {
                            model.removeRow(0);
                        }
                    }
                }
                comboBox_1.removeAllItems();
                for (String courseCode : populateAllSubjectsForSem()) {
                    comboBox_1.addItem(courseCode);
                }
            }
        });
        frame.getContentPane().add(addButton);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Courses", TitledBorder.CENTER, TitledBorder.TOP));
        panel.setBounds(50, 200, 900, 350);

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
        table.setSize(800, 300);
        table.setPreferredScrollableViewportSize(table.getSize());
        table.setFillsViewportHeight(true);
        panel.add(new JScrollPane(table));
        frame.add(panel);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(800, 148, 97, 25);
        btnDelete.setBackground(new Color(194, 54, 44));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                String courseCode = (String) table.getValueAt(row, 0);
                gpaCalDao.deleteUserSubjectEnrollment(userId, courseCode);
                model.removeRow(row);
                comboBox_1.removeAllItems();
                for (String courseCode1 : populateAllSubjectsForSem()) {
                    comboBox_1.addItem(courseCode1);
                }
            }
        });
        frame.getContentPane().add(btnDelete);

        JButton btnNextPage = new JButton("Predict");
        btnNextPage.setBounds(450, 600, 100, 25);
        btnNextPage.setBackground(new Color(203, 165, 39, 249));
        btnNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new PredictionPage(userId);
            }
        });
        frame.getContentPane().add(btnNextPage);

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(rightSideBackground);
    }

    private List<String> populateAllSubjectsForSem() {
        User userWithNextSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(semNumber, userId);
        List<String> subjectCodesList = new ArrayList<>();
        List<String> alreadySavedSubjectCodesList = new ArrayList<>();
        if (userWithNextSemSubjects != null) {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            for (Course course1 : userWithNextSemSubjects.getSubjectList()) {
                if (course1 != null) {
                    if (course1.getCourseCode() != null) {
                        alreadySavedSubjectCodesList.add(course1.getCourseCode());
                        model.addRow(new Object[]{course1.getCourseCode(), course1.getCourseName(), course1.getCourseCredits()});
                    }
                }
            }
        }
        for (Course course : courseListFromDb) {
            if (course != null) {
                if (course.getCourseCode() != null) {
                    if (userWithNextSemSubjects != null) {
                        if (!alreadySavedSubjectCodesList.contains(course.getCourseCode())) {
                            subjectCodesList.add(course.getCourseCode());
                        }
                    } else {
                        subjectCodesList.add(course.getCourseCode());
                    }
                }
            }
        }
        return subjectCodesList;
    }

    private void populateUserNextSemesterNumber() {
        List<Gpa> gpaListFromDb = gpaCalDao.getAllGpaByUserId(userId);
        List<Integer> semNumbersList = new ArrayList<>();
        for (Gpa gpa : gpaListFromDb) {
            try {
                semNumbersList.add(Integer.parseInt(String.valueOf(gpa.getGpaType().charAt(3))));
            } catch (Exception e) {
                System.out.println("Prediction page : Cannot convert to integer");
            }
        }
        semNumber = semNumbersList.size() + 1;
        courseListFromDb = gpaCalDao.getAllSubjectsBySemNo(semNumber);
    }
}
