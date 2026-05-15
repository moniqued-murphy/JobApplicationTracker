/**
 * Lead Author(s):
 * 
 * @author Monique Murphy; 0005396987
 * 
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Version: 2026-05-11
 */
package views;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.ApplicationModel;
import repositories.ApplicationRepository;
import services.FileService;

import java.awt.event.ActionListener;

/**
 * Purpose: The responsibility of AddApplicationView is ...
 *
 * AddApplicationView is-a ...
 * AddApplicationView is ...
 */
public class AddApplicationView extends JFrame
{
	private JTextField companyNameField;
	private JTextField jobTitleField;
	private JTextField jobLocationField;
	private JTextField payField;
	private JTextField dateField;

	private JComboBox<String> statusBox;
	private JComboBox<String> jobTypeBox;

	private JTextArea notesArea;

	private JButton saveButton;
	private JButton cancelButton;
	private JPanel mainPanel;

	public AddApplicationView()
	{

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add Application");
		this.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2, 30, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		mainPanel.setBorder(
				BorderFactory.createEmptyBorder(30, 30, 30, 30));

		Font labelFont = new Font("SansSerif", Font.BOLD, 18);

		companyNameField = new JTextField();
		styleField(companyNameField);

		jobTitleField = new JTextField();
		styleField(jobTitleField);

		jobLocationField = new JTextField();
		styleField(jobLocationField);

		payField = new JTextField();
		styleField(payField);

		dateField = new JTextField();
		styleField(dateField);

		statusBox = new JComboBox<String>();
		statusBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		statusBox.setPreferredSize(new Dimension(400, 45));
		statusBox.setMaximumSize(new Dimension(500, 45));
		statusBox.setAlignmentX(LEFT_ALIGNMENT);

		statusBox.addItem("Applied");
		statusBox.addItem("Interview");
		statusBox.addItem("Offer");
		statusBox.addItem("Rejected");

		jobTypeBox = new JComboBox<String>();
		jobTypeBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jobTypeBox.setPreferredSize(new Dimension(400, 45));
		jobTypeBox.setMaximumSize(new Dimension(500, 45));
		jobTypeBox.setAlignmentX(LEFT_ALIGNMENT);

		jobTypeBox.addItem("Job: Full-Time");
		jobTypeBox.addItem("Job: Part-Time");
		jobTypeBox.addItem("Internship: Full-Time");
		jobTypeBox.addItem("Internship: Part-Time");

		notesArea = new JTextArea(8, 20);
		notesArea.setFont(new Font("SansSerif", Font.PLAIN, 16));

		notesArea.setBorder(
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		);

		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);

		saveButton = new JButton("Save");
		saveButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		saveButton.setBackground(new Color(210, 230, 255)); // 210 230 255 , 220 210 245
		saveButton.setBorderPainted(false);
		saveButton.setFocusPainted(false);
		saveButton.setOpaque(true);
		saveButton.setContentAreaFilled(true);
		saveButton.setPreferredSize(new Dimension(140, 45));

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		cancelButton.setBackground(new Color(255, 220, 220));
		cancelButton.setBorderPainted(false);
		cancelButton.setFocusPainted(false);
		cancelButton.setOpaque(true);
		cancelButton.setContentAreaFilled(true);
		cancelButton.setPreferredSize(new Dimension(140, 45));
		
		// Left Panel
		JLabel companyLabel = new JLabel("Company Name");
		companyLabel.setFont(labelFont);
		companyLabel.setAlignmentX(LEFT_ALIGNMENT);

		leftPanel.add(companyLabel);
		leftPanel.add(Box.createVerticalStrut(8));
		leftPanel.add(companyNameField);
		leftPanel.add(Box.createVerticalStrut(24));

		JLabel jobLocationLabel = new JLabel("Location");
		jobLocationLabel.setFont(labelFont);
		jobLocationLabel.setAlignmentX(LEFT_ALIGNMENT);

		leftPanel.add(jobLocationLabel);
		leftPanel.add(Box.createVerticalStrut(8));
		leftPanel.add(jobLocationField);
		leftPanel.add(Box.createVerticalStrut(24));

		JLabel dateLabel = new JLabel("Date Applied");
		dateLabel.setFont(labelFont);
		dateLabel.setAlignmentX(LEFT_ALIGNMENT);

		leftPanel.add(dateLabel);
		leftPanel.add(Box.createVerticalStrut(8));
		leftPanel.add(dateField);
		leftPanel.add(Box.createVerticalStrut(24));

		JLabel jobTypeLabel = new JLabel("Job Type");
		jobTypeLabel.setFont(labelFont);
		jobTypeLabel.setAlignmentX(LEFT_ALIGNMENT);

		leftPanel.add(jobTypeLabel);
		leftPanel.add(Box.createVerticalStrut(8));
		leftPanel.add(jobTypeBox);

		mainPanel.add(leftPanel);

		// Right Panel
		JLabel jobTitleLabel = new JLabel("Job Title");
		jobTitleLabel.setFont(labelFont);
		jobTitleLabel.setAlignmentX(LEFT_ALIGNMENT);

