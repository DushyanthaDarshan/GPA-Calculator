package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.PredictReportResult;
import com.TeamPhoenix.gpaCalculator.beans.Student;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import org.apache.commons.collections4.CollectionUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author user
 */

public class AllGpaPage {
    private JFrame frame;
    private JLabel OverallGPALabel;
    private JLabel sem1Gpa;
    private JLabel sem2Gpa;
    private JLabel sem3Gpa;
    private JLabel sem4Gpa;
    private JLabel sem5Gpa;
    private JLabel sem6Gpa;
    private JLabel sem7Gpa;
    private JLabel sem8Gpa;
    private JLabel electiveSubjectsGpa;
    private JLabel coreSubjectsGpa;
    private Long userId;
    private Map<String, Double> gradeWithGpvMap = new HashMap<>();

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AllGpaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllGpaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllGpaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllGpaPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AllGpaPage window = new AllGpaPage(1l);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AllGpaPage(Long userId) {
        this.userId = userId;
        initComponents();
    }

    private void initComponents() {
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
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/logo.png").getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT));
        leftSideApplicationName.setIcon(imageIcon);
        leftSideApplicationName.setFont(new Font("Dialog", Font.BOLD, 22));
        leftSideApplicationName.setHorizontalAlignment(SwingConstants.CENTER);
        leftSideApplicationName.setBounds(20, 77, 400, 600);
        panel_1.add(leftSideApplicationName);

        //name related
        JLabel nameLabel = new JLabel("GPA TYPES WITH VALUES");
        nameLabel.setBounds(450, 63, 500, 30);
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setBackground(new Color(255, 255, 255));
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        panel_1.add(nameLabel);

        //Overall GPA related
        OverallGPALabel = new JLabel("Overall GPA : Not Add Yet");
        OverallGPALabel.setBounds(420, 160, 500, 25);
        OverallGPALabel.setForeground(new Color(255, 255, 255));
        OverallGPALabel.setBackground(new Color(25, 255, 255));
        OverallGPALabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(OverallGPALabel);

        //sem 1 GPA related
        sem1Gpa = new JLabel("Sem 1 gpa   : Not Add Yet");
        sem1Gpa.setBounds(420, 200, 500, 25);
        sem1Gpa.setForeground(new Color(255, 255, 255));
        sem1Gpa.setBackground(new Color(25, 255, 255));
        sem1Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem1Gpa);

