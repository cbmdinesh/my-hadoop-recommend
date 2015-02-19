/**
 * 
 */
package mainapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import recommendation.CopyOfGenericUserBasedRecommender2;
import user.UserInput;

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
import java.util.HashSet;
import java.util.Properties;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Dinesh
 *
 */
public class RateMovies {

	private JFrame frame;
	File file=new File("filepath.properties");
	static String userId="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
		{
		if(args[0]!=null)
		{
			userId=args[0];
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Please run from login frame", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RateMovies window = new RateMovies();
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
	public RateMovies() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 958, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		HashSet<String> generes=new HashSet<String>();
		
		final Properties properties=new Properties();
		try {
			FileInputStream inputStream=new FileInputStream(file);
			properties.load(inputStream);
			String fileName=properties.getProperty("newTitle");
			
			BufferedReader br=new BufferedReader(new FileReader(fileName));
			String line="";
			while((line=br.readLine())!=null)
			{
			String genere[]=line.split(",");
			if(genere.length==3)
			{
				String items[]=genere[2].split("\\|");
				for(String s:items)
				{
					generes.add(s);
				}
				
			}
			}
			
		}
		catch (IOException e) {
		JOptionPane.showMessageDialog(frame, "Error in input file path");
			e.printStackTrace();
		}
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("select a Generes");
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(193, 102, 234, 24);
		frame.getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(193, 152, 71, 24);
		frame.getContentPane().add(comboBox_2);
		comboBox_2.addItem("1");
		comboBox_2.addItem("2");
		comboBox_2.addItem("3");
		comboBox_2.addItem("4");
		comboBox_2.addItem("5");
		
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					FileInputStream inputStream=new FileInputStream(file);
					properties.load(inputStream);
					String fileName=properties.getProperty("newTitle");
					
					BufferedReader br=new BufferedReader(new FileReader(fileName));
					String line="";
					String selectedItem=(String) comboBox.getSelectedItem();
					comboBox_1.removeAllItems();
					while((line=br.readLine())!=null)
					{
						if(line.contains(selectedItem))
						{
							
							comboBox_1.addItem(line.split(",")[1]);
						}
					}
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "Error in input file path");
						e.printStackTrace();
					}
				
			}
		});
		comboBox.setBounds(193, 48, 234, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblRateMovies = new JLabel("Rate Movies");
		lblRateMovies.setForeground(Color.BLUE);
		lblRateMovies.setFont(new Font("URW Gothic L", Font.ITALIC, 16));
		lblRateMovies.setBounds(149, 12, 129, 15);
		frame.getContentPane().add(lblRateMovies);
		
		for(String g:generes)
		{
			comboBox.addItem(g);
		}
		
		JButton btnNewButton = new JButton("Rate Movie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				FileInputStream inputStream;
				try {
					
					inputStream = new FileInputStream(file);
					properties.load(inputStream);
					String titleFile=properties.getProperty("newTitle");
					String ratingFile=properties.getProperty("ratings");
					BufferedReader br=new BufferedReader(new FileReader(titleFile));
					String line;
					while((line=br.readLine())!=null)
					{
						String items[]=line.split(",");
						if(items.length>1)
						{
						if(comboBox_1.getSelectedItem().toString().contains(items[1]))
						{
							if(!checkUserDetails(userId,items[0]))
							{
							BufferedWriter bw=new BufferedWriter(new FileWriter(ratingFile,true));
							bw.write(userId+","+items[0]+","+comboBox_2.getSelectedItem());
							bw.write("\n");
							bw.close();
							JOptionPane.showMessageDialog(frame, "Values Submitted!!!!!!!!!");	
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Values are already Submitted!!!!!!!!!");
							}
							
						}
						}
					}
					
					
					
					
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				
			}

			private boolean checkUserDetails(String userId, String string)
			{
				FileInputStream inputStream;
				try {
					inputStream = new FileInputStream(file);
					properties.load(inputStream);
					String ratingFile=properties.getProperty("ratings");
					BufferedReader br=new BufferedReader(new FileReader(ratingFile));
					String line;
					while((line=br.readLine())!=null)
					{
						String items[]=line.split(",");
						if(userId.equals(items[0])&&string.equals(items[1]))
						{
							return true;
						}
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
				return false;
			}
		});
		btnNewButton.setBounds(266, 229, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		
		JLabel lblSelectCategory = new JLabel("Select Generes");
		lblSelectCategory.setForeground(Color.RED);
		lblSelectCategory.setFont(new Font("URW Chancery L", Font.BOLD, 18));
		lblSelectCategory.setBounds(30, 48, 145, 34);
		frame.getContentPane().add(lblSelectCategory);
		
		JLabel lblSelectMovie = new JLabel("Select Movie");
		lblSelectMovie.setForeground(Color.RED);
		lblSelectMovie.setBackground(Color.RED);
		lblSelectMovie.setFont(new Font("URW Chancery L", Font.BOLD, 18));
		lblSelectMovie.setBounds(41, 107, 134, 15);
		frame.getContentPane().add(lblSelectMovie);
		
		JLabel lblRateMe = new JLabel("Rate Me!!!");
		lblRateMe.setForeground(Color.RED);
		lblRateMe.setBounds(41, 157, 117, 15);
		frame.getContentPane().add(lblRateMe);
		
		JLabel lblrateOutOf = new JLabel("(Rate Out of 5!)");
		lblrateOutOf.setForeground(Color.BLUE);
		lblrateOutOf.setFont(new Font("URW Chancery L", Font.BOLD, 18));
		lblrateOutOf.setBounds(279, 157, 148, 15);
		frame.getContentPane().add(lblrateOutOf);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(65, 229, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Click here to view Rated Movies");
		btnNewButton_2.setFont(new Font("URW Chancery L", Font.BOLD, 18));
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String args[]={};
					new ViewRatedMovies().main(args);
				} 
			
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(478, 131, 270, 24);
		frame.getContentPane().add(btnNewButton_2);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 316, 430, 203);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnClickHereTo = new JButton("Click here to view Recommented Items");
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				StringBuilder sb=CopyOfGenericUserBasedRecommender2.main(userId);
				textArea.setText(sb.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnClickHereTo.setBounds(478, 229, 305, 25);
		frame.getContentPane().add(btnClickHereTo);
	
	}
}
