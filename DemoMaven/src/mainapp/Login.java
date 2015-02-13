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
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

/**
 * @author Dinesh
 *
 */

public class Login {

	private JFrame frame;
	private JTextField textField;
	private final JTextField textField_1 = new  JPasswordField();
	File file = new File("filepath.properties");

	/**
	 * Launch the application.
	 */
	public static void main() {
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
		frame.setBounds(100, 100, 627, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(248, 97, 239, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField_1.setBounds(248, 159, 239, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String uid=textField.getText();
					String pass=textField_1.getText();
					if(uid.equals("admin")&&pass.equals("admin"))
					{
						AddMovieDetails.main();
					}
					else
					{
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
				
				} 
				}catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "Make sure that input folder contains users.csv file", "File Not Found",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(128, 234, 110, 25);
		frame.getContentPane().add(btnNewButton);
		
		Label label = new Label("User Id");
		label.setForeground(Color.RED);
		label.setFont(new Font("URW Chancery L", Font.BOLD, 16));
		label.setBounds(126, 85, 70, 46);
		frame.getContentPane().add(label);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("URW Chancery L", Font.BOLD, 16));
		lblPassword.setBounds(126, 163, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String args[] = null;
				new SignUp().main(args);
				
			}
		});
		btnSignUp.setBounds(253, 347, 110, 25);
		frame.getContentPane().add(btnSignUp);
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 18));
		lblNewLabel.setBounds(288, 29, 122, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnNewButton_1.setBounds(370, 234, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
	}
}