        //sem 2 GPA related
        sem2Gpa = new JLabel("Sem 2 gpa   : Not Add Yet");
        sem2Gpa.setBounds(420, 240, 500, 25);
        sem2Gpa.setForeground(new Color(255, 255, 255));
        sem2Gpa.setBackground(new Color(25, 255, 255));
        sem2Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem2Gpa);

        //sem 3 GPA related
        sem3Gpa = new JLabel("Sem 3 gpa   : Not Add Yet");
        sem3Gpa.setBounds(420, 280, 500, 25);
        sem3Gpa.setForeground(new Color(255, 255, 255));
        sem3Gpa.setBackground(new Color(25, 255, 255));
        sem3Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem3Gpa);

        //sem 4 GPA related
        sem4Gpa = new JLabel("Sem 4 gpa   : Not Add Yet");
        sem4Gpa.setBounds(420, 320, 500, 25);
        sem4Gpa.setForeground(new Color(255, 255, 255));
        sem4Gpa.setBackground(new Color(25, 255, 255));
        sem4Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem4Gpa);

        //sem 5 GPA related
        sem5Gpa = new JLabel("Sem 5 gpa   : Not Add Yet");
        sem5Gpa.setBounds(420, 360, 500, 25);
        sem5Gpa.setForeground(new Color(255, 255, 255));
        sem5Gpa.setBackground(new Color(25, 255, 255));
        sem5Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem5Gpa);

        //sem 6 GPA related
        sem6Gpa = new JLabel("Sem 6 gpa   : Not Add Yet");
        sem6Gpa.setBounds(420, 400, 500, 25);
        sem6Gpa.setForeground(new Color(255, 255, 255));
        sem6Gpa.setBackground(new Color(25, 255, 255));
        sem6Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem6Gpa);

        //sem 7 GPA related
        sem7Gpa = new JLabel("Sem 7 gpa   : Not Add Yet");
        sem7Gpa.setBounds(420, 440, 500, 25);
        sem7Gpa.setForeground(new Color(255, 255, 255));
        sem7Gpa.setBackground(new Color(25, 255, 255));
        sem7Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem7Gpa);

        //sem 8 GPA related
        sem8Gpa = new JLabel("Sem 8 gpa   : Not Add Yet");
        sem8Gpa.setBounds(420, 480, 500, 25);
        sem8Gpa.setForeground(new Color(255, 255, 255));
        sem8Gpa.setBackground(new Color(25, 255, 255));
        sem8Gpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(sem8Gpa);

        JPanel gpaPanel = new JPanel();
        gpaPanel.setBounds(500, 250, 150, 25);
        gpaPanel.setBackground(new Color(60, 63, 65));

        electiveSubjectsGpa = new JLabel("");
        electiveSubjectsGpa.setBounds(420, 520, 500, 25);
        electiveSubjectsGpa.setForeground(new Color(255, 0, 0));
        electiveSubjectsGpa.setBackground(new Color(255, 255, 255));
        electiveSubjectsGpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(electiveSubjectsGpa);

        coreSubjectsGpa = new JLabel("");
        coreSubjectsGpa.setBounds(420, 560, 500, 25);
        coreSubjectsGpa.setForeground(new Color(255, 0, 0));
        coreSubjectsGpa.setBackground(new Color(255, 255, 255));
        coreSubjectsGpa.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(coreSubjectsGpa);

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

        populateGpa();
    }

    private void populateGpa() {
        List<Gpa> allGpaFromDb = gpaCalDao.getAllGpaByUserId(userId);
        if (CollectionUtils.isNotEmpty(allGpaFromDb)) {
            for (Gpa gpa : allGpaFromDb) {
                if (gpa != null) {
                    String gpaType = gpa.getGpaType();
                    if (gpaType.equals("OVERALL")) {
                        OverallGPALabel.setText("Overall GPA : " + gpa.getGpa());
                    } else if (gpaType.equals("sem1")) {
                        sem1Gpa.setText("Sem 1 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem2")) {
                        sem1Gpa.setText("Sem 2 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem3")) {
                        sem1Gpa.setText("Sem 3 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem4")) {
                        sem1Gpa.setText("Sem 4 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem5")) {
                        sem1Gpa.setText("Sem 5 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem6")) {
                        sem1Gpa.setText("Sem 6 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem7")) {
                        sem1Gpa.setText("Sem 7 gpa   : " + gpa.getGpa());
                    } else if (gpaType.equals("sem8")) {
                        sem1Gpa.setText("Sem 8 gpa   : " + gpa.getGpa());
                    }
                }
            }
        }

        gradeWithGpvMap.put("A+", 4.0);
        gradeWithGpvMap.put("A", 4.0);
        gradeWithGpvMap.put("A-", 3.7);
        gradeWithGpvMap.put("B+", 3.3);
        gradeWithGpvMap.put("B", 3.0);
        gradeWithGpvMap.put("B-", 2.7);
        gradeWithGpvMap.put("C+", 2.3);
        gradeWithGpvMap.put("C", 2.0);
        gradeWithGpvMap.put("D+", 1.7);
        gradeWithGpvMap.put("D", 1.3);
        gradeWithGpvMap.put("D-", 1.0);

        Student studentFromDb = gpaCalDao.getUserByUserId(userId);
        List<PredictReportResult> coreSubjectList = new ArrayList<>();
        List<PredictReportResult> electiveSubjectList = new ArrayList<>();
        if (studentFromDb != null) {
            Student student = gpaCalDao.getUserCoreCourses(userId, studentFromDb.getStream(), studentFromDb.getCombination());
            if (student != null) {
                if (student.getSubjectList() != null) {
                    for (Course course : student.getSubjectList()) {
                        if (course != null) {
                            if (course.getResult() != null) {
                                if (course.getResult().getResultGrade() != null) {
                                    PredictReportResult predictReportResult = new PredictReportResult();
                                    predictReportResult.setSubjectCode(course.getCourseCode());
                                    predictReportResult.setSubjectName(course.getCourseName());
                                    predictReportResult.setResultGrade(course.getResult().getResultGrade());
                                    predictReportResult.setSubjectCredit(course.getCourseCredits());
                                    predictReportResult.setGpv(gradeWithGpvMap.get(course.getResult().getResultGrade()));
                                    if (course.getCourseType().equals("ELECTIVE")) {
                                        electiveSubjectList.add(predictReportResult);
                                    } else if (course.getCourseType().equals("CORE")) {
                                        coreSubjectList.add(predictReportResult);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        electiveSubjectsGpa.setText("Elective gpa : " + Math.round(calculateGpa(electiveSubjectList) * 100.0) / 100.0);
        coreSubjectsGpa.setText("Core gpa : " + Math.round(calculateGpa(electiveSubjectList) * 100.0) / 100.0);
    }

    private Double calculateGpa(List<PredictReportResult> SemResults) {
        int creditsCount = 0;
        double multiplicationOFCreditAndGpv = 0.0;
        for (PredictReportResult predictReportResult : SemResults) {
            creditsCount = creditsCount + predictReportResult.getSubjectCredit();
            multiplicationOFCreditAndGpv = multiplicationOFCreditAndGpv + (predictReportResult.getSubjectCredit() *
                    predictReportResult.getGpv());
        }
        return multiplicationOFCreditAndGpv / creditsCount;
    }
}