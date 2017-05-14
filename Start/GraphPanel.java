package Start;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class GraphPanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private volatile JComponent clickedComponent;

	public GraphPanel(ToolBar aToolBar, CircleGraph aGraph) {
		toolBar = aToolBar;
		graph = aGraph;
		setBackground(Color.WHITE);
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent event) {
				Point mousePoint = snapToGrid(event.getPoint());
				clickedComponent = getComponentAt(mousePoint); 
				if(clickedComponent!=null){
					return;
				}
				Color nodeColor = toolBar.getSelectedCircleNodeColor();
				if (nodeColor != null) {	//finds the color getSelectedCircleColor() returned.
					if(nodeColor==Color.BLACK){	//Each if statement is defined by the button color. TODO: Make more convenient buttons.. 
						GridNode newNode = new GridNode(1);
						graph.add(newNode, mousePoint);	//.. and expand function of the buttons.
						adjustToFrame(newNode);
					}
					else if(nodeColor==Color.WHITE){
						GridNode newNode = new GridNode(6);
						graph.add(newNode, mousePoint);
						adjustToFrame(newNode);
					}
					else if(nodeColor==Color.BLUE){
						GridNode newNode = new GridNode(2);
						graph.add(newNode, mousePoint);
						adjustToFrame(newNode);
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
				System.out.println("height: "+getParent().getHeight()+" width: " + getParent().getWidth());
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
				Point mousePoint = snapToGrid(e.getPoint());
				if(clickedComponent!=null&&clickedComponent.getClass()==GridNode.class){
					System.out.println("drag");
					if(inFrame((GridNode)clickedComponent,mousePoint.getX()-clickedComponent.getX(),mousePoint.getY()-clickedComponent.getY())==true){
						((GridNode) clickedComponent).vectorMove(mousePoint.getX()-clickedComponent.getX(),mousePoint.getY()-clickedComponent.getY());	
						repaint();
					}
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
	/**
	 * Returns true if the given @param component, moved with the vector @param dx, @param dy 
	 * still remains within the boundaries of its parent, else false
	 * */
	public boolean inFrame(Component component, double dx, double dy){
		Rectangle2D bounds = new Rectangle((int)(component.getX()+dx),(int)(component.getY()+dy),component.getWidth(), component.getHeight());
		if(getParent().getBounds().contains(bounds)==false){
			return false;
		}
		return true;
	}
	/**
	 * Adjusts component to GraphPanels parent
	 * @param Component
	*/
	public void adjustToFrame(Component component){
		if(getParent().getBounds().contains(component.getBounds())==false){
			if(component.getBounds().getX()+component.getWidth()>getParent().getWidth()){
				component.setLocation(getParent().getWidth()-component.getWidth(), component.getY());
			}
			if(component.getBounds().getX()<0){
				component.setLocation(0,component.getY());
			}
			if(component.getBounds().getY()+component.getHeight()>getParent().getHeight()){
				component.setLocation(component.getX(),getParent().getHeight()-component.getHeight());
			}
			if(component.getBounds().getY()<0){
				component.setLocation(component.getX(), 0);
			}
		}
		return;
	}
	public void adjustToFrame(GridNode n){
		if(getParent().getBounds().contains(n.getBounds())==false){
			if(n.getBounds().getX()+n.getWidth()>getParent().getWidth()){
				n.moveTo(getParent().getWidth()-n.getWidth(), n.getY());
			}
			if(n.getBounds().getX()<0){
				n.moveTo(0,n.getY());
			}
			if(n.getBounds().getY()+n.getHeight()>getParent().getHeight()){
				n.moveTo(n.getX(),getParent().getHeight()-n.getHeight());
			}
			if(n.getBounds().getY()<0){
				n.moveTo(n.getX(), 0);
			}
		}
		return;
	}
	/**
	 * Modifies the Point p to fit the grid
	 * @param Point p
	 * @return Point p, adjusted to the grid
	 * */
	private Point snapToGrid(Point p){
		//p.setLocation(p.getX()-p.getX()%30,p.getY()-p.getY()%20);
		return p;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
	}

	private CircleGraph graph;
	private ToolBar toolBar;
}
