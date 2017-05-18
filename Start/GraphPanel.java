package Start;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class GraphPanel extends JComponent {
	private CircleGraph graph;
	private ComponentList componentList;
	private ShoppingList shoppingList;
	private ActionBar actionBar;
	private ComponentPopMenu popMenu;
	private JPopupMenu jPopMenu;
	private static final long serialVersionUID = 1L;
	private volatile GridItem clickedComponent;
	private volatile GridItem hoverComponent;
	private GraphPanel mThis = this;
	/**
	 * 
	 */
	public GraphPanel(ComponentList cList, ShoppingList sList, ComponentPopMenu pMenu, ActionBar aActionBar, CircleGraph aGraph) {
		popMenu = pMenu;
		jPopMenu = pMenu.getMenu();
		actionBar = aActionBar;
		componentList = cList;
		shoppingList = sList;
		graph = aGraph;
		actionBar.setGraphPanel(this);
		setBackground(Color.WHITE);
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent event){
				GridNode newNode = null;
				Point mousePoint = snapToGrid(event.getPoint());
				clickedComponent = getComponentAt(mousePoint);
				jPopMenu.setVisible(false);
				if(clickedComponent!=null && SwingUtilities.isLeftMouseButton(event) && !event.isControlDown()){
					return;
				}
				int componentIndex = componentList.getIndex();
				String componentName = componentList.getComponentName();
				if (SwingUtilities.isLeftMouseButton(event) && !event.isControlDown()) {
					if(componentIndex==0){
						newNode = new GridNode(1, componentName);
						mousePoint = adjustToOffset(newNode, mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==1){
						newNode = new GridNode(4, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==2){
						newNode = new GridNode(6, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==3){
						newNode = new GridNode(8, componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==4){
						newNode = new GridNode(componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==5){
						newNode = new GridNode(componentName);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
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
						popMenu.selectItem(componentName);
						popMenu.externalRepaint(mThis);
					}
				} else if(event.isControlDown()){
					System.out.print("here");
					newNode = (GridNode) getComponentAt(mousePoint);
					if(newNode != null){
						graph.removeComponent(newNode);
						shoppingList.removeItem(componentName);
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
				if(clickedComponent.getClass() == Line.class){
					if(isColliding((Line)clickedComponent)){
						graph.removeComponent(clickedComponent);
						repaint();
						clickedComponent = null;
						return;
					}
					else if(getComponentAt(e.getPoint())!=null&&getComponentAt(e.getPoint()).getClass()==CircleNode.class){
						((Line)(clickedComponent)).setEnd((CircleNode)getComponentAt(e.getPoint()));
						clickedComponent.setColor(clickedComponent.DEFAULT_COLOR);
						repaint();
						clickedComponent = null;
						return;
					}
					else{
						graph.removeComponent(clickedComponent);
					}
				}
				repaint();
				clickedComponent = null;

			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				hoverComponent = getComponentAt(e.getPoint());
				if(hoverComponent!=null&&hoverComponent.getClass()==CircleNode.class){
					Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
				    setCursor(cursor);
				}
				else{
					Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if(e.isControlDown()){
					return;
				}
				Point mousePoint = snapToGrid(e.getPoint());
				mousePoint = adjustToOffset(clickedComponent,mousePoint);
				if(clickedComponent!=null&&clickedComponent.getClass()==GridNode.class){
					if(inFrame((GridNode)clickedComponent,mousePoint.getX()-clickedComponent.getX(),mousePoint.getY()-clickedComponent.getY())==true){
						((GridNode) clickedComponent).vectorMove(((int)(mousePoint.getX()-clickedComponent.getX())),(int)(mousePoint.getY()-clickedComponent.getY()));	
						repaint();
					}
				}
				else if(clickedComponent!=null&&clickedComponent.getClass()==CircleNode.class){
					Line newLine = new Line((CircleNode)clickedComponent);
					graph.add((Line)newLine,mousePoint);
					clickedComponent = newLine;
					((Line)(clickedComponent)).setEndPoint(mousePoint);
					repaint();
				}
				else if(clickedComponent!=null&&clickedComponent.getClass()==Line.class){
					if(isColliding((Line)clickedComponent)){
						clickedComponent.setCollided(true);
					}
					else{
						clickedComponent.setCollided(false);
						if(getComponentAt(e.getPoint())!=null&&getComponentAt(e.getPoint()).getClass()==CircleNode.class){
							clickedComponent.setConnection(true);
						}
						else{
							clickedComponent.setConnection(false);
						}
					}
					((Line)(clickedComponent)).setEndPoint(mousePoint);
					repaint();
				}
			}
		});
	}
	public boolean isColliding(Line line){ 
		for(GridItem gridItem : graph.getComponents()){
			if(gridItem.getClass()==Line.class&&gridItem!=clickedComponent&&((Line)(clickedComponent)).produceLineBounds().intersectsLine((Line2D)((Line) gridItem).produceLineBounds())){
				return true;
			}
		}
		return false;
	}
	
	
	/**Returns the component which contains @param point, if it exists.
	 * Else null*/
	public GridItem getComponentAt(Point point){
		for(GridItem gridItem : graph.getComponents()){
			if(gridItem.getBounds().contains(point)){
				for(GridItem node : gridItem.getNodes()){
					System.out.println(point);
					System.out.println(node.getBounds());
					if(node.getBounds().contains(point)){
						System.out.println("node here!");
						return node;
					}
				}
				return gridItem;
			}
		}
		return null;
	}
	/**
	 * Returns true if the given @param component, moved with the vector @param dx, @param dy 
	 * still remains within the boundaries of its parent, else false
	 * */
	public boolean inFrame(GridItem gridItem, double dx, double dy){
		Rectangle2D bounds = gridItem.produceBounds();
		bounds.setRect(bounds.getX()+dx, bounds.getY()+dy, gridItem.w, gridItem.h);
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
}
