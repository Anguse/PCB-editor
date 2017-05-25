package Start;
import java.awt.*;
import java.awt.geom.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * The model module of the PCBGraphEditor, this is where all of the grids components is located
 * */
public class PcbGraph implements Serializable{
	private ArrayList<GridItem> gridItems;
	private ShoppingList shoppingList;
	private static final long serialVersionUID = -6757559296850264270L;

	public PcbGraph() {
		gridItems = new ArrayList<GridItem>();
	}
	public void add(GridItem gridItem,Point2D p){
		gridItem.vectorMove((int)p.getX(),(int)p.getY());
		gridItems.add(gridItem);
	}
	public void draw(Graphics2D g) {
		for(GridItem gridItem : gridItems){
			gridItem.paint(g);
		}
	}
	public ArrayList<GridItem> getComponents() {
		return gridItems;
	}
	/**Saves all of the components in the list gridItems to a .dat file
	 * @param filename - A String that declares the name of the file to be saved
	 **/
	public void saveComponents(String filename){
		ArrayList<GridItem> saveObject = (ArrayList<GridItem>)getComponents();
		SaveObject savable = new SaveObject(saveObject, shoppingList.getHmn(), shoppingList.getHmv(), shoppingList.getIndexList());
		String file = "src\\"+filename + ".dat";
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(savable);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**Loads the file of the given filename, if it exists and sets the the list gridItems to the loaded file
	 * @param filepath - The name of the file to be loaded
	 **/
	public void loadComponents(String filepath) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream (new FileInputStream ("src\\"+filepath+".dat"));
		SaveObject savable = (SaveObject) in.readObject();
		ArrayList<GridItem> saveObject = savable.getInfo();
		in.close();
		loadComponentArray(saveObject);
		shoppingList.updateList(savable.getHmn(),savable.getHmv(),savable.getIndexList());
	}
	/**Changes the component array to the loaded objects component array.
	 * Used to store loaded files information.*/
	private void loadComponentArray(ArrayList<GridItem> loadable){
		gridItems = loadable;
	}
	/**Removes the component from the list gridItems.*/
	public void removeComponent(GridItem gridItem){
		gridItems.remove(gridItem);
		if(gridItem.getClass()==GridNode.class){
			for(CircleNode node : gridItem.getNodes()){
				if(node.isConnected()){
					Line connection = node.getConnection();
					connection.getEnd().disconnect();
					connection.getStart().disconnect();
					gridItems.remove(connection);
				}
			}
		}
	}
	public void setShoppingList(ShoppingList sList){
		shoppingList = sList;
	}
}

