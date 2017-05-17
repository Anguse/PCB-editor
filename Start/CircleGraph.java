package Start;
import java.awt.*;
import java.awt.geom.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.List;

import javax.swing.JComponent;

public class CircleGraph implements Serializable{
	private ArrayList<GridItem> gridItems;
	/**
	 * 
	 * */
	private static final long serialVersionUID = -6757559296850264270L;

	public CircleGraph() {
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
	public List<GridItem> getComponents() {
		return gridItems;
	}
	/*Saves the components array.
	 * TODO: Thoroughly testing to makes sure components are recreatable and saves correctly
	 * No more exception, .dat file is created. Have to test it by adding load function.*/
	public void saveComponents(String filename){
		ArrayList<GridItem> saveObject = (ArrayList<GridItem>)getComponents();
		SaveObject savable = new SaveObject(saveObject);
		String file = "src\\"+filename + ".dat";
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(savable);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*Loads the components array.
	 * TODO: Thoroughly testing to makes sure components are recreatable and loads correctly*/
	public void loadComponents(String filepath) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream (new FileInputStream ("src\\"+filepath+".dat"));
		SaveObject savable = (SaveObject) in.readObject();
		ArrayList<GridItem> saveObject = savable.getInfo();
		in.close();
		loadComponentArray(saveObject);

	}
	/**Changes the component array to the loaded objects component array.
	 * Used to store loaded files information.*/
	private void loadComponentArray(ArrayList<GridItem> loadable){
		gridItems = loadable;
	}
	/**Removes the component from the array list.*/
	public void removeComponent(GridItem gridItem){
		gridItems.remove(gridItem);
	}

}

