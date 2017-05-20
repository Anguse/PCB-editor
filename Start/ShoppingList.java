package Start;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ShoppingList extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3495881061634047648L;
	private HashMap<Integer, String> hmn;
	private HashMap<Integer, Integer> hmv;
	private PcbGraph gGraph;
	private DefaultListModel<String> model;
	private List<String> indexList;
	JList<String> list;
	
	/**The ShoppingList class creates a JList, ArrayList and 2 HashMaps.
	 * The ArrayList and HashMaps are used to track the amount of components used.*/
	
	public ShoppingList(PcbGraph g){
		hmn = new HashMap<Integer, String>();
		hmv = new HashMap<Integer, Integer>();
		indexList = new ArrayList<String>();
		gGraph = g;
		list = new JList<String>(model = new DefaultListModel<>());
		list.setBounds(60, 60, 30, 60);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
		indexList = new ArrayList<String>();
			
		add(list);
	}
	
	
	/**Adds an item to the HashMaps for saving, ArrayList to track index and
	 * to the JList model to display the items. If there is already a item of the same name,
	 * addItem will increment the value in the hmv HashMap and set the text in the JList accordingly.*/
	public void addItem(String item){
		System.out.println(item);
		if(hmn.get(item.hashCode()) == null){
			hmn.put(item.hashCode(), item);
			indexList.add(item);
		}
		if(hmv.get(item.hashCode()) != null){
			model.set(indexList.indexOf(item), item + ": " + (hmv.get(item.hashCode()) + 1) + "   ");
			hmv.put(item.hashCode(), hmv.get(item.hashCode())+1);
		} else{
			hmv.put(item.hashCode(), 1);
			model.addElement(item + ": " + 1 + "   ");
		}
		
	}
	/**Removes a item. If there is more than one component of the items name placed. removeItem() will decrement
	 * the value in the hmv HashMap and update the JList accordingly.*/
	public String removeItem(String item){
		System.out.print(item);
		System.out.print(hmv.get(item.hashCode()));
		if(hmv.get(item.hashCode()) == 1){
			hmn.remove(item.hashCode());
			hmv.remove(item.hashCode());
			model.removeElement(indexList.indexOf(item));
			model.removeElementAt(indexList.indexOf(item));
			indexList.remove(item);
		} else{
			model.set(indexList.indexOf(item), item + ": " + (hmv.get(item.hashCode()) - 1) + "   ");
			hmv.put(item.hashCode(), hmv.get(item.hashCode())-1);
		}
		return item;
	}
	
	public HashMap<Integer, String> getHmn(){
		return hmn;
	}
	
	public HashMap<Integer, Integer> getHmv(){
		return hmv;
	}
	
	public ArrayList<String> getIndexList(){
		return (ArrayList<String>) indexList;
	}
	
	public DefaultListModel<String> getModel(){
		return model;
	}
	
	/**Updates the shopping list. This method is called when loading a file. It is important that the HashMaps and the
	 * ArrayList matches.
	 * */
	public void updateList(HashMap<Integer, String> n,HashMap<Integer, Integer> v,ArrayList<String> iList ){
		String name;
		int amount;
		for(int i = 0; i<indexList.size(); i++){
			name = indexList.get(i);
			amount = hmv.get(name.hashCode());
			for(int j = 0; j<amount; j++){
				this.removeItem(name);
			}
		}
		model.removeAllElements();
		hmn = new HashMap<Integer, String>();
		hmv = new HashMap<Integer, Integer>();
		indexList = new ArrayList<String>();
		for(int i = 0; i<iList.size(); i++){
			name = iList.get(i);
			amount = v.get(name.hashCode());
			for(int j = 0; j<amount; j++){
				this.addItem(name);
			}
		}
	}
}
