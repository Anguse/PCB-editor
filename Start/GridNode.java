package Start;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

public class GridNode extends JComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3735539464503062041L;

	public GridNode(int rows, String text){
		x=0;y=0;
		this.rows = rows;
		setBounds(0-DEFAULT_OFFSET/2,0,DEFAULT_SIZE+DEFAULT_OFFSET,DEFAULT_SIZE+10*(rows-1));
		color = DEFAULT_COLOR;
		tooltip = text;
		setToolTipText(text);
		for(int i = 0; i < rows; i++){
			CircleNode newNode = new CircleNode(x-5, y+10*(i+1),5,Color.CYAN); 
			add(newNode);
			nodes.add(newNode);
			newNode = new CircleNode(x+20, y+10*(i+1),5,Color.CYAN);
			add(newNode);
			nodes.add(newNode);
		}
		
	}
	
	/* This constructor creates a GridNode with set size and uneven amount of
	 * pins (2 and 1).*/
	public GridNode(String text){
		x=0;y=0;
		rows = 2;
		setBounds(0-DEFAULT_OFFSET/2,0,DEFAULT_SIZE+DEFAULT_OFFSET,DEFAULT_SIZE+10*(rows-1));
		color = DEFAULT_COLOR;
		tooltip = text;
		setToolTipText(text);
		for(int i = 0; i < rows; i++){
			CircleNode newNode = new CircleNode(x-5, y+10*(i+1),5,Color.CYAN); 
			add(newNode);
			nodes.add(newNode);
		}
		CircleNode newNode = new CircleNode(x+20, y+15,5,Color.CYAN);
		add(newNode);
		nodes.add(newNode);
		
	}
	public void paint(Graphics g){
		Rectangle2D rectangle = new Rectangle(x, y, DEFAULT_SIZE, DEFAULT_SIZE+10*(rows-1));
		Color oldColor = g.getColor();
		g.setColor(color);
		((Graphics2D)g).fill(rectangle);
		((Graphics2D)g).setColor(oldColor);
		((Graphics2D)g).draw(rectangle);
		for(Component component : getComponents()){
			((CircleNode)component).draw((Graphics2D)g);
		}
		g.setColor(color.BLACK);
		g.drawString(tooltip, getX()-(tooltip.length()/2)+1, getY()-1);
	}
	
	/**
	 * Moves the GridNode and all its components in the direction @param dx, @param dy 
	 * */
	public void vectorMove(double dx, double dy){
		x+=dx;y+=dy;
		setLocation((int)(getLocation().getX()+dx),(int)(getLocation().getY()+dy));
		for(Component component : getComponents()){
			component.setLocation((int)(component.getX()+dx),(int)(component.getY()+dy));
		}
	}
	public void moveTo(double x, double y){
		this.x=(int)x;this.y=(int)y;
		setLocation((int)x,(int)y);
		for(CircleNode node : getNodes()){
			node.vectorMove(x-getX(), y-getY());
		}
	}
	public ArrayList<CircleNode> getNodes(){
		return nodes;
	}
	private int x,y;
	private Color color;
	private static final int DEFAULT_OFFSET = 10;
	private static final int DEFAULT_SIZE = 20;
	private Color DEFAULT_COLOR = Color.GREEN;
	private ArrayList<CircleNode> nodes = new ArrayList<CircleNode>();
	private int rows;
	private String tooltip;
}
