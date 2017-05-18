package Start;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public abstract class GridItem extends JComponent{
	protected int x,y,w,h;
	@SuppressWarnings("unused")
	protected Color DEFAULT_COLOR = Color.BLACK;
	
	/**
	 * An abstract class for all the components to be placed in the GraphPanel
	 */
	private static final long serialVersionUID = -5327478353150778105L;
	
	public abstract void paint(Graphics2D g);
	public void vectorMove(int dx,int dy){
		x+=dx;y+=dy;
		setLocation(x,y);
		for(Component component : getComponents()){
			if(component.getClass()==CircleNode.class){
				((CircleNode)(component)).vectorMove(dx,dy);
			}
		}
	}
	public Rectangle produceBounds(){
		Rectangle bounds = new Rectangle(x,y,w,h);
		return bounds;
	}
	public List<CircleNode> getNodes(){
		List<CircleNode> nodes = new ArrayList<CircleNode>();
		for(Component component : getComponents()){
			if(component.getClass()==CircleNode.class){
				nodes.add((CircleNode) component);
			}
		}
		return nodes;
	}
}
