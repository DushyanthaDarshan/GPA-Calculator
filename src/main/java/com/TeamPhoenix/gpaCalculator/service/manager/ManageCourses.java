package com.TeamPhoenix.gpaCalculator.service.manager;

import com.TeamPhoenix.gpaCalculator.beans.Course;
import com.TeamPhoenix.gpaCalculator.service.dao.GpaCalDao;
import com.TeamPhoenix.gpaCalculator.service.dao.Impl.GpaCalDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class ManageCourses {

    private JFrame frame;
    private Long userId;
    private JTextField courseNameTextArea;
    private JTextField codeTextArea;
    private JTable table;
    private DefaultTableModel model;
    private Integer semNumber;
    private List<Course> allCourseForSem;

    GpaCalDao gpaCalDao = new GpaCalDaoImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManageCourses window = new ManageCourses(1l);
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
    public ManageCourses(Long userId) {
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

        JLabel pageTitle = new JLabel("Manage Courses");
        pageTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pageTitle.setBounds(380, 13, 300, 40);
        pageTitle.setForeground(new Color(255, 255, 255));
        pageTitle.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(pageTitle);

        JLabel courseUnit = new JLabel("Select a Semester: ");
        courseUnit.setFont(new Font("Tahoma", Font.BOLD, 18));
        courseUnit.setBounds(104, 80, 200, 16);
        courseUnit.setForeground(new Color(255, 255, 255));
        courseUnit.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(courseUnit);

        JComboBox selectSem = new JComboBox();
        selectSem.setModel(new DefaultComboBoxModel(new String[] {"Select", "Semester 1", "Semester 2", "Semester 3", "Semester 4",
                "Semester 5", "Semester 6", "Semester 7", "Semester 8"}));
        selectSem.setBounds(300, 80, 120, 22);
        frame.getContentPane().add(selectSem);

        JLabel addCourseLabel = new JLabel("Add a Course");
        addCourseLabel.setBounds(20, 120, 300, 15);
        addCourseLabel.setForeground(new Color(255, 255, 255));
        addCourseLabel.setBackground(new Color(255, 255, 255));
        addCourseLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(addCourseLabel);

        //Name related
        JLabel courseNameLabel = new JLabel("Name : ");
        courseNameLabel.setBounds(20, 160, 90, 15);
        courseNameLabel.setForeground(new Color(255, 255, 255));
        courseNameLabel.setBackground(new Color(255, 255, 255));
        courseNameLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(courseNameLabel);

        courseNameTextArea = new JTextField();
        courseNameTextArea.setBounds(110, 160, 200, 20);
        courseNameTextArea.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        courseNameTextArea.setForeground(new Color(255, 255, 255));
        courseNameTextArea.setCaretColor(new Color(255, 255, 255));
        courseNameTextArea.setBackground(new Color(0, 46, 98));
        courseNameTextArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(courseNameTextArea);
        courseNameTextArea.setColumns(10);

        //Code related
        JLabel codeLabel = new JLabel("Code : ");
        codeLabel.setBounds(480, 160, 90, 15);
        codeLabel.setForeground(new Color(255, 255, 255));
        codeLabel.setBackground(new Color(255, 255, 255));
        codeLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(codeLabel);

        codeTextArea = new JTextField();
        codeTextArea.setBounds(550, 160, 200, 20);
        codeTextArea.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        codeTextArea.setForeground(new Color(255, 255, 255));
        codeTextArea.setCaretColor(new Color(255, 255, 255));
        codeTextArea.setBackground(new Color(0, 46, 98));
        codeTextArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.getContentPane().add(codeTextArea);
        codeTextArea.setColumns(10);

        //credits related
        JLabel courseCredits = new JLabel("Credits : ");
        courseCredits.setBounds(20, 200, 90, 15);
        courseCredits.setForeground(new Color(255, 255, 255));
        courseCredits.setBackground(new Color(255, 255, 255));
        courseCredits.setFont(new Font("Dialog", Font.PLAIN, 15));
        frame.getContentPane().add(courseCredits);

        JComboBox credits = new JComboBox();
        credits.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6}));
        credits.setBounds(110, 200, 120, 22);
        frame.getContentPane().add(credits);

        model = new DefaultTableModel();
        model.addColumn("Course Code");
        model.addColumn("Course Name");
        model.addColumn("Credits");

        JButton addButton = new JButton("ADD");
        addButton.setBounds(300, 240, 97, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String courseName = courseNameTextArea.getText();
                String code = codeTextArea.getText();
                Integer creditCount = (Integer) credits.getSelectedItem();
                String sem = (String) selectSem.getSelectedItem();

                Integer baseId = null;
                try {
                    String baseCategoryId = code.charAt(0) + String.valueOf(code.charAt(1));
                    if (baseCategoryId.equals("PH")) {
                        baseId = 1;
                    } else if (baseCategoryId.equals("AM")) {
                        baseId = 2;
                    } else if (baseCategoryId.equals("CH")) {
                        baseId = 3;
                    } else if (baseCategoryId.equals("ST")) {
                        baseId = 4;
                    } else if (baseCategoryId.equals("PM")) {
                        baseId = 5;
                    } else if (baseCategoryId.equals("CS")) {
                        baseId = 6;
                    }
                } catch (Exception e) {
                    System.out.println("Cannot convert to integer to base Id");
                }

                boolean status = validateInputs(courseName, code);
                if (status) {
                    Course courseFromDb = gpaCalDao.getCourseByCode(code);
                    if (courseFromDb == null) {
                        Course course = new Course();
                        course.setCourseName(courseName);
                        course.setCourseCode(code);
                        course.setCourseCredits(creditCount);
                        course.setSemesterNumber(semNumber);
                        course.setSubjectBaseCategoryId(baseId);
                        while (model.getRowCount() != 0) {
                            model.removeRow(0);
                        }
                        model.addRow(new Object[]{course.getCourseCode(), course.getCourseName(), creditCount});
                        gpaCalDao.saveCourse(course);
                        populateAlreadyAddedCourses();
                    } else {
                        JOptionPane.showMessageDialog(addButton, "Course already added", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        frame.getContentPane().add(addButton);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(400, 240, 97, 25);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                String courseCode = (String) table.getValueAt(row, 0);
                gpaCalDao.deleteCourse(courseCode);
                model.removeRow(row);
            }
        });
        frame.getContentPane().add(btnDelete);

        JPanel panel = new JPanel();
        panel.setBounds(240, 280, 500, 420);
        panel.setBackground(new Color(0, 46, 98));

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        table.setRowHeight(30);
        table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(300);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        panel.add(new JScrollPane(table));
        frame.add(panel);

        addCourseLabel.setVisible(false);
        courseNameLabel.setVisible(false);
        courseNameTextArea.setVisible(false);
        codeLabel.setVisible(false);
        codeTextArea.setVisible(false);
        courseCredits.setVisible(false);
        credits.setVisible(false);
        addButton.setVisible(false);
        btnDelete.setVisible(false);
        panel.setVisible(false);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.BLACK);
        backBtn.setFont(new Font("Sitka Text", Font.BOLD, 18));
        backBtn.setBackground(new Color(239, 205, 68));
        backBtn.setBounds(10, 418, 100, 30);
        frame.getContentPane().add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdminHome(userId);
                frame.dispose();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                Integer newCredits = null;
                String newName = null;
                if (column == 2) {
                    Integer[] choices = {1, 2, 3, 4, 5, 6};
                    newCredits = (Integer) JOptionPane.showInputDialog(frame, "Choose course credits...",
                            "Edit course credits", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
                    if (newCredits != null) {
                        table.setValueAt(newCredits, row, column);
                    }
                }
                if (column == 1) {
                    newName = (String) JOptionPane.showInputDialog(frame, "Enter course name...",
                            "Edit course name", JOptionPane.QUESTION_MESSAGE);
                    if (newName != null) {
                        table.setValueAt(newName, row, column);
                    }
                }
                if (newCredits != null || newName != null) {
                    Course courseFromDb = gpaCalDao.getCourseByCode((String) table.getValueAt(row, 0));
                    Course course = new Course();
                    course.setCourseName(newName);
                    course.setCourseCredits(newCredits);
                    course.setCourseCode(courseFromDb.getCourseCode());
                    course.setCourseId(courseFromDb.getCourseId());
                    gpaCalDao.updateCourse(course);
                    populateAlreadyAddedCourses();
                }
            }
        });

        JLabel rightSideBackground = new JLabel("");
        rightSideBackground.setIcon(new ImageIcon("src/main/java/com/TeamPhoenix/gpaCalculator/service/imgs/left_side_signup_page.jpg"));
        rightSideBackground.setHorizontalAlignment(SwingConstants.CENTER);
        rightSideBackground.setBounds(0, 0, 1000, 750);
        frame.getContentPane().add(rightSideBackground);

        selectSem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == selectSem) {
                    if (!Objects.equals(selectSem.getSelectedItem(), "Select")) {
                        try {
                            char semN = selectSem.getSelectedItem().toString().charAt(9);
                            semNumber = Integer.parseInt(String.valueOf(semN));
                        } catch (Exception e) {
                            System.out.println("Cannot convert to integer to semester number");
                        }
                        populateAlreadyAddedCourses();

                        addCourseLabel.setVisible(true);
                        courseNameLabel.setVisible(true);
                        courseNameTextArea.setVisible(true);
                        codeLabel.setVisible(true);
                        codeTextArea.setVisible(true);
                        courseCredits.setVisible(true);
                        credits.setVisible(true);
                        addButton.setVisible(true);
                        btnDelete.setVisible(true);
                        panel.setVisible(true);
                    } else {
                        while (model.getRowCount() != 0) {
                            model.removeRow(0);
                        }
                        addCourseLabel.setVisible(false);
                        courseNameLabel.setVisible(false);
                        courseNameTextArea.setVisible(false);
                        codeLabel.setVisible(false);
                        codeTextArea.setVisible(false);
                        courseCredits.setVisible(false);
                        credits.setVisible(false);
                        addButton.setVisible(false);
                        btnDelete.setVisible(false);
                        panel.setVisible(false);
                    }
                }
            }
        });
    }

    private boolean validateInputs(String courseName, String code) {
        boolean isValidInputs = true;
        if (StringUtils.isBlank(courseName)) {
            JOptionPane.showMessageDialog(courseNameTextArea, "Course name should not be empty", "Error", JOptionPane.WARNING_MESSAGE);
            isValidInputs = false;
        }
        if (courseName.length() > 250) {
            JOptionPane.showMessageDialog(courseNameTextArea, "Course name length should not be exceed 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
            isValidInputs = false;
        }
        if (StringUtils.isBlank(code)) {
            JOptionPane.showMessageDialog(courseNameTextArea, "Course code should not be empty", "Error", JOptionPane.WARNING_MESSAGE);
            isValidInputs = false;
        }
        if (code.length() > 25) {
            JOptionPane.showMessageDialog(courseNameTextArea, "Course code length should not be exceed 25 characters", "Error", JOptionPane.WARNING_MESSAGE);
            isValidInputs = false;
        }
        return isValidInputs;
    }

    private void populateAlreadyAddedCourses() {
        allCourseForSem = gpaCalDao.getAllSubjectsBySemNo(semNumber);
        if (allCourseForSem != null) {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            for (Course course : allCourseForSem) {
                if (course != null) {
                    model.addRow(new Object[]{course.getCourseCode(), course.getCourseName(), course.getCourseCredits()});
                }
            }
        }
    }
}
