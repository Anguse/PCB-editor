package Start;

import java.awt.Component;
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveObject implements Serializable{

	private static final long serialVersionUID = -7618804253210183551L;
	private ArrayList<Component> sList;
	
	public SaveObject(ArrayList<Component> list){
		sList = list;
	}

}
