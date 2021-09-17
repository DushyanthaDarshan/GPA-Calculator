package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.PredictReportResult;
import com.TeamPhoenix.gpaCalculator.beans.Subject;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PredictionPage {

//    private List<String> semGpaList = Arrays.asList("sem1, sem2, sem3, sem4, sem5, sem6");
//    private List<String> gradeList = Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C");
//    private List<Double> gpvList = Arrays.asList(4.0, 4.0, 3.7, 3.3, 3.0, 2.7, 2.4, 2.1);
    private Map<String, Double> gradeWithGpvMap = new HashMap<>();
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PredictionPage window = new PredictionPage();
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
    public PredictionPage() {
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

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1000, 750);
        panel.add(panel_1);
        panel_1.setLayout(null);

        populatePrediction();
    }

    private void populatePrediction () {
        gradeWithGpvMap.put("A+", 4.0);
        gradeWithGpvMap.put("A", 4.0);
        gradeWithGpvMap.put("A-", 3.7);
        gradeWithGpvMap.put("B+", 3.3);
        gradeWithGpvMap.put("B", 3.0);
        gradeWithGpvMap.put("B-", 2.7);
        gradeWithGpvMap.put("C+", 2.4);
        gradeWithGpvMap.put("C", 2.1);

        GpaCalDao gpaCalDao = new GpaCalDaoImpl();
        User userFromDb = gpaCalDao.getUserDetailsByUsername("dushyantha1208@gmail.com");
        if (userFromDb != null) {
            Long userId = userFromDb.getUserId();
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
            User userWithNextSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(sizeOfList, userId);
            if (userWithNextSemSubjects != null) {
//                Gpa overallGpa = gpaCalDao.getOverallGpa(userId, "OVERALL");
                List<PredictReportResult> oldSemResults = new ArrayList<>();
                List<PredictReportResult> newSemResults = new ArrayList<>();
                for (int i = 0; sizeOfList > i; i++) {
                    User userWithSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(i + 1, userId);
                    if (userWithSemSubjects != null) {
                        for (Subject subject : userWithSemSubjects.getSubjectList()) {
                            PredictReportResult predictReportResult = new PredictReportResult();
                            predictReportResult.setSubjectName(subject.getSubjectName());
                            predictReportResult.setSubjectCode(subject.getSubjectCode());
                            predictReportResult.setSubjectCredit(subject.getSubjectCredits());
                            predictReportResult.setResultGrade(subject.getResult().getResultGrade());
                            predictReportResult.setGpv(gradeWithGpvMap.get(subject.getResult().getResultGrade()));
                            predictReportResult.setNewSem(false);
                            oldSemResults.add(predictReportResult);
                        }
                    }
                }
                for (Subject subject : userWithNextSemSubjects.getSubjectList()) {
                    PredictReportResult predictReportResult = new PredictReportResult();
                    predictReportResult.setSubjectName(subject.getSubjectName());
                    predictReportResult.setSubjectCode(subject.getSubjectCode());
                    predictReportResult.setSubjectCredit(subject.getSubjectCredits());
                    predictReportResult.setResultGrade("C");
                    predictReportResult.setGpv(gradeWithGpvMap.get("C"));
                    predictReportResult.setNewSem(true);
                    newSemResults.add(predictReportResult);
                }

                List<PredictReportResult> thirdUpperList = new ArrayList<>();
                List<PredictReportResult> secondLowerList = new ArrayList<>();
                List<PredictReportResult> secondUpperList = new ArrayList<>();
                List<PredictReportResult> firstClassList = new ArrayList<>();
                boolean isFirstClassFound = false;
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
                        if (newGpa >= 2.7 && newGpa < 3.0) {
                            thirdUpperList.add(predictReportResult);
                        } else if (newGpa >= 3.0 && newGpa < 3.3) {
                            secondLowerList.add(predictReportResult);
                        } else if (newGpa >= 3.3 && newGpa < 3.7) {
                            secondUpperList.add(predictReportResult);
                        } else if (newGpa >= 3.7) {
                            firstClassList.add(predictReportResult);
                            isFirstClassFound = true;
                            break;
                        }
                    }
                }
                System.out.println("thirdUpperList"  + new Gson().toJson(thirdUpperList));
                System.out.println("secondLowerList"  + new Gson().toJson(secondLowerList));
                System.out.println("secondUpperList" + new Gson().toJson(secondUpperList));
                System.out.println("firstClassList" + new Gson().toJson(firstClassList));
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
