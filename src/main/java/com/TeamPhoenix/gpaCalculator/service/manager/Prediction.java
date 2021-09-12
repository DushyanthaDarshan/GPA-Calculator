package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.User;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaDaoImpl;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Prediction {
//    private GpaDao gpaDao;
    private JPanel predictionRootPanel;
    private JButton button1;

    public Prediction() {
        predictionRootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Prediction().predictionRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Prediction prediction = new Prediction();
        prediction.populateMainMethod();
    }

    private void populateMainMethod() {
        GpaDao gpaDao = new GpaDaoImpl();
        User user = gpaDao.getUserDetailsByUsername("dushyantha1208@gmail.com");
        System.out.println(user.getName());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
