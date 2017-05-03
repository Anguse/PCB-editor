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

public class CircleGraph implements Serializable{

	private static final long serialVersionUID = -6757559296850264270L;

	public CircleGraph() {
		components = new ArrayList<Component>();
	}

	
	@SuppressWarnings("deprecation")
	public void add(Component c, Point2D p){
		Rectangle2D bounds = c.getBounds();
		c.move((int)(p.getX() - bounds.getX()),(int)( p.getY() - bounds.getY()));
		components.add(c);
	}
	public void add(GridNode n, Point2D p){
		Rectangle2D bounds = n.getBounds();
		n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());
		components.add(n);
	}

	public void draw(Graphics2D g2) {
		for(Component c : components)
			c.paint(g2);
	}
	
	public List<Component> getComponents() {
		return components;
	}
	
	/*Saves the components array.
	 * TODO: Thoroughly testing to makes sure components are recreatable and saves correctly
	 * No more exception, .dat file is created. Have to test it by adding load function.*/
	public void saveComponents(String filename){
		ArrayList<Component> saveObject = (ArrayList<Component>) this.getComponents();
		SaveObject savable = new SaveObject(saveObject);
		String file = filename + ".dat";
		
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
	public ArrayList<Component> loadComponents(String filepath) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream (new FileInputStream (filepath));
		SaveObject savable = (SaveObject) in.readObject();
		ArrayList<Component> saveObject = savable.getInfo();
		in.close();
		return saveObject;
	}
	/**Changes the component array to the loaded objects component array.
	 * Used to store loaded files information.*/
	public void loadComponentArray(ArrayList<Component> loadable){
		components = loadable;
	}

	private ArrayList<Component> components;
}

