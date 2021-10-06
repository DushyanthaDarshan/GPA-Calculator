package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.PredictReportResult;
import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.beans.Student;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PredictionPage {

//    private List<String> semGpaList = Arrays.asList("sem1, sem2, sem3, sem4, sem5, sem6");
//    private List<String> gradeList = Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C");
//    private List<Double> gpvList = Arrays.asList(4.0, 4.0, 3.7, 3.3, 3.0, 2.7, 2.4, 2.1);
    private Map<String, Double> gradeWithGpvMap = new HashMap<>();
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private Long userId;
    private Integer semNumber;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PredictionPage window = new PredictionPage(1l);
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
    public PredictionPage(Long userId) {
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

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1000, 750);
        panel.add(panel_1);
        panel_1.setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("Course Unit");
        model.addColumn("Course Name");
        model.addColumn("First Class");
        model.addColumn("Second Upper Class");
        model.addColumn("Second Lower Class");
        model.addColumn("General Normal Class");

        populatePrediction();

        JLabel semesterNumberLabel = new JLabel("Prediction for Semester " + semNumber);
        semesterNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        semesterNumberLabel.setBounds(359, 13, 500, 40);
        semesterNumberLabel.setForeground(new Color(255, 255, 255));
        semesterNumberLabel.setBackground(new Color(255, 255, 255));
        panel_1.add(semesterNumberLabel);

        JPanel panelForTable = new JPanel();
        panelForTable.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Courses with Predict Results", TitledBorder.CENTER, TitledBorder.TOP));
        panelForTable.setBounds(50, 100, 900, 500);

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        table.setRowHeight(30);
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(200);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 50));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        table.setSize(800, 500);
        table.setPreferredScrollableViewportSize(table.getSize());
        table.setFillsViewportHeight(true);
        panelForTable.add(new JScrollPane(table));
        panel_1.add(panelForTable);

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/right_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 1000, 750);
        panel_1.add(rightSideBackground);
    }

    private void populatePrediction () {
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

        GpaCalDao gpaCalDao = new GpaCalDaoImpl();
        if (userId != null) {
            List<Gpa> gpaListFromDb = gpaCalDao.getAllGpaByUserId(userId);
            List<Integer> semNumbersList = new ArrayList<>();
            for (Gpa gpa : gpaListFromDb) {
                try {
                    semNumbersList.add(Integer.parseInt(String.valueOf(gpa.getGpaType().charAt(3))));
                } catch (Exception e) {
                    System.out.println("Prediction page : Cannot convert to integer");
                }
            }
            int sizeOfList = semNumbersList.size();
            semNumber = sizeOfList + 1;
            Student studentWithNextSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(sizeOfList + 1, userId);
            if (studentWithNextSemSubjects != null) {
//                Gpa overallGpa = gpaCalDao.getOverallGpa(userId, "OVERALL");
                List<PredictReportResult> oldSemResults = new ArrayList<>();
                List<PredictReportResult> newSemResults = new ArrayList<>();
                for (int i = 0; sizeOfList > i; i++) {
                    Student studentWithSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(i + 1, userId);
                    if (studentWithSemSubjects != null) {
                        for (Course course : studentWithSemSubjects.getSubjectList()) {
                            PredictReportResult predictReportResult = new PredictReportResult();
                            predictReportResult.setSubjectName(course.getCourseName());
                            predictReportResult.setSubjectCode(course.getCourseCode());
                            predictReportResult.setSubjectCredit(course.getCourseCredits());
                            predictReportResult.setResultGrade(course.getResult().getResultGrade());
                            predictReportResult.setGpv(gradeWithGpvMap.get(course.getResult().getResultGrade()));
                            predictReportResult.setNewSem(false);
                            oldSemResults.add(predictReportResult);
                        }
                    }
                }
                for (Course course : studentWithNextSemSubjects.getSubjectList()) {
                    PredictReportResult predictReportResult = new PredictReportResult();
                    predictReportResult.setSubjectName(course.getCourseName());
                    predictReportResult.setSubjectCode(course.getCourseCode());
                    predictReportResult.setSubjectCredit(course.getCourseCredits());
                    predictReportResult.setResultGrade("C");
                    predictReportResult.setGpv(gradeWithGpvMap.get("C"));
                    predictReportResult.setNewSem(true);
                    newSemResults.add(predictReportResult);
                }

                List<PredictReportResult> thirdUpperList = new ArrayList<>();
                List<PredictReportResult> secondLowerList = new ArrayList<>();
                List<PredictReportResult> secondUpperList = new ArrayList<>();
                List<PredictReportResult> firstClassList = new ArrayList<>();
                Map<String, List<PredictReportResult>> classesMap = new HashMap<>();
                boolean isFirstClassFound = false;
                boolean isSecondUpperClassFound = false;
                boolean isSecondLowerClassFound = false;
                boolean isThirdUpperClassFound = false;
                while (!isFirstClassFound) {
                    for (PredictReportResult predictReportResult : newSemResults) {
                        if (predictReportResult.getResultGrade().equals("C")) {
                            predictReportResult.setResultGrade("C+");
                            predictReportResult.setGpv(gradeWithGpvMap.get("C+"));
                        } else if (predictReportResult.getResultGrade().equals("C+")) {
                            predictReportResult.setResultGrade("B-");
                            predictReportResult.setGpv(gradeWithGpvMap.get("B-"));
                        } else if (predictReportResult.getResultGrade().equals("B-")) {
                            predictReportResult.setResultGrade("B");
                            predictReportResult.setGpv(gradeWithGpvMap.get("B"));
                        } else if (predictReportResult.getResultGrade().equals("B")) {
                            predictReportResult.setResultGrade("B+");
                            predictReportResult.setGpv(gradeWithGpvMap.get("B+"));
                        } else if (predictReportResult.getResultGrade().equals("B+")) {
                            predictReportResult.setResultGrade("A-");
                            predictReportResult.setGpv(gradeWithGpvMap.get("A-"));
                        } else if (predictReportResult.getResultGrade().equals("A-")) {
                            predictReportResult.setResultGrade("A");
                            predictReportResult.setGpv(gradeWithGpvMap.get("A"));
                        }
                        double newGpa = calculateGpa(oldSemResults, newSemResults);
                        if (newGpa < 3.0) {
                            if (!isThirdUpperClassFound) {
                                for (PredictReportResult predictReportResult1 : newSemResults) {
                                    PredictReportResult predictReportResult2 = new PredictReportResult();
                                    predictReportResult2.setSubjectName(predictReportResult1.getSubjectName());
                                    predictReportResult2.setSubjectCode(predictReportResult1.getSubjectCode());
                                    predictReportResult2.setResultGrade(predictReportResult1.getResultGrade());
                                    predictReportResult2.setSubjectCredit(predictReportResult1.getSubjectCredit());
                                    predictReportResult2.setGpv(predictReportResult1.getGpv());
                                    predictReportResult2.setNewSem(false);
                                    thirdUpperList.add(predictReportResult2);
                                }
                                isThirdUpperClassFound = true;
                            }
                        } else if (newGpa >= 3.0 && newGpa < 3.3) {
                            if (!isSecondLowerClassFound) {
                                for (PredictReportResult predictReportResult1 : newSemResults) {
                                    PredictReportResult predictReportResult2 = new PredictReportResult();
                                    predictReportResult2.setSubjectName(predictReportResult1.getSubjectName());
                                    predictReportResult2.setSubjectCode(predictReportResult1.getSubjectCode());
                                    predictReportResult2.setResultGrade(predictReportResult1.getResultGrade());
                                    predictReportResult2.setSubjectCredit(predictReportResult1.getSubjectCredit());
                                    predictReportResult2.setGpv(predictReportResult1.getGpv());
                                    predictReportResult2.setNewSem(false);
                                    secondLowerList.add(predictReportResult2);
                                }
                                isSecondLowerClassFound = true;
                            }
                        } else if (newGpa >= 3.3 && newGpa < 3.7) {
                            if (!isSecondUpperClassFound) {
                                for (PredictReportResult predictReportResult1 : newSemResults) {
                                    PredictReportResult predictReportResult2 = new PredictReportResult();
                                    predictReportResult2.setSubjectName(predictReportResult1.getSubjectName());
                                    predictReportResult2.setSubjectCode(predictReportResult1.getSubjectCode());
                                    predictReportResult2.setResultGrade(predictReportResult1.getResultGrade());
                                    predictReportResult2.setSubjectCredit(predictReportResult1.getSubjectCredit());
                                    predictReportResult2.setGpv(predictReportResult1.getGpv());
                                    predictReportResult2.setNewSem(false);
                                    secondUpperList.add(predictReportResult2);
                                }
                                isSecondUpperClassFound = true;
                            }
                        } else if (newGpa >= 3.7) {
                            firstClassList.addAll(newSemResults);
                            isFirstClassFound = true;
                            break;
                        }
                    }
                }
                System.out.println("thirdUpperList"  + new Gson().toJson(thirdUpperList));
                System.out.println("secondLowerList"  + new Gson().toJson(secondLowerList));
                System.out.println("secondUpperList" + new Gson().toJson(secondUpperList));
                System.out.println("firstClassList" + new Gson().toJson(firstClassList));

                for (PredictReportResult predictReportResult : firstClassList) {
                    if (predictReportResult != null) {
                        String subjectCode = predictReportResult.getSubjectCode();
                        String subjectName = predictReportResult.getSubjectName();
                        Integer subjectCredit = predictReportResult.getSubjectCredit();
                        String firstClassResultGrade = predictReportResult.getResultGrade();
                        String secondUpperClassResultGrade = predictReportResult.getResultGrade();
                        String secondLowerClassResultGrade = predictReportResult.getResultGrade();
                        String generalBelowClassResultGrade = predictReportResult.getResultGrade();
                        for (PredictReportResult predictReportResult2ndUpperClass : secondUpperList) {
                            if (predictReportResult2ndUpperClass != null) {
                                if (predictReportResult2ndUpperClass.getSubjectCode().equals(subjectCode)) {
                                    secondUpperClassResultGrade = predictReportResult2ndUpperClass.getResultGrade();
                                }
                            }
                        }
                        for (PredictReportResult predictReportResult2ndLowerClass : secondLowerList) {
                            if (predictReportResult2ndLowerClass != null) {
                                if (predictReportResult2ndLowerClass.getSubjectCode().equals(subjectCode)) {
                                    secondLowerClassResultGrade = predictReportResult2ndLowerClass.getResultGrade();
                                }
                            }
                        }
                        for (PredictReportResult predictReportResultGeneralBelowClass : thirdUpperList) {
                            if (predictReportResultGeneralBelowClass != null) {
                                if (predictReportResultGeneralBelowClass.getSubjectCode().equals(subjectCode)) {
                                    generalBelowClassResultGrade = predictReportResultGeneralBelowClass.getResultGrade();
                                }
                            }
                        }
                        model.addRow(new Object[]{subjectCode, subjectName, firstClassResultGrade, secondUpperClassResultGrade,
                            secondLowerClassResultGrade, generalBelowClassResultGrade});
                    }
                }
            }
        }
    }

    private Double calculateGpa(List<PredictReportResult> oldSemResults, List<PredictReportResult> newSemResults) {
        int creditsCount = 0;
        double multiplicationOFCreditAndGpv = 0.0;
        List<PredictReportResult> allSemResults = new ArrayList<>();
        allSemResults.addAll(oldSemResults);
        allSemResults.addAll(newSemResults);
        for (PredictReportResult predictReportResult : allSemResults) {
            creditsCount = creditsCount + predictReportResult.getSubjectCredit();
            multiplicationOFCreditAndGpv = multiplicationOFCreditAndGpv + (predictReportResult.getSubjectCredit() *
                    predictReportResult.getGpv());
        }
        return multiplicationOFCreditAndGpv / creditsCount;
    }
}
