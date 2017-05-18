package Start;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComponentList extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private PcbGraph gGraph;
	private String[] testList = {"NOT", "4-pin component", "6-pin component", "8-pin component", "AND", "OR" };
	private String selected;
	JList<String> list;
	
	/**Produces a list to be attached to the main frame.
	 * The array containing the information on what is being selected need
	 * to match the amount of components that can be created in the GrapgPanel.*/
	
	public ComponentList(PcbGraph g){
		selected = "";
		
		
		gGraph = g;
		list = new JList<String>(testList);
		list.setBounds(60, 60, 30, 60);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int selectedIndex = list.getSelectedIndex();
				selected = testList[selectedIndex];
				System.out.print(selectedIndex);
			}
			
		});
		add(list);
	}
	
	
	/**Return the index of selected JList item.
	 * @return the index of selected item in the JList
	 * @invariant 0 <= list.getSelectedIndex() <= list.length*/
	public int getIndex(){
		return list.getSelectedIndex();
	}
	
	public String getComponentName(){
		return selected;
	}
	
}
