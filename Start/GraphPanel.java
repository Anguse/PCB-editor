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
	private GraphPanel mThis = this;

	public GraphPanel(ComponentList cList,ComponentPopMenu pMenu, ActionBar aActionBar, CircleGraph aGraph) {
		
		popMenu = pMenu;
		jPopMenu = pMenu.getMenu();
		actionBar = aActionBar;
		componentList = cList;
		graph = aGraph;
		actionBar.setGraphPanel(this);
		setBackground(Color.WHITE);
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent event){
				getParent().getParent().setSize(900, 600);
				getParent().setSize(900,600);
				System.out.println(getParent().getWidth()+"  "+getParent().getHeight());
				GridNode newNode = null;
				Point mousePoint = snapToGrid(event.getPoint());
				clickedComponent = getComponentAt(mousePoint); 
				jPopMenu.setVisible(false);
				if(clickedComponent!=null && SwingUtilities.isLeftMouseButton(event)){
					return;
				}
				int componentIndex = componentList.getIndex();
				String componentName = componentList.getComponentName();
				if (SwingUtilities.isLeftMouseButton(event)) {
					if(componentIndex==0){
						newNode = new GridNode(1, componentName);
						mousePoint = adjustToOffset(newNode, mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==1){
						newNode = new GridNode(4, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==2){
						newNode = new GridNode(6, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==3){
						newNode = new GridNode(8, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==4){
						newNode = new GridNode(componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==5){
						newNode = new GridNode(componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
				} else if(SwingUtilities.isRightMouseButton(event)){
					System.out.print("right click");
					newNode = (GridNode) getComponentAt(mousePoint);
					if(newNode != null){
						//graph.removeComponent(newNode);
						newNode.getComponentPopupMenu().setLocation(mousePoint);
						newNode.getComponentPopupMenu().setVisible(true);
						popMenu.selectComponent(newNode);
						popMenu.externalRepaint(mThis);
					}
				}
				clickedComponent = newNode;
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
				Point mousePoint = snapToGrid(e.getPoint());
				mousePoint = adjustToOffset(clickedComponent,mousePoint);
				if(clickedComponent!=null&&clickedComponent.getClass()==GridNode.class){
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
	 * Adjusts @param point from top left corner, to middle of @param component
	 * @return Adjusted point
	 * */
	public Point adjustToOffset(Component component, Point point){
		point.x-=component.getWidth()/2;
		point.y-=component.getHeight()/2;
		return point;
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
		p.setLocation(p.getX()-p.getX()%30,p.getY()-p.getY()%20);
		return p;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
	}

	private CircleGraph graph;
	private ComponentList componentList;
	private ActionBar actionBar;
	private ComponentPopMenu popMenu;
	private JPopupMenu jPopMenu;
}
