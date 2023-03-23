package LIbraryManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Abdul Waheed
 */
public class Rules extends JFrame{
    
     public Rules()
    {
      initUI();    
    }
    
    private void initUI()
    {
   setBounds(0, 0, 900, 660);
      
      setName("mainFrame");
      
      setTitle("Welcome to Central Library");
     
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      
      
     JPanel Registration = new JPanel();
     Registration.setBackground(Color.white);
     add(Registration);
      
     Registration.setLayout(null);
     
     
     JLabel title = new JLabel();
    title .setBounds(330, 0, 500, 50);
     title .setText("Rules and Regulations");
      title.setFont(title.getFont().deriveFont(25f));
      title.setForeground(Color.white);
     Registration.add(title);

     
     Font f1 = new Font("Arial", Font.BOLD, 40);
     JLabel r = new JLabel();
     r .setBounds(350, 50, 800, 30);
      r .setText("Rules");
     r.setFont(f1);
       Registration.add(r);
     
  JLabel r1 = new JLabel();
  r1 .setBounds(100, 100, 800, 30);
   r1 .setText("1. Students are required to maintain silence in the library.");
  r1.setFont( r1.getFont().deriveFont(16f));
    Registration.add(r1);
    
    
     JLabel r2 = new JLabel();
  r2 .setBounds(100, 150, 800, 30);
   r2 .setText("2. Students must scan their ID card while entering the library.");
   r2.setFont( r2.getFont().deriveFont(16f));
    Registration.add(r2); 
    
    
     JLabel r3 = new JLabel();
  r3 .setBounds(100, 200, 800, 30);
   r3 .setText("3.Students are not allowed to bring any food, drinks, or smoke in the library.");
   r3.setFont( r3.getFont().deriveFont(16f));
    Registration.add(r3);
    
    
     JLabel r4 = new JLabel();
  r4 .setBounds(100, 250, 800, 30);
   r4 .setText("4. Students must return the books within the due date; otherwise, they will be charged a fine.");
   r4.setFont( r4.getFont().deriveFont(16f));
    Registration.add(r4);
    
    
     JLabel r5 = new JLabel();
  r5 .setBounds(100, 300, 800, 30);
   r5 .setText("5. Students are not allowed to bring their personal books inside the library.");
   r5.setFont( r5.getFont().deriveFont(16f));
    Registration.add(r5);
    
    
     JLabel r6 = new JLabel();
  r6 .setBounds(100, 350, 800, 30);
   r6 .setText("6. Students are not allowed to damage the books, furniture, or any other library property.");
   r6.setFont( r6.getFont().deriveFont(16f));
    Registration.add(r6);
  
    JLabel r7 = new JLabel();
  r7 .setBounds(100, 400, 800, 30);
   r7 .setText("7. Students must follow the dress code while visiting the library.");
   r7.setFont( r7.getFont().deriveFont(16f));
    Registration.add(r7);
    
       JLabel r8 = new JLabel();
  r8 .setBounds(100, 450, 800, 30);
   r8 .setText("8. Students are not allowed to bring any guests or outsiders into the library.");
   r8.setFont( r8.getFont().deriveFont(16f));
    Registration.add(r8);
    
JButton backButton  = new JButton("Back");
     backButton.setBounds(100, 570, 100, 30); 
     backButton.setFont(backButton.getFont().deriveFont(16f));

JButton exitButton = new JButton("Exit");
     exitButton.setBounds(550, 570, 100, 30);
     exitButton.setFont(exitButton.getFont().deriveFont(16f));


     
    
     Registration.add(backButton);
     Registration.add(exitButton); 
     
     this.setVisible(true);
     
     
        
     
     backButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent ee) {
                     Rules.this.setVisible(false);
                     mainScreen obj = new mainScreen();
                      obj.setVisible(true);
                  }
     });
     
     exitButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e1) {
                     System.exit(0);
                      
                  }
              });
}
    
    
}
