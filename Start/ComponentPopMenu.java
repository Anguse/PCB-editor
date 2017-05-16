package Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ComponentPopMenu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPopupMenu popMenu;
	private CircleGraph gGraph;
	private GridNode selectedComponent;
	private String selectedItem;
	private GraphPanel gp;
	private ShoppingList sList;
	
	public ComponentPopMenu(CircleGraph g, ShoppingList s){
		
		gGraph = g;
		
		sList = s;
		
		popMenu = new JPopupMenu();
		ActionListener menuListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.print("pop");
				if(e.getActionCommand() == "Remove"){
					System.out.print("yeah");
					gGraph.removeComponent(selectedComponent);
					sList.removeItem(selectedItem);
					popMenu.setVisible(false);
					gp.repaint();
				}
			}
		};
		JMenuItem item;
		popMenu.add(item = new JMenuItem("Remove"));
		item.setHorizontalAlignment(JMenuItem.RIGHT);
		item.addActionListener(menuListener);
		
		add(popMenu);
		
		//popMenu.addPopupMenuListener(l);
	}
	
	public JPopupMenu getMenu(){
		return popMenu;
	}
	
	public void selectComponent(GridNode g){
		selectedComponent = g;
	}
	
	public void selectItem(String s){
		selectedItem = s;
	}
	
	public String getSelected(){
		return selectedItem;
	}
	
	public void externalRepaint(GraphPanel p){
		gp = p;
	}
	
}
