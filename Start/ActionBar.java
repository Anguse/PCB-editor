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
	private static final String[] IMG_PATHS = {	"C:\\Users\\Felix\\workspace\\CircuitBoardProject\\src\\Start\\saveIcon.png",
												"C:\\Users\\Felix\\workspace\\CircuitBoardProject\\src\\Start\\openIcon.png"};
	private static final String[] IMG_PATHS_NAME = {"SAVE", "OPEN"};
	
	private ButtonGroup group;
	private CircleGraph bGraph;
	
	public ActionBar(CircleGraph graph) {
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

	public String getSelectedAction() {
		Enumeration<AbstractButton> e = group.getElements();
		int i = 0;
		while (e.hasMoreElements()) {	//Iterates to find what button is selected by checking the button Color.
			JToggleButton button = (JToggleButton) e.nextElement();
			if (button.isSelected())
				return IMG_PATHS_NAME[i];
			i++;
		}
		return null;
	}

	public void add(final ImageIcon  n) {
		JButton button = new JButton();
		
		button.setIcon(n);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
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
						System.out.println("Button is working!");	//For testing.
						bGraph.saveComponents("testfile");	
					} else if(buttonName == "OPEN"){
						System.out.println("Button is working2!");	//For testing.
						bGraph.loadComponents("testfile.dat");
						repaint();
					}
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		group.add(button);
		add(button);
	}
	

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;

}