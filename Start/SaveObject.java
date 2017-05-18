package Start;

import java.awt.Component;
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

public class SaveObject implements Serializable{

	private static final long serialVersionUID = -7618804253210183551L;
	private ArrayList<GridItem> sList;
	
	/**The SaveObject is used to save all components used and added to a ArrayList;
	 * to save to a singel object.*/
	
	public SaveObject(ArrayList<GridItem> saveObject){
		sList = saveObject;
	}
	/**Returns the ArryList<Component>
	 * Used to access the private ArrayList<Component>*/
	public ArrayList<GridItem> getInfo(){
		return sList;
	}

}
