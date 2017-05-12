package Start;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class GraphPanel extends JComponent {
	private volatile JComponent clickedComponent;

	public GraphPanel(ToolBar aToolBar, CircleGraph aGraph) {
		toolBar = aToolBar;
		graph = aGraph;
		setBackground(Color.WHITE);
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent event) {
				Point mousePoint = event.getPoint();
				int x = event.getX() - (event.getX()%30);
				int y = event.getY() - (event.getY()%20);
				clickedComponent = getComponentAt(mousePoint); 
				if(clickedComponent!=null){
					return;
				}
				mousePoint.setLocation(x,y);
				Color nodeColor = toolBar.getSelectedCircleNodeColor();
				if (nodeColor != null) {	//finds the color getSelectedCircleColor() returned.
					if(nodeColor==Color.BLACK){	//Each if statement is defined by the button color. TODO: Make more convenient buttons.. 
						graph.add(new GridNode(1), mousePoint);	//.. and expand function of the buttons.
					}
					else if(nodeColor==Color.WHITE){
						graph.add(new GridNode(6), mousePoint);
					}
					else if(nodeColor==Color.BLUE){
						graph.add(new GridNode(2), mousePoint);
					}
					else{
						try {
							toolBar.buttonAction(aGraph);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	//Test 4th button.
					}
				}
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				clickedComponent = null;
				
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX() - (e.getX()%30);
				int y = e.getY() - (e.getY()%20);
				Point mousePoint = new Point(x,y);
				
				if(clickedComponent!=null&&clickedComponent.getClass()==GridNode.class){
					System.out.println("drag");
					((GridNode) clickedComponent).vectorMove(x-clickedComponent.getX(),y-clickedComponent.getY());
					repaint();
				}
			}
		});
	}
	/**Returns the component which contains @param point, if it exists.
	 * Else null*/
	public JComponent getComponentAt(Point point){
		for(JComponent component : graph.getComponents()){
			if(component.getBounds().contains(point)){
				return component;
			}
		}
		return null;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
	}

	private CircleGraph graph;
	private ToolBar toolBar;
}
