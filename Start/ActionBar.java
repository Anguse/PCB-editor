package Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ActionBar extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] IMG_PATHS = {	"src\\saveIcon.png",
												"src\\openIcon.png"};
	private static final String[] IMG_PATHS_NAME = {"SAVE", "OPEN"};
	
	private ButtonGroup group;
	private PcbGraph bGraph;
	private GraphPanel gPanel;
	
	/**ActionBar produces buttons that have standard functions as
	 * save and load.
	 * @param CircleGraph graph, used to manipulate the CircleGraph class used.
	 * */
	
	public ActionBar(PcbGraph graph) {
		bGraph = graph;
		group = new ButtonGroup();
		for (String c : IMG_PATHS){
			try {
		         BufferedImage img = ImageIO.read(new File(c));
		         ImageIcon icon = new ImageIcon(img);
		         Image newImg = icon.getImage();
		         Image resizeImg = newImg.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		         icon = new ImageIcon(resizeImg);
		         add(icon);
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		}
	}
	
	
	/* Adds a button with the chosen icon.
	 * Icons are stored in the src folder and the method
	 * access them by using a String array with the path.
	 * The amount of paths AND icons determines how many
	 * buttons are added.*/
	public void add(final ImageIcon  n) {
		JButton button = new JButton();
		
		button.setIcon(n);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				Enumeration<AbstractButton> e = group.getElements();
				String buttonName = "";
				int i = 0;
				while (e.hasMoreElements()) {	//Iterates to find what button is selected by checking the button Color.
					JButton button = (JButton) e.nextElement();
					if (button == event.getSource()){
						buttonName = IMG_PATHS_NAME[i];
					}
					i++;
				}
				System.out.print(buttonName);
				if(buttonName == "SAVE"){
					//TODO: Add functionality.
					SaveFrame save = new SaveFrame(bGraph);
				} else if(buttonName == "OPEN"){
					//bGraph.loadComponents("testfile.dat");
					LoadFrame load = new LoadFrame(bGraph, gPanel);
					gPanel.repaint();
				}
			}
		});
		group.add(button);
		add(button);
	}
	
	/* Used by GraphPanel to give this class access to the GraphPanel.
	 * This is done so ActionBar class can call repaint() to
	 * construct the loaded data.*/
	public void setGraphPanel(GraphPanel g){
		gPanel = g;
	}
	

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;

}