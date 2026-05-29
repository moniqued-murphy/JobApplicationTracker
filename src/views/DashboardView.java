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
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import models.ApplicationModel;

/**
 * Purpose: The responsibility of DashboardView is...
 * to display an overview of the user's job search progress, 
 * including summary statistics and a table of recent applications.
 *
 */
public class DashboardView extends JFrame
{
	private JLabel totalApplicationsLabel;
	private JLabel interviewsLabel;
	private JLabel offersLabel;
	private JLabel rejectionsLabel;

	private JPanel recentListPanel;
	private PieChartPanel pieChart;

	private JButton applicationsButton;
	private JButton addApplicationButton;
	private JButton statisticsButton;
	private JButton dashboardButton;

	// Constructor
	public DashboardView()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Dashboard");
		this.setLayout(new BorderLayout());

		// Sidebar

		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(new Color(30, 40, 60));
		sidebar.setPreferredSize(new Dimension(160, 0));
		sidebar.setBorder(
				BorderFactory.createEmptyBorder(24, 0, 24, 0));

		dashboardButton = makeSidebarButton(
				"Dashboard",
				true);

		applicationsButton = makeSidebarButton(
				"Applications",
				false);

		addApplicationButton = makeSidebarButton(
				"+ Add Application",
				false);

		statisticsButton = makeSidebarButton(
				"Statistics",
				false);

		sidebar.add(dashboardButton);
		sidebar.add(Box.createVerticalStrut(8));
		sidebar.add(applicationsButton);
		sidebar.add(Box.createVerticalStrut(8));
		sidebar.add(addApplicationButton);
		sidebar.add(Box.createVerticalStrut(8));
		sidebar.add(statisticsButton);
		sidebar.add(Box.createVerticalGlue());

		add(sidebar, BorderLayout.WEST);

		// Main Content Area

		Color backgroundColor = new Color(245, 244, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);
		mainPanel.setBorder(
				BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// Title Section

		JLabel titleLabel = new JLabel("Dashboard");
		titleLabel.setFont(
				new Font("SansSerif", Font.BOLD, 26));
		titleLabel.setForeground(
				new Color(30, 40, 60));
		titleLabel.setAlignmentX(LEFT_ALIGNMENT);

		JLabel subtitleLabel = new JLabel(
				"Overview of your job search progress");
		subtitleLabel.setFont(
				new Font("SansSerif", Font.PLAIN, 15));
		subtitleLabel.setForeground(
				new Color(120, 120, 140));
		subtitleLabel.setAlignmentX(LEFT_ALIGNMENT);

		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(4));
		mainPanel.add(subtitleLabel);
		mainPanel.add(Box.createVerticalStrut(24));

		// Stats Cards Row

