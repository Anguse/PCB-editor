package Start;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * A circular node that is filled with a color.
 */
public class CircleNode extends GridItem implements Serializable{
	private Line connection;
	private static final int DEFAULT_SIZE = 5;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6316363996010354681L;
	public CircleNode(Color color) {
		x=y=0;
		w=h=DEFAULT_SIZE;
		DEFAULT_COLOR=this.color=color;
		setBounds(produceBounds());
		connection = null;
	}
	public CircleNode(int x, int y, int size, Color color){
		this.x=x;this.y=y;w=h=size;
		DEFAULT_COLOR=this.color=color;
		setBounds(produceBounds());
		connection = null;
	}
	public void paint(Graphics2D g) {
		Ellipse2D circle = new Ellipse2D.Double(x,y,w,h);
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fill(circle);
		g.setColor(oldColor);
		g.draw(circle);
	}
	public void connect(Line line){
		connection = line;
	}
	public void disconnect(){
		connection = null;
	}
	public boolean isConnected(){
		if(connection==null){
			return false;
		}
		return true;
	}
	public Line getConnection(){
		return connection;
	}
}
