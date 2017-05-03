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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CircleGraph graph = new CircleGraph();
		toolBar = new ToolBar(graph);
		panel = new GraphPanel(toolBar, graph);
		scrollPane = new JScrollPane(panel);
		scrollPane.getViewport().setBackground(Color.WHITE);

		this.add(toolBar, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private GraphPanel panel;
	private JScrollPane scrollPane;
	private ToolBar toolBar;

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;
}
