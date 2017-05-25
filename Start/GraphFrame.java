package Start;

import java.awt.*;
import javax.swing.*;

/**
 * This frame shows the toolbar and the graph. The View module for the PCBGraphEditor
 */
public class GraphFrame extends JFrame {
	private GraphPanel panel;
	private JScrollPane scrollPane;
	private ToolBar toolBar;
	private ActionBar actionBar;
	private ComponentList cList;
	private ShoppingList sList;
	private ComponentPopMenu pMenu;
	public static final int FRAME_WIDTH = 1200;	
	public static final int FRAME_HEIGHT = 800;
	private static final long serialVersionUID = -6382842890638267357L;
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
		graph.setShoppingList(sList);

		upper.add(cList, BorderLayout.NORTH);
		upper.add(sList, BorderLayout.SOUTH);
		this.add(actionBar, BorderLayout.NORTH);
		this.add(upper, BorderLayout.EAST);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	/**
	 * @return the toolBar
	 */
	public ToolBar getToolBar() {
		return toolBar;
	}
	/**
	 * @param toolBar the toolBar to set
	 */
	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}
}
