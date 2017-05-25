package Start;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
	/**Produces a list to be attached to the main frame.
	 * The array containing the information on what is being selected need
	 * to match the amount of components that can be created in the GraphPanel.*/
public class ComponentList extends JPanel{
	private static final long serialVersionUID = 1L;
	private PcbGraph gGraph;
	private String[] nameList = {"NOT", "4-pin component", "6-pin component", "8-pin component", "AND", "OR" };
	private String selected;
	JList<String> list;
	public ComponentList(PcbGraph g){
		selected = "";
		setGraph(g);
		list = new JList<String>(nameList);
		list.setBounds(60, 60, 30, 60);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int selectedIndex = list.getSelectedIndex();
				selected = nameList[selectedIndex];
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


	public PcbGraph getGraph() {
		return gGraph;
	}


	public void setGraph(PcbGraph gGraph) {
		this.gGraph = gGraph;
	}
	
}
