package LIbraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainScreen extends JFrame {

	public mainScreen() {
		initUI();
	}

	private void initUI() {

		setBounds(0, 0, 900, 660);

		setName("mainFrame");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setLayout(null);
		add(container);
		JPanel panelLabel = new JPanel();
		panelLabel.setLayout(null);
		panelLabel.setBounds(0, 0, 900, 100);
		panelLabel.setBackground(new Color(0, 199, 0));
		// label panel and labe

		JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(29.0f));
		label.setForeground(Color.white);
		label.setText("Welcome To Central Library");
		label.setVerticalAlignment(JLabel.BOTTOM);
		label.setBounds(230, 5, 500, 60);
		container.add(panelLabel);
		panelLabel.add(label);

		ImageIcon icon = new ImageIcon("cl.jpg");
		JLabel lb = new JLabel(icon);
		lb.setBounds(198, 100, 702, 420);
		
		panelLabel.add(lb);
		
		
		lb.setIcon(icon);
		container.add(lb);
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(null);

		panelButton.setBackground(Color.black);
		panelButton.setBounds(0, 100, 200, 420);
		container.add(panelButton);
		JButton home = new JButton("Home");
		home.setBounds(0, 0, 200, 70);
		// home.setPreferredSize(new Dimension(80,80));
		home.setFont(home.getFont().deriveFont(18f));
		panelButton.add(home);
		JButton admin = new JButton("Admin");
		admin.setBounds(0, 70, 200, 70);
		admin.setFont(admin.getFont().deriveFont(18f));

		// facilities.setPreferredSize(new Dimension(80,80));

		panelButton.add(admin);

		JButton books = new JButton("books");

		books.setBounds(0, 140, 200, 70);

		books.setFont(books.getFont().deriveFont(18f));

		panelButton.add(books);

		JButton rules = new JButton("Rules");

		rules.setBounds(0, 210, 200, 70);

		rules.setFont(rules.getFont().deriveFont(18f));

		rules.setPreferredSize(new Dimension(80, 80));

		panelButton.add(rules);

		JButton one = new JButton("");

		one.setBounds(0, 280, 200, 70);

		one.setFont(rules.getFont().deriveFont(18f));

		one.setPreferredSize(new Dimension(80, 80));

		panelButton.add(one);
		JButton two = new JButton("");

		two.setBounds(0, 350, 200, 70);

		two.setFont(rules.getFont().deriveFont(18f));

		two.setPreferredSize(new Dimension(80, 80));

		panelButton.add(two);

		JPanel footer = new JPanel();

		footer.setBounds(0, 520, 900, 100);

		footer.setBackground(Color.pink);

		container.add(footer);

		JLabel footerLabel = new JLabel(
				"<html><p>Developer: Onkar Wagh<br>&nbsp; Address: Pune, India<br>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  |-_-| </p></html>");

		footerLabel.setFont(footerLabel.getFont().deriveFont(17f));

		footerLabel.setVerticalTextPosition(JLabel.BOTTOM);

		footerLabel.setVerticalAlignment(JLabel.BOTTOM);

		footer.add(footerLabel);

		this.setVisible(true);

		// action listener

		home.addActionListener((ActionEvent e) -> {
		});

		admin.addActionListener((ActionEvent e) -> {
			this.setVisible(false);
			Admin adObj = new Admin();
//          adObj.setVisible(true);

		});

		rules.addActionListener((ActionEvent e) -> {
			this.setVisible(false);
			Rules Obj = new Rules();

		});
//
		books.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainScreen.this.setVisible(false);

				try {
					Book obj = new Book();
					obj.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
