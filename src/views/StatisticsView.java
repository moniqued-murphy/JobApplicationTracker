/**
 * Lead Author(s):
 * @author Monique Murphy; 0005396987
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2026-05-29
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Purpose: The responsibility of StatisticsView is to display
 * a statistics overview of the user's job application data,
 * including rate cards, a top pay panel, and chart panels.
 *
 * StatisticsView is-a JFrame
 */
public class StatisticsView extends JFrame
{
	private JButton topPayButton;
	private JButton topLocationsButton;
	private JButton topJobTypesButton;

	/**
	 * Constructor
	 * Purpose: Create an instance of StatisticsView and build the layout
	 *
	 * @return void
	 */
	public StatisticsView()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Statistics");
		this.setLayout(new BorderLayout());

		// Main Content Area

		Color backgroundColor = new Color(245, 244, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);
		mainPanel.setBorder(
				BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// Title Section

		JLabel titleLabel = new JLabel("Statistics");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
		titleLabel.setForeground(new Color(30, 40, 60));
		titleLabel.setAlignmentX(LEFT_ALIGNMENT);

		JLabel subtitleLabel = new JLabel("Overview of your job application statistics");
		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		subtitleLabel.setForeground(new Color(120, 120, 140));
		subtitleLabel.setAlignmentX(LEFT_ALIGNMENT);

		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(4));
		mainPanel.add(subtitleLabel);
		mainPanel.add(Box.createVerticalStrut(24));

		// Rate Cards Row

		JPanel cardsPanel = new JPanel();
		cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
		cardsPanel.setBackground(backgroundColor);
		cardsPanel.setAlignmentX(LEFT_ALIGNMENT);
		cardsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		// Create labels to hold the rate values for each card
		JLabel totalAppliedLabel = new JLabel("0");
		JLabel interviewRateLabel = new JLabel("0%");
		JLabel offerRateLabel = new JLabel("0%");
		JLabel rejectionRateLabel = new JLabel("0%");

		JPanel totalCard = makeStatCard(
				"Total Applied",
				totalAppliedLabel,
				new Color(210, 225, 255),
				new Color(100, 149, 237));

		JPanel interviewCard = makeStatCard(
				"Interview Rate",
				interviewRateLabel,
				new Color(210, 240, 220),
				new Color(80, 180, 120));

		JPanel offerCard = makeStatCard(
				"Offer Rate",
				offerRateLabel,
				new Color(255, 235, 210),
				new Color(230, 160, 80));

		JPanel rejectionCard = makeStatCard(
				"Rejection Rate",
				rejectionRateLabel,
				new Color(255, 215, 215),
				new Color(220, 100, 100));

