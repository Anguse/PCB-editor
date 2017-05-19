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

public class GridNode extends GridItem implements Serializable{
	private int DEFAULT_OFFSET = 10;
	private static final int DEFAULT_SIZE = 20;
	private int rows;
	private String tooltip;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3735539464503062041L;

	public GridNode(int rows, String text){
		this.rows = rows;
		x=0;y=0;w=DEFAULT_SIZE+DEFAULT_OFFSET;h=DEFAULT_SIZE+10*(rows-1);
		color = DEFAULT_COLOR;
		tooltip = text;
		setBounds(produceBounds());
		setToolTipText(text);
		for(int i = 0; i < rows; i++){
			add(new CircleNode(x-5, y+10*(i+1),5,Color.CYAN));
			add(new CircleNode(x+20, y+10*(i+1),5,Color.CYAN));
		}	
	}
	/* This constructor creates a GridNode with set size and uneven amount of
	 * pins (2 and 1).*/
	public GridNode(String text){
		rows=2;
		x=0;y=0;w=DEFAULT_SIZE+DEFAULT_OFFSET;h=DEFAULT_SIZE+10*(rows-1);
		setBounds(produceBounds());
		color = DEFAULT_COLOR;
		tooltip = text;
		setToolTipText(text);
		for(int i = 0; i < rows; i++){
			add(new CircleNode(x-5, y+10*(i+1),5,Color.CYAN)); 
		}
		add(new CircleNode(x+20, y+15,5,Color.CYAN));
	}
	public void paint(Graphics2D g){
		setBounds(produceBounds());
		Rectangle2D rectangle = new Rectangle(x,y,DEFAULT_SIZE,h);
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fill(rectangle);
		g.setColor(oldColor);
		g.draw(rectangle);
		for(Component component : getComponents()){
			((CircleNode)component).paint((Graphics2D)g);
		}
		g.setColor(DEFAULT_COLOR);
		g.drawString(tooltip, getX()-(tooltip.length()/2)+1, getY()-1);
		g.setColor(oldColor);
	}
	@Override
	public Rectangle produceBounds(){
		Rectangle bounds = new Rectangle(x-DEFAULT_OFFSET/2,y,w,h);
		return bounds;
	}
	
	public String getCompName(){
		return tooltip;
	}
}
