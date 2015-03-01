package mainapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
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
import java.awt.Color;
import java.awt.Font;

public class AddMovieDetails extends JFrame {
	File file = new File("filepath.properties");
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieDetails frame = new AddMovieDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AddMovieDetails() throws IOException {
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		
		final String usersfile=properties.getProperty("newTitle");
		
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
	final int id=Integer.parseInt(ID)+1;
	
	
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 824, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMovieName = new JLabel("Movie Name");
		lblMovieName.setBounds(113, 161, 121, 15);
		contentPane.add(lblMovieName);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(114, 248, 70, 15);
		contentPane.add(lblYear);
		
		JLabel lblGenere = new JLabel("Genere:");
		lblGenere.setBounds(126, 349, 70, 15);
		contentPane.add(lblGenere);
		
		textField = new JTextField();
		textField.setBounds(297, 159, 235, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 246, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Action");
		chckbxNewCheckBox.setBounds(297, 320, 129, 23);
		contentPane.add(chckbxNewCheckBox);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Thriller");
		chckbxNewCheckBox_1.setBounds(430, 320, 129, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Comedy");
		chckbxNewCheckBox_2.setBounds(297, 367, 129, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Drama");
		chckbxNewCheckBox_3.setBounds(430, 367, 129, 23);
		contentPane.add(chckbxNewCheckBox_3);
		
		JLabel lblAddMovieDetails = new JLabel("Add Movie Details");
		lblAddMovieDetails.setFont(new Font("URW Chancery L", Font.BOLD, 26));
		lblAddMovieDetails.setForeground(Color.BLUE);
		lblAddMovieDetails.setBackground(Color.RED);
		lblAddMovieDetails.setBounds(260, 45, 244, 34);
		contentPane.add(lblAddMovieDetails);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")||textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(contentPane, "Please fill all details");
				}
				else
				{
					String MName=textField.getText();
					String Year=textField_1.getText();
					StringBuffer Genere = new StringBuffer();
					if(chckbxNewCheckBox.isSelected())
											Genere.append("Action|");
					
					if(chckbxNewCheckBox_1.isSelected())
									Genere.append("Thriller|");
						
					
					if(chckbxNewCheckBox_2.isSelected())
						Genere.append("Comedy|");
						
					if(chckbxNewCheckBox_2.isSelected())
						Genere.append("Drama|");
					
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter(usersfile,true));
				
				bw.write(id+","+MName+"("+Year+")"+","+Genere);
				bw.write("\n");
				bw.close();
				JOptionPane.showMessageDialog(contentPane, "Successfully added!!!!!!!!!");
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnSubmit.setBounds(136, 463, 117, 25);
		contentPane.add(btnSubmit);
		}
}

