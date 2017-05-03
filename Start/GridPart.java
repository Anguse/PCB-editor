package Start;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GridPart extends JComponent{
	public GridPart(){
		setBounds(0,0,3,3);
		color = DEFAULT_COLOR;
	}
	public void paintComponent(Graphics2D g){
		Rectangle2D rectangle = getBounds();
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fill(rectangle);
		g.setColor(oldColor);
		g.draw(rectangle);	
	}

	private Color color;
	private static final int DEFAULT_SIZE = 20;
	private Color DEFAULT_COLOR = Color.RED;
}
