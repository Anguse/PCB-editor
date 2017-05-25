package Start;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/**The SaveObject is used to save all components used and added to a ArrayList;
 * to save to a single object.*/
public class SaveObject implements Serializable{

	private static final long serialVersionUID = -7618804253210183551L;
	private ArrayList<GridItem> sList;
	private HashMap<Integer, String> hmn;
	private HashMap<Integer, Integer> hmv;
	private ArrayList<String> indexList;
	
	public SaveObject(ArrayList<GridItem> saveObject, HashMap<Integer, String> n, HashMap<Integer, Integer> v,
			ArrayList<String> iList){
		sList = saveObject;
		hmn = n;
		hmv = v;
		indexList = iList;
	}
	/**Returns the ArryList<Component>
	 * Used to access the private ArrayList<Component>*/
	public ArrayList<GridItem> getInfo(){
		return sList;
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

}