		JPanel cardsPanel = new JPanel();
		cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
		cardsPanel.setBackground(backgroundColor);
		cardsPanel.setAlignmentX(LEFT_ALIGNMENT);
		cardsPanel.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, 110));

		totalApplicationsLabel = new JLabel("0");
		interviewsLabel = new JLabel("0");
		offersLabel = new JLabel("0");
		rejectionsLabel = new JLabel("0");

		JPanel totalCard = makeStatCard(
				"Total Applications",
				totalApplicationsLabel,
				new Color(210, 225, 255),
				new Color(100, 149, 237));

		JPanel interviewsCard = makeStatCard(
				"Interviews",
				interviewsLabel,
				new Color(210, 240, 220),
				new Color(80, 180, 120));

		JPanel offersCard = makeStatCard(
				"Offers",
				offersLabel,
				new Color(255, 235, 210),
				new Color(230, 160, 80));

		JPanel rejectionsCard = makeStatCard(
				"Rejections",
				rejectionsLabel,
				new Color(255, 215, 215),
				new Color(220, 100, 100));

		cardsPanel.add(totalCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(interviewsCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(offersCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(rejectionsCard);

		mainPanel.add(cardsPanel);
		mainPanel.add(Box.createVerticalStrut(28));

		// Bottom Section: left pieChartPanel and right recent applications

		JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
		bottomPanel.setBackground(backgroundColor);
		bottomPanel.setAlignmentX(LEFT_ALIGNMENT);

		// Left side: pie chart panel
		pieChart = new PieChartPanel();
		pieChart.setBackground(Color.WHITE);
		pieChart.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235)),
						BorderFactory.createEmptyBorder(
								16, 16, 16, 16)));
		
		

		// Right side: Recent Applications card
		JPanel recentCard = new JPanel(new BorderLayout());
		recentCard.setBackground(Color.WHITE);
		recentCard.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235)),
						BorderFactory.createEmptyBorder(
								16, 16, 16, 16)));

		JLabel recentLabel = new JLabel("Recent Applications");
		recentLabel.setFont(
				new Font("SansSerif", Font.BOLD, 16));
		recentLabel.setForeground(
				new Color(30, 40, 60));
		recentLabel.setBorder(
				BorderFactory.createEmptyBorder(0, 0, 12, 0));

		// List panel that holds individual application rows
		recentListPanel = new JPanel();
		recentListPanel.setLayout(
				new BoxLayout(recentListPanel, BoxLayout.Y_AXIS));
		recentListPanel.setBackground(Color.WHITE);

		JScrollPane recentScroll = new JScrollPane(recentListPanel);
		recentScroll.setBorder(BorderFactory.createEmptyBorder());
		recentScroll.getViewport().setBackground(Color.WHITE);

		recentCard.add(recentLabel, BorderLayout.NORTH);
		recentCard.add(recentScroll, BorderLayout.CENTER);

		bottomPanel.add(pieChart);
		bottomPanel.add(recentCard);

		mainPanel.add(bottomPanel);

		add(mainPanel, BorderLayout.CENTER);

		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Getters

	public JLabel getTotalApplicationsLabel()
	{
		return totalApplicationsLabel;
	}

	public JLabel getInterviewsLabel()
	{
		return interviewsLabel;
	}

	public JLabel getOffersLabel()
	{
		return offersLabel;
	}

	public JLabel getRejectionsLabel()
	{
		return rejectionsLabel;
	}

	public JButton getApplicationsButton()
	{
		return applicationsButton;
	}

	public JButton getAddApplicationButton()
	{
		return addApplicationButton;
	}

	public JButton getStatisticsButton()
	{
		return statisticsButton;
	}

	public JButton getDashboardButton()
	{
		return dashboardButton;
	}

	// Update Methods

	/**
	 * Method: Update Total Applications
	 * Purpose: Update the total applications state card with a new count
	 *
	 * @param count the new total application count
	 * @return void
	 */
	public void updateTotalApplications(int count)
	{
		totalApplicationsLabel.setText(String.valueOf(count));
	}

	/**
	 * Method: Update Interviews
	 * Purpose: Update the interviews state card with a new count
	 *
	 * @param count the new interview count
	 * @return void
	 */
	public void updateInterviews(int count)
	{
		interviewsLabel.setText(String.valueOf(count));
	}

	/**
	 * Method: Update Offers
	 * Purpose: Update the offers state card with a new count
	 *
	 * @param count the new offer count
	 * @return void
	 */
	public void updateOffers(int count)
	{
		offersLabel.setText(String.valueOf(count));
	}

	/**
	 * Method: Update Rejections
	 * Purpose: Update the rejections state card with a new count
	 *
	 * @param count the new rejection count
	 * @return void
	 */
	public void updateRejections(int count)
	{
		rejectionsLabel.setText(String.valueOf(count));
	}

	/**
	 * Method: Display Recent Applications
	 * Purpose: Display the last 5 applications in the recent applications table
	 *
	 * @param apps an ArrayList of ApplicationModel objects
	 * @return void
	 */
	public void displayRecentApplications(ArrayList<ApplicationModel> apps)
	{
		// Clear existing rows
		recentListPanel.removeAll();

		int startIndex = Math.max(0, apps.size() - 5);

		for (int i = startIndex; i < apps.size(); i++)
		{
			ApplicationModel app = apps.get(i);

			// Each row has the app name on the left, status and date on the right
			JPanel row = new JPanel(new BorderLayout());
			row.setBackground(Color.WHITE);
			row.setBorder(
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(
									0, 0, 1, 0,
									new Color(240, 240, 245)),
							BorderFactory.createEmptyBorder(
									10, 4, 10, 4)));
			row.setMaximumSize(
					new Dimension(Integer.MAX_VALUE, 62));

			// Left: "Company: Job Title"
			JLabel nameLabel = new JLabel(
					app.getCompanyName() + " – " + app.getJobTitle());
			nameLabel.setFont(
					new Font("SansSerif", Font.PLAIN, 14));
			nameLabel.setForeground(new Color(30, 40, 60));

			// Right: status (colored) above date (gray)
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(
					new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			rightPanel.setBackground(Color.WHITE);

			JLabel statusLabel = new JLabel(app.getStatus());
			statusLabel.setFont(
					new Font("SansSerif", Font.BOLD, 14));
			statusLabel.setAlignmentX(RIGHT_ALIGNMENT);

			// Color the status text based on value
			String status = app.getStatus();
			if (status.equals("Applied"))
			{
				statusLabel.setForeground(new Color(100, 149, 237));
			}
			else if (status.equals("Interview"))
			{
				statusLabel.setForeground(new Color(80, 180, 120));
			}
			else if (status.equals("Offer"))
			{
				statusLabel.setForeground(new Color(230, 160, 80));
			}
			else if (status.equals("Rejected"))
			{
				statusLabel.setForeground(new Color(220, 100, 100));
			}

			JLabel dateLabel = new JLabel(app.getDateApplied());
			dateLabel.setFont(
					new Font("SansSerif", Font.PLAIN, 12));
			dateLabel.setForeground(new Color(150, 150, 170));
			dateLabel.setAlignmentX(RIGHT_ALIGNMENT);

			rightPanel.add(statusLabel);
			rightPanel.add(dateLabel);

			row.add(nameLabel, BorderLayout.WEST);
			row.add(rightPanel, BorderLayout.EAST);

			recentListPanel.add(row);
		}

		// Refresh the panel to show new rows
		recentListPanel.revalidate();
		recentListPanel.repaint();
	}
	
	/**
	 * Method: Update Pie Chart
	 * Purpose: Send new data to the pie chart panel and trigger a redraw
	 *
	 * @param applied the number of applied applications
	 * @param interviews the number of interview applications
	 * @param offers the number of offer applications
	 * @param rejected the number of rejected applications
	 * @return void
	 */
	public void updatePieChart(int applied, int interviews, int offers, int rejected)
	{
		pieChart.updateData(applied, interviews, offers, rejected);
	}

	// Helper Methods  

	/**
	 * Method: Make Stat Card
	 * Purpose: Build a stat card panel with a number label and title
	 *
	 * @param title the card label shown below the number
	 * @param numberLabel the JLabel that holds the stat count
	 * @param bgColor the background accent color for the card
	 * @param accentColor the color used for the number text
	 * @return a JPanel styled as a state card
	 */
	private JPanel makeStatCard(
			String title,
			JLabel numberLabel,
			Color bgColor,
			Color accentColor)
	{
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBackground(Color.WHITE);
		card.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235),
								1,
								true),
						BorderFactory.createEmptyBorder(
								18, 20, 18, 20)));
		card.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, 110));

		JPanel accentBar = new JPanel();
		accentBar.setBackground(bgColor);
		accentBar.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, 6));
		accentBar.setAlignmentX(LEFT_ALIGNMENT);

		numberLabel.setFont(
				new Font("SansSerif", Font.BOLD, 32));
		numberLabel.setForeground(accentColor);
		numberLabel.setAlignmentX(LEFT_ALIGNMENT);

		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(
				new Font("SansSerif", Font.PLAIN, 14));
		titleLabel.setForeground(
				new Color(100, 100, 120));
		titleLabel.setAlignmentX(LEFT_ALIGNMENT);

		card.add(accentBar);
		card.add(Box.createVerticalStrut(10));
		card.add(numberLabel);
		card.add(Box.createVerticalStrut(4));
		card.add(titleLabel);

		return card;
	}

	/**
	 * Method: Make Sidebar Button
	 * Purpose: Build a sidebar navigation button
	 *
	 * @param label the button text
	 * @param isActive whether this button is the currently selected view
	 * @return a JButton styled for the sidebar
	 */
	private JButton makeSidebarButton(String label, boolean isActive)
	{
		JButton btn = new JButton(label);

		btn.setFont(
				new Font("SansSerif", Font.BOLD, 14));

		if (isActive)
		{
			btn.setBackground(new Color(70, 100, 180));
			btn.setForeground(Color.WHITE);
		}
		else
		{
			btn.setBackground(new Color(30, 40, 60));
			btn.setForeground(new Color(180, 190, 210));
		}

		btn.setBorder(
				BorderFactory.createEmptyBorder(
						10, 14, 10, 14));

		btn.setFocusPainted(false);

		btn.setContentAreaFilled(false);

		btn.setOpaque(true);

		btn.setAlignmentX(CENTER_ALIGNMENT);

		btn.setMaximumSize(
				new Dimension(Integer.MAX_VALUE, 42));

		return btn;
	}
}