		cardsPanel.add(totalCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(interviewCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(offerCard);
		cardsPanel.add(Box.createHorizontalStrut(16));
		cardsPanel.add(rejectionCard);

		mainPanel.add(cardsPanel);
		mainPanel.add(Box.createVerticalStrut(20));

		// Bottom Section: left chart panel and right chart panel with toggle buttons

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setBackground(backgroundColor);
		bottomPanel.setAlignmentX(LEFT_ALIGNMENT);

		// Left panel: Interviews by Month chart area
		JPanel byMonthPanel = makeChartPanel("Interviews by Month");

		// Right panel: three toggle buttons to switch between Top Pay, Top Locations, Top Job Types
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235)),
						BorderFactory.createEmptyBorder(16, 16, 16, 16)));

		// Toggle buttons at the top of the right panel
		JPanel toggleBar = new JPanel();
		toggleBar.setLayout(new BoxLayout(toggleBar, BoxLayout.X_AXIS));
		toggleBar.setBackground(Color.WHITE);
		toggleBar.setBorder(
				BorderFactory.createEmptyBorder(0, 0, 12, 0));

		topLocationsButton = makeToggleButton("Top Locations", true);
		topJobTypesButton = makeToggleButton("Top Job Types", false);
		topPayButton = makeToggleButton("Top Pay", false);

		toggleBar.add(topLocationsButton);
		toggleBar.add(Box.createHorizontalStrut(8));
		toggleBar.add(topJobTypesButton);
		toggleBar.add(Box.createHorizontalStrut(8));
		toggleBar.add(topPayButton);
		toggleBar.add(Box.createHorizontalGlue());

		// Center label indicating the chart area
		JLabel comingSoonLabel = new JLabel("Coming Soon", SwingConstants.CENTER);
		comingSoonLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comingSoonLabel.setForeground(new Color(180, 180, 200));

		rightPanel.add(toggleBar, BorderLayout.NORTH);
		rightPanel.add(comingSoonLabel, BorderLayout.CENTER);

		bottomPanel.add(byMonthPanel);
		bottomPanel.add(Box.createHorizontalStrut(20));
		bottomPanel.add(rightPanel);

		mainPanel.add(bottomPanel);

		add(mainPanel, BorderLayout.CENTER);

		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Helper Methods

	/**
	 * Method: Make Stat Card
	 * Purpose: Build a stat card panel with a number label and title
	 *
	 * @param title the card label shown below the number
	 * @param numberLabel the JLabel that holds the stat value
	 * @param bgColor the background accent color for the card
	 * @param accentColor the color used for the number text
	 * @return a JPanel styled as a stat card
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
								new Color(225, 225, 235), 1, true),
						BorderFactory.createEmptyBorder(18, 20, 18, 20)));
		card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		// Colored accent bar at the top of the card
		JPanel accentBar = new JPanel();
		accentBar.setBackground(bgColor);
		accentBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 6));
		accentBar.setAlignmentX(LEFT_ALIGNMENT);

		numberLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		numberLabel.setForeground(accentColor);
		numberLabel.setAlignmentX(LEFT_ALIGNMENT);

		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		titleLabel.setForeground(new Color(100, 100, 120));
		titleLabel.setAlignmentX(LEFT_ALIGNMENT);

		card.add(accentBar);
		card.add(Box.createVerticalStrut(10));
		card.add(numberLabel);
		card.add(Box.createVerticalStrut(4));
		card.add(titleLabel);

		return card;
	}

	/**
	 * Method: Make Chart Panel
	 * Purpose: Build a styled panel that serves as a placeholder for a chart
	 *
	 * @param title the title displayed at the top of the chart panel
	 * @return a JPanel styled as a chart container
	 */
	private JPanel makeChartPanel(String title)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235)),
						BorderFactory.createEmptyBorder(16, 16, 16, 16)));

		// Title label at the top of the chart panel
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		titleLabel.setForeground(new Color(30, 40, 60));
		titleLabel.setBorder(
				BorderFactory.createEmptyBorder(0, 0, 12, 0));

		// Center label indicating the chart area
		JLabel comingSoonLabel = new JLabel("Coming Soon", SwingConstants.CENTER);
		comingSoonLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comingSoonLabel.setForeground(new Color(180, 180, 200));

		panel.add(titleLabel, BorderLayout.NORTH);
		panel.add(comingSoonLabel, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * Method: Make Toggle Button
	 * Purpose: Build a toggle button for switching between chart views
	 *
	 * @param label the text displayed on the button
	 * @param isActive whether this button is currently selected
	 * @return a JButton styled as a toggle button
	 */
	private JButton makeToggleButton(String label, boolean isActive)
	{
		JButton btn = new JButton(label);
		btn.setFont(new Font("SansSerif", Font.BOLD, 13));
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setOpaque(true);

		// Highlight the active button and style the inactive button differently
		if (isActive)
		{
			btn.setBackground(new Color(100, 149, 237));
			btn.setForeground(Color.WHITE);
		}
		else
		{
			btn.setBackground(Color.WHITE);
			btn.setForeground(new Color(100, 100, 120));
		}

		btn.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(
								new Color(225, 225, 235), 1, true),
						BorderFactory.createEmptyBorder(6, 14, 6, 14)));

		return btn;
	}
}
