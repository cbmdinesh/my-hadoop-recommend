/**
 * 
 */
package mainapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dataPreparation.DataPrepDriver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Dinesh
 *
 */

public class HomePage {

	private JFrame frame;
	File file=new File("filepath.properties");
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	public HomePage() {
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
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 496);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Click here for Data Preprocessing");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String args[]=null;
				try {
					new DataPrepDriver().main(args);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(280, 163, 271, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblAdminHome = new JLabel("Admin Home");
		lblAdminHome.setFont(new Font("URW Chancery L", Font.BOLD, 22));
		lblAdminHome.setForeground(Color.RED);
		lblAdminHome.setBounds(327, 38, 198, 25);
		frame.getContentPane().add(lblAdminHome);
		
		JButton btnNewButton_1 = new JButton("Click here for title preparation");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String args[]=null;
				try {
					new DataPrepDriver().main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(280, 332, 275, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Data Clustering");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		btnNewButton_2.setBounds(276, 403, 271, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View PreProcessed Data");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Displaydetails.main();
			}
		});
		btnNewButton_3.setBounds(280, 243, 275, 25);
		frame.getContentPane().add(btnNewButton_3);
	}
}
