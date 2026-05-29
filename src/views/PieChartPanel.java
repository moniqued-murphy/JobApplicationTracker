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

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Purpose: The responsibility of PieChartPanel is to draw a pie chart
 * that visually represents the distribution of job application statuses.
 *
 * PieChartPanel is-a JPanel
 * PieChartPanel is a custom panel that overrides paintComponent to draw a pie chart and legend
 */
public class PieChartPanel extends JPanel
{
	private int applied;
	private int interviews;
	private int offers;
	private int rejected;
	
	/**
	 * Constructor
	 * Purpose: Create an instance of PieChartPanel
	 * 
	 * @param appliedCount
	 * @param interviewCount
	 * @param offerCount
	 * @param rejectedCount
	 */
	public PieChartPanel()
	{
		this.applied = 0;
		this.interviews = 0;
		this.offers = 0;
		this.rejected = 0;
	}
	
	// Override the paintComponent method to draw the pie chart based on the current data
	@Override
	protected void paintComponent(Graphics g)
	{
		// Clear the background and prepare to draw the pie chart
		super.paintComponent(g);
		// Calculate the total number of applications to determine the proportions for each status
		int total = applied + interviews + offers + rejected;
		if (total == 0)
		{
			return; // Avoid division by zero
		}
		// Calculate the angles for each status based on their proportions and draw the pie chart
		int startAngle = 0;
		int diameter = 200;
		int x = (getWidth() - diameter) / 2 - 40;
		int y = (getHeight() - diameter) / 2;

		int appliedAngle = (int) ((double) applied / total * 360);
		g.setColor(new Color(100, 149, 237));
		g.fillArc(x, y, 200, 200, startAngle, appliedAngle);
		startAngle += appliedAngle;

		int interviewAngle = (int) ((double) interviews / total * 360);
		g.setColor(new Color(80, 180, 120));
		g.fillArc(x, y, 200, 200, startAngle, interviewAngle);
		startAngle += interviewAngle;

		int offerAngle = (int) ((double) offers / total * 360);
		g.setColor(new Color(230, 160, 80));
		g.fillArc(x, y, 200, 200, startAngle, offerAngle);
		startAngle += offerAngle;

		int rejectedAngle = (int) ((double) rejected / total * 360);
		g.setColor(new Color(220, 100, 100));
		g.fillArc(x, y, 200, 200, startAngle, rejectedAngle);

		// Draw the legend for the pie chart to indicate what each color represents
		int legendX = x + diameter + 20;
		int legendY = y + 20;

		drawLegendItem(g, legendX, legendY, new Color(100, 149, 237), "Applied (" + applied + ")");
		drawLegendItem(g, legendX, legendY + 30, new Color(80, 180, 120), "Interview (" + interviews + ")");
		drawLegendItem(g, legendX, legendY + 60, new Color(230, 160, 80), "Offer (" + offers + ")");
		drawLegendItem(g, legendX, legendY + 90, new Color(220, 100, 100), "Rejected (" + rejected + ")");
	}
	
	/**
	 * Purpose: Update the data for the pie chart and request a repaint to reflect the changes
	 * 
	 * @param applied
	 * @param interviews
	 * @param offers
	 * @param rejected
	 */
	public void updateData(int applied, int interviews, int offers, int rejected)
	{
		this.applied = applied;
		this.interviews = interviews;
		this.offers = offers;
		this.rejected = rejected;
		// Request the panel to be repainted with the new data
		repaint(); 
	}
	
	/**
	 * Helper method to draw a legend item for the pie chart
	 * Purpose: 
	 * @param g the Graphics object used for drawing
	 * @param x the x coordinate of the top left corner of the legend item
	 * @param y the y coordinate of the top left corner of the legend item
	 * @param color the color of the square representing the status in the legend
	 * @param label the text label for the legend item, indicating the status and count
	 */
	private void drawLegendItem(Graphics g, int x, int y, Color color, String label)
	{
	    // Draw the colored square
	    g.setColor(color);
	    g.fillRect(x, y, 14, 14);

	    // Draw the label text next to it
	    g.setColor(new Color(30, 40, 60));
	    g.drawString(label, x + 20, y + 12);
	}
}
