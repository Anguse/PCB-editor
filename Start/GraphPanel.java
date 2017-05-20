package Start;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class GraphPanel extends JComponent {
	private PcbGraph graph;
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
	 * */
	public GraphPanel(ComponentList cList, ShoppingList sList, ComponentPopMenu pMenu, ActionBar aActionBar, PcbGraph aGraph) {
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
				Point mousePoint = event.getPoint();
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
						mousePoint = snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
							graph.add(newNode,mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==1){
						newNode = new GridNode(4,componentName);
						mousePoint=snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==2){
						newNode = new GridNode(6, componentName);
						mousePoint = snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==3){
						newNode = new GridNode(8, componentName);
						mousePoint = snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==4){
						newNode = new GridNode(componentName);
						mousePoint = snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
							graph.add(newNode, mousePoint);
							shoppingList.addItem(componentName);
						}
						newNode.setComponentPopupMenu(jPopMenu);
					}
					else if(componentIndex==5){
						newNode = new GridNode(componentName);
						mousePoint = snapToGrid(mousePoint);
						if(inFrame(newNode,mousePoint.getX()-newNode.getX(),mousePoint.getY()-newNode.getY())){
							mousePoint.setLocation(mousePoint.getX()+5, mousePoint.getY());
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
						popMenu.selectItem(newNode.getCompName());
						popMenu.externalRepaint(mThis);
					}
				} else if(event.isControlDown()){
					newNode = (GridNode) getComponentAt(mousePoint);
					if(newNode != null){
						graph.removeComponent(newNode);
						shoppingList.removeItem(newNode.getCompName());
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
				if(clickedComponent!=null&&clickedComponent.getClass() == Line.class){
					if(isColliding((Line)clickedComponent)){
						graph.removeComponent(clickedComponent);
						repaint();
						clickedComponent = null;
						return;
					}
					else if(getComponentAt(e.getPoint())!=null&&getComponentAt(e.getPoint()).getClass()==CircleNode.class&&differentParents(((Line)(clickedComponent)).getStart(),(CircleNode)getComponentAt(e.getPoint()))){
						((Line)(clickedComponent)).setEnd((CircleNode)getComponentAt(e.getPoint()));
						((CircleNode)(getComponentAt(e.getPoint()))).connect((Line)clickedComponent);
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
				Point mousePoint = e.getPoint();
				hoverComponent = getComponentAt(e.getPoint());
				mousePoint = adjustToOffset(clickedComponent,mousePoint);
				if(clickedComponent!=null&&clickedComponent.getClass()==GridNode.class){
					if(hoverComponent!=null&&hoverComponent.getClass()==GridNode.class){
						return;
					}
					mousePoint = snapToGrid(e.getPoint());
					if(inFrame((GridNode)clickedComponent,mousePoint.getX()-clickedComponent.getX(),mousePoint.getY()-clickedComponent.getY())==true){
						((GridNode) clickedComponent).vectorMove(((int)(mousePoint.getX()-clickedComponent.getX())),(int)(mousePoint.getY()-clickedComponent.getY()));	
						repaint();
					}
				}
				else if(clickedComponent!=null&&clickedComponent.getClass()==CircleNode.class){
					Line newLine = new Line((CircleNode)clickedComponent);
					((CircleNode)(clickedComponent)).connect(newLine);
					graph.add((Line)newLine,mousePoint);
					clickedComponent = newLine;
					((Line)(clickedComponent)).setEndPoint(mousePoint);
					repaint();
				}
				else if(clickedComponent!=null&&clickedComponent.getClass()==Line.class){
					if(isColliding((Line)clickedComponent)||hoverComponent!=null&&hoverComponent.getClass()==CircleNode.class&&differentParents(((Line)clickedComponent).getStart(),(CircleNode)hoverComponent)==false){
						clickedComponent.setCollided(true);
					}
					else{
						clickedComponent.setCollided(false);
						if(hoverComponent!=null&&hoverComponent.getClass()==CircleNode.class){
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
	/**

	 * Returns true if @param line is intersecting with another line 
	 * */
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
					if(node.getBounds().contains(point)){
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
		if(component!=null){
			point.x-=component.getWidth()/2;
			point.y-=component.getHeight()/2;
			return point;
		}
		return point;
	}
	/**
	 * Modifies the Point p to fit the grid
	 * @param Point p
	 * @return Point p, adjusted to the grid
	 * */
	private Point snapToGrid(Point p){
		//Add better snapping here
		p.setLocation(p.getX()-p.getX()%30,p.getY()-p.getY()%20);
		return p;
	}
	public void paintComponent(Graphics g) {
		drawGrid(g);
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
	}
	/**
	 * Returns true if the given @param start & @param end is located in different parents
	 * */
	public boolean differentParents(CircleNode start, CircleNode end){
		GridNode node = (GridNode)start.getParent();
		if(end!=null&&node!=end.getParent()){
			return true;
		}
		return false;
	}
	/**
	 * Draws a grid on the @param g
	 * */
	public void drawGrid(Graphics g){
		for(int i = 0; i < getParent().getHeight()/20; i++){
			g.drawLine(0, 20*i, getParent().getWidth(), 20*i);
		}
		for(int i = 0; i <= getParent().getWidth()/30; i++){
			g.drawLine(30*i,0,30*i,getParent().getHeight());
		}
	}
}
