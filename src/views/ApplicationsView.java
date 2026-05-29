/**
 * Lead Author(s):
 * @author Monique Murphy; 0005396987
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2026-05-28
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.ApplicationModel;

/**
 * Purpose: The responsibility of ApplicationsView is
 * To display all saved applications in a table
 * and provide buttons for add, edit, delete, filter, and search
 *
 */
public class ApplicationsView extends JFrame
{
	private JTable applicationsTable;
	private DefaultTableModel tableModel;

	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton searchButton;

	private JComboBox<String> filterBox;
	private JTextField searchField;

	public ApplicationsView()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Applications");
		this.setLayout(new BorderLayout());

		Color backgroundColor = new Color(245, 244, 250);
		getContentPane().setBackground(backgroundColor);

		// Top Panel

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(backgroundColor);
		topPanel.setBorder(
				BorderFactory.createEmptyBorder(24, 30, 12, 30));

		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(backgroundColor);
		toolbarPanel.setAlignmentX(LEFT_ALIGNMENT);
		toolbarPanel.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, 50));

		JPanel leftToolbar = new JPanel(
				new FlowLayout(FlowLayout.LEFT, 12, 0));

		leftToolbar.setBackground(backgroundColor);

		addButton = makeButton(
				"Add",
				new Color(210, 230, 255));

		editButton = makeButton(
				"Edit",
				new Color(210, 230, 255));

		deleteButton = makeButton(
				"Delete",
				new Color(255, 220, 220));

		searchButton = makeButton(
				"Search",
				new Color(220, 230, 255));

		leftToolbar.add(addButton);
		leftToolbar.add(editButton);
		leftToolbar.add(deleteButton);

		leftToolbar.add(Box.createHorizontalStrut(8));

		filterBox = new JComboBox<>(new String[]
		{
				"All",
				"Applied",
				"Interview",
				"Offer",
				"Rejected"
		});

		filterBox.setFont(
				new Font("SansSerif", Font.PLAIN, 15));

		filterBox.setPreferredSize(
				new Dimension(160, 38));

		filterBox.setBackground(Color.WHITE);

		filterBox.setFocusable(false);

		leftToolbar.add(filterBox);

		leftToolbar.add(Box.createHorizontalStrut(20));

		searchField = new JTextField();

		searchField.setFont(
				new Font("SansSerif", Font.PLAIN, 15));

		searchField.setText("Search applications...");

		searchField.setPreferredSize(
				new Dimension(300, 38));

		searchField.setForeground(Color.GRAY);

		searchField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if (searchField.getText().equals("Search applications..."))
				{
					searchField.setText("");
					searchField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if (searchField.getText().trim().isEmpty())
				{
					searchField.setText("Search applications...");
					searchField.setForeground(Color.GRAY);
				}
			}
		});

		searchField.setBackground(Color.WHITE);

		searchField.setCaretColor(Color.BLACK);

		searchField.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 230)),
						BorderFactory.createEmptyBorder(
								6, 10, 6, 10)));

		leftToolbar.add(searchField);

		leftToolbar.add(Box.createHorizontalStrut(6));

		leftToolbar.add(searchButton);

		toolbarPanel.add(leftToolbar, BorderLayout.WEST);

		topPanel.add(toolbarPanel);

		add(topPanel, BorderLayout.NORTH);

		// Table Panel

		String[] columns =
		{
				"Company Name",
				"Job Title",
				"Location",
				"Pay / Salary",
				"Date Applied",
				"Status",
				"Type"
		};

		tableModel = new DefaultTableModel(columns, 0)
		{
			@Override
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}
		};

		applicationsTable = new JTable(tableModel);

		applicationsTable.getTableHeader()
				.setReorderingAllowed(false);

		// Set all columns to not resizable
		for (int i = 0;
				i < applicationsTable.getColumnModel().getColumnCount();
				i++)
		{
			applicationsTable.getColumnModel()
					.getColumn(i)
					.setResizable(false);
		}

		applicationsTable.setAutoResizeMode(
				JTable.AUTO_RESIZE_LAST_COLUMN);

		applicationsTable.getColumnModel()
				.getColumn(0).setPreferredWidth(220);

		applicationsTable.getColumnModel()
				.getColumn(1).setPreferredWidth(210);

		applicationsTable.getColumnModel()
				.getColumn(2).setPreferredWidth(170);

		applicationsTable.getColumnModel()
				.getColumn(3).setPreferredWidth(150);

		applicationsTable.getColumnModel()
				.getColumn(4).setPreferredWidth(150);

		applicationsTable.getColumnModel()
				.getColumn(5).setPreferredWidth(140);

		applicationsTable.getColumnModel()
				.getColumn(6).setPreferredWidth(200);

		applicationsTable.setDefaultRenderer(
				Object.class,
				new DefaultTableCellRenderer()
		{
			@Override
			public java.awt.Component
			getTableCellRendererComponent(
					JTable table,
					Object value,
					boolean isSelected,
					boolean hasFocus,
					int row,
					int column)
			{
				java.awt.Component cell =
						super.getTableCellRendererComponent(
								table,
								value,
								isSelected,
								hasFocus,
								row,
								column);

				if (!isSelected)
				{
					if (row % 2 == 0)
					{
						cell.setBackground(Color.WHITE);
					}
					else
					{
						cell.setBackground(
								new Color(248, 248, 252));
					}
				}

				return cell;
			}
		});

		applicationsTable.setFont(
				new Font("SansSerif", Font.PLAIN, 15));

		applicationsTable.setRowHeight(42);

		applicationsTable.setShowVerticalLines(false);

		applicationsTable.setShowHorizontalLines(false);

		applicationsTable.setGridColor(
				new Color(250, 250, 250));

		applicationsTable.setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		applicationsTable.setSelectionBackground(
				new Color(210, 225, 255));

		applicationsTable.setBackground(Color.WHITE);

		applicationsTable.setIntercellSpacing(
				new Dimension(0, 0));

		applicationsTable.getTableHeader()
				.setFont(
						new Font("SansSerif", Font.BOLD, 15));

		applicationsTable.getTableHeader()
				.setBackground(
						new Color(248, 248, 250));

		applicationsTable.getTableHeader()
				.setForeground(
						new Color(55, 55, 65));

		DefaultTableCellRenderer headerRenderer =
				(DefaultTableCellRenderer)
				applicationsTable.getTableHeader()
						.getDefaultRenderer();

		headerRenderer.setHorizontalAlignment(
				SwingConstants.CENTER);

		headerRenderer.setBorder(
				BorderFactory.createEmptyBorder(
						0, 0, 0, 0));

		applicationsTable.getTableHeader()
				.setDefaultRenderer(headerRenderer);

		applicationsTable.getTableHeader()
				.setPreferredSize(new Dimension(0, 42));

		DefaultTableCellRenderer centerRenderer =
				new DefaultTableCellRenderer();

		centerRenderer.setHorizontalAlignment(
				SwingConstants.CENTER);

		for (int col : new int[]
		{
				3, 4, 5
		})
		{
			applicationsTable.getColumnModel()
					.getColumn(col)
					.setCellRenderer(centerRenderer);
		}

		JScrollPane scrollPane =
				new JScrollPane(applicationsTable);

		scrollPane.setBorder(
				BorderFactory.createEmptyBorder());

		scrollPane.getViewport()
				.setBackground(
						new Color(252, 252, 253));

		JPanel tableWrapper =
				new JPanel(new BorderLayout());

		tableWrapper.setBorder(
				BorderFactory.createEmptyBorder(
						6, 30, 30, 30));

		tableWrapper.setBackground(backgroundColor);

		tableWrapper.add(scrollPane, BorderLayout.CENTER);

		add(tableWrapper, BorderLayout.CENTER);

		// Bottom Panel

		JPanel bottomPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 16, 8));

		bottomPanel.setBackground(backgroundColor);

		add(bottomPanel, BorderLayout.SOUTH);

		this.setSize(1200, 760); 
		this.setLocationRelativeTo(null); 
		this.setVisible(true);
	}

	// Getters

	public JTable getApplicationsTable()
	{
		return applicationsTable;
	}

	public DefaultTableModel getTableModel()
	{
		return tableModel;
	}

	public JButton getAddButton()
	{
		return addButton;
	}

	public JButton getEditButton()
	{
		return editButton;
	}

	public JButton getDeleteButton()
	{
		return deleteButton;
	}

	public JButton getSearchButton()
	{
		return searchButton;
	}

	public JComboBox<String> getFilterBox()
	{
		return filterBox;
	}

	public JTextField getSearchField()
	{
		return searchField;
	}

	public int getSelectedApplicationIndex()
	{
		return applicationsTable.getSelectedRow();
	}

	public String getSearchText()
	{
		String text = searchField.getText();
		return text.equals("Search applications...") ? "" : text;
	}

	/**
	 * Method: Make Button
	 * Purpose: Create JButton with the given label and background color
	 * 
	 * @param label the text to display on the button
	 * @param bg the background color of the button
	 * @return a JButton
	 */
	private JButton makeButton(String label, Color bg)
	{
		JButton btn = new JButton(label);

		btn.setFont(
				new Font("SansSerif", Font.BOLD, 15));

		btn.setBackground(bg);

		btn.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235),
								1,
								true),
						BorderFactory.createEmptyBorder(
								8, 18, 8, 18)));

		btn.setFocusPainted(false);

		btn.setContentAreaFilled(false);

		btn.setOpaque(true);

		btn.setPreferredSize(
				new Dimension(100, 38));

		return btn;
	}
	
	/**
	 * Method: Display Applications
	 * Purpose: Display the collection of applications in the table
	 * 
	 * @param applications an ArrayList of ApplicationModel objects 
	 * @return void 
	 */
	public void displayApplications(ArrayList<ApplicationModel> applications)
	{
		tableModel.setRowCount(0);
		
		for (int i = 0; i < applications.size(); i++)
		{
			ApplicationModel app = applications.get(i);
			
			tableModel.addRow(new Object[]
					{
							app.getCompanyName(),
							app.getJobTitle(),
							app.getJobLocation(),
							String.format("$%.2f", app.getPay()),
							app.getDateApplied(),
							app.getStatus(),
							app.getApplicationType()
					});
		}
	}
}