package LIbraryManagementSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame {

    public Student() throws Exception {
        super("Student Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/centralLibrary", "root", "Onkar@21");
        Statement stmt = con.createStatement();
        String selectQuery = "SELECT student_id, student_name, student_email FROM student";
        ResultSet rs = stmt.executeQuery(selectQuery);
        JTable table = new JTable(buildTableModel(rs));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainScreen obj = new mainScreen();

                obj.setVisible(true);
            }
        });
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
        // Create a table model based on the ResultSet metadata
        var metaData = rs.getMetaData();
        var columnCount = metaData.getColumnCount();
        var columnNames = new String[columnCount];
        for (int column = 1; column <= columnCount; column++) {
            columnNames[column - 1] = metaData.getColumnName(column);
        }

        // Add the data to the table model
        var data = new Object[20][columnCount]; // assuming no more than 20 students
        int rowCount = 0;
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row[columnIndex - 1] = rs.getObject(columnIndex);
            }
            data[rowCount] = row;
            rowCount++;
        }

        
        return new DefaultTableModel(data, columnNames);
    }
}
