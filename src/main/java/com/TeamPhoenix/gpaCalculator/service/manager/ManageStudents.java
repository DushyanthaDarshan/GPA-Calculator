package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Student;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageStudents {

    private JFrame frame;
    private Long userId;
    private JTable table;
    private DefaultTableModel model;
    private List<Student> allStudents;

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManageStudents window = new ManageStudents(1l);
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
    public ManageStudents(Long userId) {
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

        JLabel pageTitle = new JLabel("Manage Students");
        pageTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pageTitle.setBounds(380, 13, 300, 40);
        pageTitle.setForeground(new Color(255, 255, 255));
        pageTitle.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(pageTitle);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(350, 600, 115, 30);
        btnDelete.setFont(new Font("Sitka Text", Font.BOLD, 18));
        btnDelete.setBackground(new Color(246, 84, 69));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                String userName = (String) table.getValueAt(row, 1);
                gpaCalDao.deleteStudent(userName);
                populateStudents();
            }
        });
        frame.getContentPane().add(btnDelete);

        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Username");
        model.addColumn("Stream");
        model.addColumn("Combination");
        model.addColumn("Degree");
        model.addColumn("Status");

        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 900, 420);
        panel.setBackground(new Color(0, 46, 98));

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        table.setRowHeight(30);
        table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        table.setSize(850, 500);
        table.setPreferredScrollableViewportSize(table.getSize());
        table.setFillsViewportHeight(true);
        columnModel.getColumn(0).setPreferredWidth(500);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(200);
        columnModel.getColumn(5).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        panel.add(new JScrollPane(table));
        frame.add(panel);

        populateStudents();

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.BLACK);
        backBtn.setFont(new Font("Sitka Text", Font.BOLD, 18));
        backBtn.setBackground(new Color(239, 205, 68));
        backBtn.setBounds(500, 600, 100, 30);
        frame.getContentPane().add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdminHome(userId);
                frame.dispose();
            }
        });

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(rightSideBackground);
    }

    private void populateStudents() {
        allStudents = gpaCalDao.getAllStudents();
        if (allStudents != null) {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            for (Student student : allStudents) {
                if (student != null) {
                    if (student.getUserType().equals("STUDENT")) {
                        model.addRow(new Object[]{student.getName(), student.getUsername(), student.getStream(), student.getCombination(),
                                student.getDegree(), student.getStatus()});
                    }
                }
            }
        }
    }
}
