package Start;

import java.awt.Component;
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveObject implements Serializable{

	private static final long serialVersionUID = -7618804253210183551L;
	private ArrayList<Component> sList;
	
	/**The SaveObject is used to save all components used and added to a ArrayList;
	 * to save to a singel object.*/
	
	public SaveObject(ArrayList<Component> list){
		sList = list;
	}
	/**Returns the ArryList<Component>
	 * Used to access the private ArrayList<Component>*/
	public ArrayList<Component> getInfo(){
		return sList;
	}

}
