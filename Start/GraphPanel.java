package Start;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphPanel extends JComponent {

	public GraphPanel(ToolBar aToolBar, CircleGraph aGraph) {
		toolBar = aToolBar;
		graph = aGraph;
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Point2D mousePoint = event.getPoint();
				int x = event.getX() - (event.getX()%30);
				int y = event.getY() - (event.getY()%40);
				mousePoint.setLocation(x,y);
				Color nodeColor = toolBar.getSelectedCircleNodeColor();
				if (nodeColor != null) {
					if(nodeColor==Color.BLACK)
						graph.add(new GridNode(1), mousePoint);
					else
						graph.add(new GridNode(6), mousePoint);
				}
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
	}

	private CircleGraph graph;
	private ToolBar toolBar;
}
