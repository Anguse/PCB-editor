package Start;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class CircleGraph {

	public CircleGraph() {
		components = new ArrayList<Component>();
	}

	
	public void add(Component c, Point2D p){
		Rectangle2D bounds = c.getBounds();
		c.move((int)(p.getX() - bounds.getX()),(int)( p.getY() - bounds.getY()));
		components.add(c);
	}
	public void add(GridNode n, Point2D p){
		Rectangle2D bounds = n.getBounds();
		n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());
		components.add(n);
	}

	public void draw(Graphics2D g2) {
		for(Component c : components)
			c.paint(g2);
	}
	
	public List<Component> getComponents() {
		return components;
	}

	private ArrayList<Component> components;
}

