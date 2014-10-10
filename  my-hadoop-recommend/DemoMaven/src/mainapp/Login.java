/**
 * 
 */
package mainapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;

/**
 * @author Dinesh
 *
 */

public class Login {

	private JFrame frame;
	private JTextField textField;
	private final JTextField textField_1 = new JTextField();
	File file = new File("filepath.properties");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(209, 77, 104, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField_1.setBounds(209, 115, 104, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String uid=textField.getText();
					String pass=textField_1.getText();
					FileInputStream inputStream = new FileInputStream(file);
					Properties properties=new Properties();
						properties.load(inputStream);
						String fileName=properties.getProperty("userDetails");
					BufferedReader br=new BufferedReader(new FileReader("input/users.csv"));
					String line="";
					boolean userflag=false;
				while((line=br.readLine())!=null)
				{
				String[] details=line.split(",");
				if((details[0].equals(uid))&&(details[1].equals(pass)))
				{
					userflag=true;
					break;
				}
				
				}
				if(userflag==true)
				{
					System.out.println("Valid user");
					JOptionPane.showMessageDialog(frame, "WelCome", "Login Succeed",JOptionPane.INFORMATION_MESSAGE);
					String[] args={uid};
					new RateMovies().main(args);
					frame.hide();
				}
				
				else
				{
					System.out.println("InValid user");
					JOptionPane.showMessageDialog(frame, "UserName or password must be wrong!!!!!!", "Authentication failed",JOptionPane.ERROR_MESSAGE);
				}
				
				} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "Make sure that input folder contains users.csv file", "File Not Found",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(126, 185, 91, 25);
		frame.getContentPane().add(btnNewButton);
		
		Label label = new Label("User Id");
		label.setBounds(95, 81, 68, 21);
		frame.getContentPane().add(label);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(95, 120, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String args[] = null;
				new SignUp().main(args);
				
			}
		});
		btnSignUp.setBounds(307, 185, 91, 25);
		frame.getContentPane().add(btnSignUp);
	}
}
