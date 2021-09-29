package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Gpa;
import com.TeamPhoenix.gpaCalculator.beans.PredictReportResult;
import com.TeamPhoenix.gpaCalculator.beans.Subject;
import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

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

public class Gpv {
    /**
     * Creates new form Gpv
     */

    public Gpv() {
        initComponents();
    }

    private void initComponents() {

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
        JLabel nameLabel = new JLabel("Orverall GPA");
        nameLabel.setBounds(600, 63, 200, 30);
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setBackground(new Color(255, 255, 255));
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        panel_1.add(nameLabel);

        //get user name
        JLabel userNameLabel = new JLabel("Name : ");
        userNameLabel.setBounds(420, 125, 200, 25);
        userNameLabel.setForeground(new Color(255, 255, 255));
        userNameLabel.setBackground(new Color(255, 255, 255));
        userNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(userNameLabel);

        //get user Id
        JLabel userIndexNumberLabel = new JLabel("Index Number : ");
        userIndexNumberLabel.setBounds(420, 160, 200, 25);
        userIndexNumberLabel.setForeground(new Color(255, 255, 255));
        userIndexNumberLabel.setBackground(new Color(255, 255, 255));
        userIndexNumberLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(userIndexNumberLabel);

        //OrverAll GPA related
        JLabel OrverAllGPALabel = new JLabel("Orverall GPA : ");
        OrverAllGPALabel.setBounds(420, 220, 200, 25);
        OrverAllGPALabel.setForeground(new Color(255, 255, 255));
        OrverAllGPALabel.setBackground(new Color(25, 255, 255));
        OrverAllGPALabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(OrverAllGPALabel);

        JPanel gpaPanel = new JPanel();
        gpaPanel.setBounds(500, 250, 150, 25);
        gpaPanel.setBackground(new Color(60, 63, 65));

        //GPA by semester related
        JLabel GpaBySemesterLabel = new JLabel("GPA by semester : ");
        GpaBySemesterLabel.setBounds(420, 290, 200, 25);
        GpaBySemesterLabel.setForeground(new Color(255, 255, 255));
        GpaBySemesterLabel.setBackground(new Color(255, 255, 255));
        GpaBySemesterLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(GpaBySemesterLabel);

        String[] semList = {"Semester 01","Semester 02","Semester 03","Semester 04","Semester 05","Semester 06","Semester 07","Semester 08"};
        JComboBox selectSemester = new JComboBox(semList);
        selectSemester.setSelectedIndex(2);
        selectSemester.setEditable(true);
        selectSemester.setBounds(600, 290, 120, 25);
        selectSemester.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        selectSemester.setForeground(new Color(255, 255, 255));
        selectSemester.setBackground(new Color(1, 47, 142));
        selectSemester.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(selectSemester);

        JLabel selectSemesterLable = new JLabel("");
        selectSemesterLable.setBounds(600, 290, 120, 25);
        selectSemesterLable.setForeground(new Color(255, 0, 0));
        selectSemesterLable.setBackground(new Color(255, 255, 255));
        selectSemesterLable.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(selectSemesterLable);

        //GPA by Core Subject related
        JLabel GpaByCoreSubLabel = new JLabel("GPA by Core Subject : ");
        GpaByCoreSubLabel.setBounds(420, 360, 200, 25);
        GpaByCoreSubLabel.setForeground(new Color(255, 255, 255));
        GpaByCoreSubLabel.setBackground(new Color(255, 255, 255));
        GpaByCoreSubLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(GpaByCoreSubLabel);

        String[] SelectSubject = {"Chemistry","Physics","Pure Maths","Applied Maths","Management Science","Industrial Statistics","Financial Mathematics","Computer Science", "Statistics", "nuclear Science", "Botany", "Zoology", "Environment", "Molecular Biology"};
        JComboBox selectSubject = new JComboBox(SelectSubject);
        selectSubject.setSelectedIndex(2);
        selectSubject.setEditable(true);
        selectSubject.setBounds(600, 360, 120, 25);
        selectSubject.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        selectSubject.setForeground(new Color(255, 255, 255));
        selectSubject.setBackground(new Color(1, 47, 142));
        selectSubject.setFont(new Font("Dialog", Font.PLAIN, 14));
        panel_1.add(selectSubject);

        JLabel selectSubjectLable = new JLabel("");
        selectSubjectLable.setBounds(600, 360, 120, 25);
        selectSubjectLable.setForeground(new Color(255, 0, 0));
        selectSubjectLable.setBackground(new Color(255, 255, 255));
        selectSubjectLable.setFont(new Font("Dialog", Font.PLAIN, 20));
        panel_1.add(selectSubjectLable);

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
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

     setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     setBackground(new java.awt.Color(102, 102, 102));
     setBounds(new java.awt.Rectangle(100, 100, 1000, 750));
     setLocation(new java.awt.Point(0, 0));
     setPreferredSize(new java.awt.Dimension(1000, 750));
     setSize(new java.awt.Dimension(1000, 750));

     javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
     getContentPane().setLayout(layout);
     layout.setHorizontalGroup(
     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGap(0, 5208, Short.MAX_VALUE)
     );
     layout.setVerticalGroup(
     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGap(0, 1700, Short.MAX_VALUE)
     );

     pack();
     }// </editor-fold>//GEN-END:initComponents
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gpv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gpv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gpv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gpv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gpv window = new Gpv();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private Map<String, Double> gradeWithGpvMap = new HashMap<>();
    private JFrame frame;

    private void populatePrediction (){

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

        if(userFromDb != null){
            Long userId = userFromDb.getUserId();
            List<Gpa> gpaListFromDb = gpaCalDao.getAllGpaByUserId(userId);
            List<Integer> semNumbersList2 = new ArrayList<>();

            for(Gpa gpa : gpaListFromDb){
                try {
                    semNumbersList2.add(Integer.parseInt(String.valueOf(gpa.getGpaType().charAt(3))));
                } catch (Exception e) {
                    System.out.println("GPA page : Cannot convert to integer");
                }
            }

            int sizeOfList = semNumbersList2.size();
            //User userWithNextSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(sizeOfList, userId);

            //if(userWithNextSemSubjects != null){
            List<PredictReportResult> oldSemResults = new ArrayList<>();

            for(int i = 0; sizeOfList>i; i++){
                User userWithSemSubjects = gpaCalDao.getAllSubjectAndUserDetailsBySemNumber(i+1, userId);
                if(userWithSemSubjects != null){
                    for(Subject subject : userWithSemSubjects.getSubjectList()){
                        PredictReportResult predictReportResult = new PredictReportResult();

                        predictReportResult.setSubjectName(subject.getSubjectName());
                        predictReportResult.setSubjectCode(subject.getSubjectCode());
                        predictReportResult.setSubjectCredit(subject.getSubjectCredits());
                        predictReportResult.setGpv(gradeWithGpvMap.get(subject.getResult().getResultGrade()));
                        predictReportResult.setNewSem(false);
                        oldSemResults.add(predictReportResult);
                    }
                }
            }
        }
    }

    private Double calculateGpa(List<PredictReportResult> oldSemResults) {
        int creditsCount = 0;
        double multiplicationOFCreditAndGpv = 0.0;
        for (PredictReportResult predictReportResult : oldSemResults) {
            creditsCount = creditsCount + predictReportResult.getSubjectCredit();
            multiplicationOFCreditAndGpv = multiplicationOFCreditAndGpv + (predictReportResult.getSubjectCredit() *
                    predictReportResult.getGpv());
        }
        return multiplicationOFCreditAndGpv / creditsCount;
    }
}
