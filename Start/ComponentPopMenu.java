package Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ComponentPopMenu extends JPanel{
	
	private JPopupMenu popMenu;
	private CircleGraph gGraph;
	private GridNode selectedComponent;
	private String selectedItem;
	private GraphPanel gp;
	
	public ComponentPopMenu(CircleGraph g){
		
		gGraph = g;
		
		popMenu = new JPopupMenu();
		ActionListener menuListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.print("pop");
				if(e.getActionCommand() == "Remove"){
					System.out.print("yeah");
					selectedItem = e.getActionCommand();
					gGraph.removeComponent(selectedComponent);
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
	
	public String getSelected(){
		return selectedItem;
	}
	
	public void externalRepaint(GraphPanel p){
		gp = p;
	}
	
}
