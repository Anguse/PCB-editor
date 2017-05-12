package Start;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GridNode extends JComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3735539464503062041L;

	public GridNode(int rows){
		setBounds(0,0,DEFAULT_SIZE,DEFAULT_SIZE+10*(rows-1));
		color = DEFAULT_COLOR;
		for(int i = 0; i < rows; i++){
			add(new CircleNode(getX()-DEFAULT_SIZE/4, getY()+10*(i+1),5,Color.CYAN));
			add(new CircleNode(getX()+20, getY()+10*(i+1),5,Color.CYAN));
		}
		
		
	}
	public void paint(Graphics g){
		Rectangle2D rectangle = getBounds();
		Color oldColor = g.getColor();
		g.setColor(color);
		((Graphics2D)g).fill(rectangle);
		((Graphics2D)g).setColor(oldColor);
		((Graphics2D)g).draw(rectangle);
		for(Component component : getComponents()){
			((CircleNode)component).draw((Graphics2D)g);
		}	
	}
	/**
	 * Moves the GridNode and all its components in the direction @param dx, @param dy 
	 * */
	public void vectorMove(double dx, double dy){
		setLocation((int)(getLocation().getX()+dx),(int)(getLocation().getY()+dy));
		for(Component component : getComponents()){
			component.setLocation((int)(component.getLocation().getX()+dx),(int)(component.getLocation().getY()+dy));
		}
	}
	
	private Color color;
	private static final int DEFAULT_SIZE = 20;
	private Color DEFAULT_COLOR = Color.GREEN;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();

}
