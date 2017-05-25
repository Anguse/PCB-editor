package Start;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
/**
 * An abstract class for all the components to be placed in the GraphPanel
 */
public abstract class GridItem extends JComponent{
	protected int x,y,w,h;
	protected Color DEFAULT_COLOR = Color.BLACK;
	protected Color COLLISION_COLOR = Color.RED;
	protected Color CONNECTION_COLOR = Color.GREEN;
	protected Color color = DEFAULT_COLOR;
	
	private static final long serialVersionUID = -5327478353150778105L;
	
	public abstract void paint(Graphics2D g);
	public abstract String toString();
	/**
	 * Moves the component and its children with the vector (dx,dy)
	 * @param dx,dy - how much the component should be moved in x and y direction
	 * */
	public void vectorMove(int dx,int dy){
		x+=dx;y+=dy;
		setLocation(x,y);
		for(Component component : getComponents()){
			if(component.getClass()==CircleNode.class){
				((CircleNode)(component)).vectorMove(dx,dy);
			}
		}
	}
	/**
	 * Generates the bounds for this component
	 * @return the bounds of the component, given as a rectangle
	 * */
	public Rectangle produceBounds(){
		Rectangle bounds = new Rectangle(x,y,w,h);
		return bounds;
	}
	/**
	 * returns the list of nodes, if the GridItem contains any
	 * @return all of the CircleNodes located in the GridItem
	 * */
	public List<CircleNode> getNodes(){
		List<CircleNode> nodes = new ArrayList<CircleNode>();
		for(Component component : getComponents()){
			if(component.getClass()==CircleNode.class){
				nodes.add((CircleNode) component);
			}
		}
		return nodes;
	}
	/**
	 * Sets the color of the component
	 * @param color - the Color which the GridItem should be changed to
	 * */
	public void setColor(Color color){
		this.color = color;
	}
	/**
	 *if state = true, changes the color of the component to CONNECTION_COLOR
	 *@param state - sets whether or not the GridItem is connected
	 * */
	public void setConnection(boolean state){
		if(state==true){
			color=CONNECTION_COLOR;
		}
		else{
			color=DEFAULT_COLOR;
		}
	}
	/**
	 *if state = true, changes the color of the component to COLLISION_COLOR
	 *@param state - sets whether or not the GridItem is collided
	 * */
	public void setCollided(boolean state){
		if(state==true){
			color=COLLISION_COLOR;
		}
		else{
			color=DEFAULT_COLOR;
		}
	}
	
}
