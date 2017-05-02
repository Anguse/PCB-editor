package Start;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GridNode extends JComponent{
	public GridNode(int rows){
		setBounds(0,0,DEFAULT_SIZE,DEFAULT_SIZE+10*(rows-1));
		color = DEFAULT_COLOR;
		for(int i = 0; i < rows; i++){
			nodes.add(new CircleNode(getX()-DEFAULT_SIZE/4,getY()+10*(i+1),DEFAULT_SIZE/4,Color.CYAN));
			nodes.add(new CircleNode(getX()+20,getY()+10*(i+1),DEFAULT_SIZE/4,Color.CYAN));
		}
	}
	public void paintComponent(Graphics2D g){
		Rectangle2D rectangle = getBounds();
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fill(rectangle);
		g.setColor(oldColor);
		g.draw(rectangle);
		for(CircleNode n : nodes){
			n.draw(g);
		}	
	}
	public void translate(double dx,double dy){
		move((int)dx,(int)dy);
		for(CircleNode n : nodes){
			n.translate(dx,dy);
		}
	}
	private Color color;
	private static final int DEFAULT_SIZE = 20;
	private Color DEFAULT_COLOR = Color.GREEN;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	
}
