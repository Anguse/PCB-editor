package Start;

import java.awt.*;
import java.awt.geom.*;

/**
 * A circular node that is filled with a color.
 */
public class CircleNode {
	public CircleNode(Color aColor) {
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
	}
	
	public CircleNode(double x, double y, int size, Color color){
		this.color = color;
		this.size = size;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, size, size);
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.fill(circle);
		g2.setColor(oldColor);
		g2.draw(circle);
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, size, size);
	}

	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 20;
}
