package LIbraryManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton backButton, exitButton;
    private JButton studentRegistration, bookEntry, addBook, students;

    public Menu() {
        // Set up the frame
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the buttons for the menu items
        studentRegistration = new JButton("Student Registration");
        bookEntry = new JButton("Book Entry");
        addBook = new JButton("Add Book");
        students = new JButton("students");

        // Add the buttons to the frame
        JPanel menuPanel = new JPanel(new GridLayout(10, 2));
        menuPanel.add(studentRegistration);
        menuPanel.add(bookEntry);
        menuPanel.add(addBook);
        menuPanel.add(students);
        
        add(menuPanel, BorderLayout.CENTER);

        // Create the panel for the back and exit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Back");
        exitButton = new JButton("Exit");
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        // Add the panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
        
        

        studentRegistration.addActionListener((ActionEvent e) -> {
              this.setVisible(false);
              StudReg sr = new StudReg();
              sr.setVisible(true);
          });
        
        bookEntry.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            LibraryGUI sr = new LibraryGUI();
            sr.setVisible(true);
        });
      
        addBook.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
           AddBook sr = new AddBook();
            sr.setVisible(true);
        });
      
        
        students.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Student obj;
				try {
					obj = new Student();

					obj.setVisible(true);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}
		});
      
        
        
    }

  
}
