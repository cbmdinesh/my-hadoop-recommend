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
import javax.swing.JProgressBar;

/**
 * @author Dinesh
 *
 */

public class HomePage {

	private JFrame frame;

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
		btnNewButton.setBounds(29, 106, 271, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblAdminHome = new JLabel("Admin Home");
		lblAdminHome.setBounds(153, 12, 100, 25);
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
		btnNewButton_1.setBounds(29, 55, 271, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Data Clustering");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		btnNewButton_2.setBounds(29, 163, 271, 25);
		frame.getContentPane().add(btnNewButton_2);
	}
}
