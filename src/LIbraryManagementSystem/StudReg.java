package LIbraryManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudReg extends JFrame implements ActionListener {
    JLabel nameLabel, emailLabel, IDLabel;
    JTextField nameField, emailField, IDField;
    JButton addButton, backButton, exitButton;

    Connection conn;
    Statement stmt;

    public StudReg() {
        super("Student Registration Form");
        setLayout(new GridLayout(8, 4));
        
      IDLabel = new JLabel("ID:");
        add(IDLabel);
        IDField = new JTextField(20);
        add(IDField);

        nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameField = new JTextField(20);
        add(nameField);

        emailLabel = new JLabel("Email:");
        add(emailLabel);
        emailField = new JTextField(20);
        add(emailField);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        add(exitButton);
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Menu obj = new Menu();

                obj.setVisible(true);
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/centrallibrary", "root", "Onkar@21");
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                int id = Integer.parseInt(IDField.getText());
                String name = nameField.getText();
                String email = emailField.getText();

                String sql = "INSERT INTO student (student_id, student_name, student_email) VALUES (" + id + ", '" + name + "', '" + email + "')";
                stmt.executeUpdate(sql);

                JOptionPane.showMessageDialog(null, "Student added successfully!");
                nameField.setText("");
                emailField.setText("");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == backButton) {
            // go back to previous screen
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private int getNextId() throws SQLException {
        int id = 1;
        ResultSet rs = stmt.executeQuery("SELECT MAX(student_id) FROM student");
        if (rs.next()) {
            id = rs.getInt(1) + 1;
        }
        return id;
    }

   
}