		rightPanel.add(jobTitleLabel);
		rightPanel.add(Box.createVerticalStrut(8));
		rightPanel.add(jobTitleField);
		rightPanel.add(Box.createVerticalStrut(24));

		JLabel payLabel = new JLabel("Pay");
		payLabel.setFont(labelFont);
		payLabel.setAlignmentX(LEFT_ALIGNMENT);

		rightPanel.add(payLabel);
		rightPanel.add(Box.createVerticalStrut(8));
		rightPanel.add(payField);
		rightPanel.add(Box.createVerticalStrut(24));

		JLabel statusLabel = new JLabel("Status");
		statusLabel.setFont(labelFont);
		statusLabel.setAlignmentX(LEFT_ALIGNMENT);

		rightPanel.add(statusLabel);
		rightPanel.add(Box.createVerticalStrut(8));
		rightPanel.add(statusBox);

		mainPanel.add(rightPanel);

		Color backgroundColor = new Color(248, 248, 252);

		getContentPane().setBackground(backgroundColor);
		mainPanel.setBackground(backgroundColor);
		leftPanel.setBackground(backgroundColor);
		rightPanel.setBackground(backgroundColor);

		add(mainPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBackground(backgroundColor);

		JPanel notesPanel = new JPanel();
		notesPanel.setLayout(new BorderLayout());
		notesPanel.setBackground(backgroundColor);

		JLabel notesLabel = new JLabel("Notes");
		notesLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		notesLabel.setAlignmentX(LEFT_ALIGNMENT);

		notesPanel.add(notesLabel, BorderLayout.NORTH);
		notesPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);

		notesPanel.setBorder(
			BorderFactory.createEmptyBorder(0, 30, 20, 30)
		);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(backgroundColor);

		buttonPanel.setBorder(
			BorderFactory.createEmptyBorder(20, 0, 10, 0)
		);

		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		
		cancelButton.addActionListener(e ->
		{
			dispose();
		});

		bottomPanel.add(notesPanel, BorderLayout.CENTER);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(1200, 750);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	/**
	 * Method: styleField
	 * Purpose: Give text fields a consistent size
	 * 
	 * @param field the text field configure
	 */
	private void styleField(JTextField field)
	{
		field.setFont(new Font("SansSerif", Font.PLAIN, 16));

		field.setPreferredSize(new Dimension(400, 45));
		field.setMaximumSize(new Dimension(500, 45));

		field.setAlignmentX(LEFT_ALIGNMENT);

		field.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 220, 225)),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)
			)
		);
	}
	
	/**
	 * Method: Get Company Name
	 * 
	 * @return Company Name 
	 */
	public String getCompanyName()
	{
		return companyNameField.getText();
	}
	
	/**
	 * Method: Get Job Title
	 * 
	 * @return Job Title 
	 */
	public String getJobTitle()
	{
		return jobTitleField.getText();
	}
	
	/**
	 * Method: Get Location
	 * Purpose: Get the location from the location text field
	 * 
	 * @return Location 
	 */
	public String getJobLocation()
	{
		return jobLocationField.getText();
	}
	
	/**
	 * Method: Get Pay
	 * Purpose: Get the pay from the pay text field
	 * 
	 * @return pay 
	 */
	public String getPay()
	{
		return payField.getText();
	}
	
	/**
	 * Method: Get Date Applied
	 * Purpose: Get the date applied from the date applied text field
	 * 
	 * @return 
	 */
	public String getDateApplied()
	{
		return dateField.getText();
	}
	
	/**
	 * Method: Get Status 
	 * Purpose: Get the status from the status combo box
	 * 
	 * @return status 
	 */
	public String getStatus()
	{
		return (String) statusBox.getSelectedItem();
	}
	
	/**
	 * Method: Get Job Type
	 * Purpose: Get the job type from the job type combo box
	 * 
	 * @return job type 
	 */
	public String getJobType()
	{
		return (String) jobTypeBox.getSelectedItem();
	}
	
	/**
	 * Method: Get Notes
	 * Purpose: Get the notes from the notes text area
	 * 
	 * @return notes 
	 */
	public String getNotes()
	{
		return notesArea.getText();
	}
	
	/**
	 * Method: Get Save Button
	 * Purpose: Get the save button from the form
	 * 
	 * @return save button 
	 */
	public JButton getSaveButton()
	{
		return saveButton;
	}
	
	/**
	 * Method: Get Cancel Button
	 * Purpose: Get the cancel button from the form
	 * 
	 * @return cancel button 
	 */
	public JButton getCancelButton()
	{
		return cancelButton;
	}
	
	/**
	 * Method: Clear Form 
	 * Purpose: Clear all the fields in the form
	 * 
	 * @return void
	 */
	public void clearForm()
	{
		companyNameField.setText("");
		jobTitleField.setText("");
		jobLocationField.setText("");
		payField.setText("");
		dateField.setText("");
		statusBox.setSelectedIndex(0);
		jobTypeBox.setSelectedIndex(0);
		notesArea.setText("");
	}
}