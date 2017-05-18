package Start;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

/**
 * This frame shows the toolbar and the graph.
 */
public class GraphFrame extends JFrame {

	public GraphFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PcbGraph graph = new PcbGraph();
		JPanel upper = new JPanel();
		cList = new ComponentList(graph);
		sList = new ShoppingList(graph);
		pMenu = new ComponentPopMenu(graph, sList);
		actionBar = new ActionBar(graph);
		panel = new GraphPanel(cList, sList, pMenu, actionBar, graph);
		scrollPane = new JScrollPane(panel);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		upper.add(cList, BorderLayout.NORTH);
		upper.add(sList, BorderLayout.SOUTH);
		this.add(actionBar, BorderLayout.NORTH);
		this.add(upper, BorderLayout.EAST);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private GraphPanel panel;
	private JScrollPane scrollPane;
	private ToolBar toolBar;
	private ActionBar actionBar;
	private ComponentList cList;
	private ShoppingList sList;
	private ComponentPopMenu pMenu;

	public static final int FRAME_WIDTH = 1200;	
	public static final int FRAME_HEIGHT = 800;
}
