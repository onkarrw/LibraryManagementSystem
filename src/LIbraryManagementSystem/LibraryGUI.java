package LIbraryManagementSystem;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryGUI extends JFrame {

	// GUI components
	private JLabel studentLabel, bookLabel, dateLabel;
	private JComboBox<String> studentDropdown, bookDropdown;
	private JButton takeButton, returnButton;

	// database connection
	private Connection conn;

	JFrame frame = new JFrame();

	public LibraryGUI() {
		// set up GUI components

		studentLabel = new JLabel("Student:");
		bookLabel = new JLabel("Book:");
		dateLabel = new JLabel("Date: " + LocalDate.now().toString());
		studentDropdown = new JComboBox<String>();
		bookDropdown = new JComboBox<String>();
		takeButton = new JButton("Take Book");
		returnButton = new JButton("Return Book");

		// set up event listeners

		// set up layout
		setLayout(new GridLayout(8, 2));
		add(studentLabel);
		add(studentDropdown);
		add(bookLabel);
		add(bookDropdown);
		add(dateLabel);
		add(new JLabel()); // empty label for spacing
		add(takeButton);
		add(returnButton);

		// set up database connection
		try {
			String url = "jdbc:mysql://localhost:3306/centrallibrary";
			String username = "root";
			String password = "Onkar@21";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection established.");

			// load students and books into dropdown menus
			loadStudents();
			loadBooks();
		} catch (SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
		}

		// set window properties
		setTitle("Library");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		JButton backButton = new JButton("Back");
		backButton.setBounds(100, 570, 100, 30);
		backButton.setFont(backButton.getFont().deriveFont(16f));

		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(550, 570, 100, 30);
		exitButton.setFont(exitButton.getFont().deriveFont(16f));

		add(backButton);
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

	}

	private void loadStudents() {
		// clear existing items and add default "Select Student" item
		studentDropdown.removeAllItems();
		studentDropdown.addItem("Select Student");

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student_name FROM student");
			while (rs.next()) {
				String name = rs.getString("student_name");
				studentDropdown.addItem(name);
			}
		} catch (SQLException e) {
			System.out.println("Error loading students: " + e.getMessage());
		}

		takeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// get selected student and book
				String student = (String) studentDropdown.getSelectedItem();
				String book = (String) bookDropdown.getSelectedItem();

				// check if a student and book are selected
				if (student.equals("Select Student") || book.equals("Select Book")) {
					JOptionPane.showMessageDialog(frame, "Please select a student and book.");
					return;
				}

				try {
					// get student ID and book ID from database
					int studentID = 0;
					int bookID = 0;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT student_id FROM student WHERE student_name = '" + student + "'");
					if (rs.next()) {
						studentID = rs.getInt("student_id");
					}
					rs = stmt.executeQuery(
							"SELECT book_id, available_books FROM book WHERE book_title = '" + book + "'");
					if (rs.next()) {
						bookID = rs.getInt("book_id");
						int availableBooks = rs.getInt("available_books");
						if (availableBooks == 0) {
							JOptionPane.showMessageDialog(frame, "No copies of this book are available.");
							return;
						} else {
							// decrement available books in database
							stmt.executeUpdate("UPDATE book SET available_books = " + (availableBooks - 1)
									+ " WHERE book_id = " + bookID);
						}
					}

					// insert new record into library table in database
					LocalDate dateTaken = LocalDate.now();
					PreparedStatement pstmt = conn
							.prepareStatement("INSERT INTO library (student_id, book_id, date_taken) VALUES (?, ?, ?)");

					pstmt.setInt(1, studentID);
					pstmt.setInt(2, bookID);
					pstmt.setDate(3, Date.valueOf(dateTaken));
					pstmt.executeUpdate();

					JOptionPane.showMessageDialog(null, "Book taken successfully.");
				} catch (SQLException ex) {
					System.out.println("Error taking book: " + ex.getMessage());
				}
			}
		});

		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// get selected student and book
				String student = (String) studentDropdown.getSelectedItem();
				String book = (String) bookDropdown.getSelectedItem();

				// check if a student and book are selected
				if (student.equals("Select Student") || book.equals("Select Book")) {
					JOptionPane.showMessageDialog(frame, "Please select a student and book.");
					return;
				}

				try {
					// get student ID and book ID from database
					int studentID = 0;
					int bookID = 0;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT student_id FROM student WHERE student_name = '" + student + "'");
					if (rs.next()) {
						studentID = rs.getInt("student_id");
					}
					rs = stmt.executeQuery(
							"SELECT book_id, available_books FROM book WHERE book_title = '" + book + "'");
					if (rs.next()) {
						bookID = rs.getInt("book_id");
						int availableBooks = rs.getInt("available_books");

						// increment available books in database
						stmt.executeUpdate("UPDATE book SET available_books = " + (availableBooks + 1)
								+ " WHERE book_id = " + bookID);
					}

					// delete record from library table in database
					LocalDate dateReturned = LocalDate.now();
					PreparedStatement pstmt = conn
							.prepareStatement("DELETE FROM library WHERE student_id = ? AND book_id = ?");
					pstmt.setInt(1, studentID);
					pstmt.setInt(2, bookID);
					pstmt.executeUpdate();

					JOptionPane.showMessageDialog(frame, "Book returned successfully.");
				} catch (SQLException ex) {
					System.out.println("Error returning book: " + ex.getMessage());

				}
			}
		});
	}

	private void loadBooks() {
		// clear existing items and add default "Select Student" item
		bookDropdown.removeAllItems();
		bookDropdown.addItem("Select Book");

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT book_title FROM book");
			while (rs.next()) {
				String name = rs.getString("book_title");
				bookDropdown.addItem(name);
			}
		} catch (SQLException e) {
			System.out.println("Error loading students: " + e.getMessage());
		}
	}
}
