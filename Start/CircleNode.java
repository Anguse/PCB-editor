package Start;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

import javax.swing.JComponent;

/**
 * A circular node that is filled with a color.
 */
public class CircleNode extends JComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6316363996010354681L;
	public CircleNode(Color color) {
		size = DEFAULT_SIZE;
		setLocation(0,0);
		this.color = color;
	}
	public CircleNode(int x, int y, double size, Color color){
		this.size = size;
		setLocation(x,y);
		this.color = color;
	}

	public void draw(Graphics2D g2) {
		Ellipse2D circle = new Ellipse2D.Double(getX(), getY(), size, size);
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.fill(circle);
		g2.setColor(oldColor);
		g2.draw(circle);
	}
	public void vectorMove(double dx,double dy){
		setLocation((int)(getX()+dy),(int)(getY()+dy));
	}
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 5;
}
