/**
 * 
 */
package mainapp;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Dinesh
 *
 */
public class SignUp {
	File file = new File("filepath.properties");
	private JFrame frame;
	private JTextField textField;
	private JLabel lblGender;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JButton Signup;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JTextField textField_1;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 794, 695);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(145, 116, 70, 15);
		frame.getContentPane().add(lblUserid);
		Signup = new JButton("Sign Up");
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(347, 113, 114, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(145, 177, 70, 15);
		frame.getContentPane().add(lblGender);
		
		textField_2 = new JTextField();
		textField_2.setBounds(347, 271, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		lblNewLabel = new JLabel("Age");
		lblNewLabel.setBounds(145, 273, 81, 15);
		frame.getContentPane().add(lblNewLabel);
		Signup.setBounds(145, 503, 114, 25);
		frame.getContentPane().add(Signup);
		
		rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(347, 173, 149, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Female");
		rdbtnNewRadioButton_1.setBounds(347, 200, 149, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		

		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
	
	
		try {
			
		
			String usersfile=properties.getProperty("userDetails");
			
			BufferedReader br=new BufferedReader(new FileReader(usersfile));
			String line="";
			String lines[] = null;
			while((line=br.readLine())!=null)
			{
				if(line==null||line.equals(""))
				{
				continue;
				}
				
				lines=line.split(",");
			}
			
		String ID=lines[0];
		int id=Integer.parseInt(ID);
		id++;
		textField.setText(new Integer(id).toString());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\"other\" or not specified", "academic/educator", "artist", "clerical/admin", "college/grad student", "customer service", "doctor/health care", "executive/managerial", "farmer", "homemaker", "K-12 student", "lawyer", "programmer", "sales/marketing\"", "self-employed", "technician/engineer", "tradesman/craftsman", "unemployed", "writer"}));
		comboBox.setBounds(348, 335, 246, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(145, 340, 93, 15);
		frame.getContentPane().add(lblOccupation);
		
		JLabel lblNewLabel_1 = new JLabel("Zip Code");
		lblNewLabel_1.setBounds(145, 405, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(347, 403, 149, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUserRegistration = new JLabel("User Registration");
		lblUserRegistration.setForeground(Color.BLUE);
		lblUserRegistration.setFont(new Font("URW Chancery L", Font.BOLD, 26));
		lblUserRegistration.setBounds(262, 22, 223, 34);
		frame.getContentPane().add(lblUserRegistration);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReset.setBounds(347, 503, 117, 25);
		frame.getContentPane().add(btnReset);
			
			
		} 
		catch (IOException e2) {
			JOptionPane.showMessageDialog(frame, "Make sure that input folder contains users.csv file", "File Not Found",JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
			
		}
		
		final String usersfile=properties.getProperty("userDetails");
		Signup.addActionListener(new ActionListener() {
			
			
			
			
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||(!rdbtnNewRadioButton.isSelected()&&!rdbtnNewRadioButton_1.isSelected()))
			{
				JOptionPane.showMessageDialog(frame, "Please fill all details");
			}
			else
			{
		String Uid=textField.getText();
		
		String gender;
		
			
		String Age=textField_2.getText();
		try {
			if(rdbtnNewRadioButton.isSelected())
			{
				gender="M";
			}
			else
			{
				gender="F";
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(usersfile,true));
			bw.write(Uid+","+gender+",");
			bw.write("\n");
			bw.close();
			JOptionPane.showMessageDialog(frame, "Successfully signed up!!!!!!!!!");
			frame.hide();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Make sure that input folder contains users.csv file", "File Not Found",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		}
			}
		});
	
	}
}
