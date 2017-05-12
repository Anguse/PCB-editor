package Start;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class ToolBar extends JPanel {

	private final static Color[] nodeColors = { Color.BLACK, Color.WHITE, Color.BLUE, Color.YELLOW, Color.ORANGE};

	public ToolBar(CircleGraph graph) {
		group = new ButtonGroup();
		for (Color c : nodeColors)
			add(new CircleNode(c));
	}

	public Color getSelectedCircleNodeColor() {
		Enumeration<AbstractButton> e = group.getElements();
		int i = 0;
		while (e.hasMoreElements()) {	//Iterates to find what button is selected by checking the button Color.
			JToggleButton button = (JToggleButton) e.nextElement();
			if (button.isSelected())
				return nodeColors[i];
			i++;
		}
		return null;
	}

	public void add(final CircleNode n) {
		JToggleButton button = new JToggleButton(new Icon() {
			public int getIconHeight() {
				return BUTTON_SIZE;
			}

			public int getIconWidth() {
				return BUTTON_SIZE;
			}

			public void paintIcon(Component c, Graphics g, int x, int y) {
				double width = n.getBounds().getWidth();
				double height = n.getBounds().getHeight();
				double scaleX = (BUTTON_SIZE - OFFSET) / width;
				double scaleY = (BUTTON_SIZE - OFFSET) / height;
				double scale = Math.min(scaleX, scaleY);

				Graphics2D g2 = (Graphics2D) g;
				AffineTransform oldTransform = g2.getTransform();
				g2.translate(x, y);
				g2.scale(scale, scale);
				g2.setColor(Color.black);
				n.draw(g2);
				g2.setTransform(oldTransform);
			}
		});
		group.add(button);
		add(button);
	}
	
	public void buttonAction(CircleGraph aGraph) throws ClassNotFoundException, IOException{	//Checks what button is pressed and execute desired function.
		setBackground(Color.WHITE);

		Color nodeColor = this.getSelectedCircleNodeColor();
		if(nodeColor== Color.YELLOW){
			//TODO: Add functionality.
			System.out.println("Button is working!");	//For testing.
			aGraph.saveComponents("disco");	
		} else if(nodeColor == Color.ORANGE){
			ArrayList<JComponent> loadable = aGraph.loadComponents("disco.dat");
			aGraph.loadComponentArray(loadable);
			repaint();
			
		}
	}

	private ButtonGroup group;

	private static final int BUTTON_SIZE = 10;	//Original size: 20
	private static final int OFFSET = 4;
}

