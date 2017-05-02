package Start;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class CircleGraph {

	public CircleGraph() {
		nodes = new ArrayList<CircleNode>();
		gNodes = new ArrayList<GridNode>();
	}

	public void add(CircleNode n, Point2D p) {
		Rectangle2D bounds = n.getBounds();
		n.translate(p.getX() - bounds.getX(),p.getY() - bounds.getY());
		nodes.add(n);
	}
	
	public void add(GridNode n, Point2D p){
		Rectangle2D bounds = n.getBounds();
		n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());
		gNodes.add(n);
	}

	public void draw(Graphics2D g2) {
		for(GridNode n : gNodes)
			n.draw(g2);
		for (CircleNode n : nodes)
			n.draw(g2);
	}

	public List<CircleNode> getNodes() {
		return nodes;
	}
	
	public List<GridNode> get_gNodes() {
		return gNodes;
	}

	private ArrayList<CircleNode> nodes;
	private ArrayList<GridNode> gNodes;
}

