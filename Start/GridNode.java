package Start;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GridNode {
	public GridNode(int rows){
		x = 0;
		y = 0;
		size = DEFAULT_SIZE;
		color = DEFAULT_COLOR;
		for(int i = 0; i < rows; i++){
			nodes.add(new CircleNode(x-DEFAULT_SIZE/4,y+10*(i+1),DEFAULT_SIZE/4,Color.CYAN));
			nodes.add(new CircleNode(x+20,y+10*(i+1),DEFAULT_SIZE/4,Color.CYAN));
		}
	}
	
	public Rectangle2D getBounds(){
		return new Rectangle2D.Double(x, y, size, size+5*(nodes.size()-1));
	}
	public void draw(Graphics2D g){
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
		x += dx;
		y += dy;
		for(CircleNode n : nodes){
			n.translate(dx,dy);
		}
	}
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 20;
	private Color DEFAULT_COLOR = Color.GREEN;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	
}
