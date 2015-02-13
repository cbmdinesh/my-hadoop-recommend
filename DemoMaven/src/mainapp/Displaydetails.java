package mainapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class Displaydetails extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Displaydetails frame = new Displaydetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Displaydetails() {
		File file=new File("filepath.properties");
		final Properties properties=new Properties();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		
		properties.load(inputStream);
		String fileName=properties.getProperty("preprocessoutput");
		
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 801, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 62, 630, 253);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblPreprocessedData = new JLabel("Preprocessed Data");
		lblPreprocessedData.setFont(new Font("URW Chancery L", Font.BOLD, 26));
		lblPreprocessedData.setForeground(Color.RED);
		lblPreprocessedData.setBounds(229, 12, 269, 38);
		contentPane.add(lblPreprocessedData);
		String line;
		while((line=br.readLine())!=null)
		{
			textArea.append(line+"\n");
		}
		} catch (IOException e) {
		
		JOptionPane.showMessageDialog(rootPane, "Please do data preprocessing!!!!");
		}
	}

}
