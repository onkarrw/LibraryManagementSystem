package LIbraryManagementSystem;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddBook extends JFrame {
    private JLabel titleLabel, publisherLabel, totalBooksLabel, availableBooksLabel;
    private JTextField titleTextField, publisherTextField, totalBooksTextField, availableBooksTextField;
    private JButton addButton, backButton, exitButton;

    public AddBook() {
        // Set JFrame properties
        setTitle("Add Book");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout
        setLayout(new GridLayout(5, 2, 10, 10));

        // Create labels
        titleLabel = new JLabel("Title:");
        publisherLabel = new JLabel("Publisher:");
        totalBooksLabel = new JLabel("Total Books:");
        availableBooksLabel = new JLabel("Available Books:");

        // Create text fields
        titleTextField = new JTextField();
        publisherTextField = new JTextField();
        totalBooksTextField = new JTextField();
        availableBooksTextField = new JTextField();

        // Create buttons
        addButton = new JButton("Add");
        backButton = new JButton("Back");
        exitButton = new JButton("Exit");

        // Add components to JFrame
        add(titleLabel);
        add(titleTextField);
        add(publisherLabel);
        add(publisherTextField);
        add(totalBooksLabel);
        add(totalBooksTextField);
        add(availableBooksLabel);
        add(availableBooksTextField);
        add(addButton);
        add(backButton);

        // Add action listeners to buttons
        addButton.addActionListener(new AddButtonListener());
        backButton.addActionListener(new BackButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        // Show JFrame
        setVisible(true);
    }

    // Add button action listener
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
            // Get values from text fields
            String title = titleTextField.getText();
            String publisher = publisherTextField.getText();
            int totalBooks = Integer.parseInt(totalBooksTextField.getText());
            int availableBooks = Integer.parseInt(availableBooksTextField.getText());

            // Create database connection
            String url = "jdbc:mysql://localhost/centrallibrary";
            String username = "root";
            String password = "Onkar@21";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                // Create SQL statement
            	
            	 String sq = "SELECT MAX(book_id) FROM book";
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sq);
                 int maxBookId = 0; // Initialize maxBookId variable
                 if (rs.next()) {
                     maxBookId = rs.getInt(1); // Store the maximum value in maxBookId variable
                    }
                 
                 
                 
                String sql = "INSERT INTO book (book_id, book_title, book_publisher, total_books, available_books) " +
                             "VALUES ("+ maxBookId+", ?, ?, ?)";
                
               
                
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, title);
                statement.setString(2, publisher);
                statement.setInt(3, totalBooks);
                statement.setInt(4, availableBooks);

                // Execute SQL statement
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Book added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add book.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Back button action listener
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Hide JFrame
            setVisible(false);

            // Show main menu JFrame
            Menu ob = new Menu();
            ob.setVisible(true);
        }
    }

    // Exit button action listener
    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close application
            System.exit(0);
        }
    }
}
