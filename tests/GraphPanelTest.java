package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Start.ActionBar;
import Start.ComponentList;
import Start.ComponentPopMenu;
import Start.GraphPanel;
import Start.GridNode;
import Start.PcbGraph;
import Start.ShoppingList;

public class GraphPanelTest {
	GraphPanel panel;
	PcbGraph graph;
	@Before
	public void setUp() throws Exception {
		graph = new PcbGraph();
		ComponentList cList = new ComponentList(graph);
		ShoppingList sList = new ShoppingList(graph);
		ComponentPopMenu pMenu = new ComponentPopMenu(graph, sList);
		ActionBar actionBar = new ActionBar(graph);
		graph.setShoppingList(sList);
		panel = new GraphPanel(cList, sList, pMenu, actionBar, graph);
		for(int i = 0; i < 15; i++){
			graph.add(new GridNode(""+i), new Point(i*30,i*30));
		}
	}
	@Test
	public void testGetComponentAtPoint() {
		assertEquals(""+5, panel.getComponentAt(new Point(5*30,5*30)).toString());
	}
	@Test
	public void testDifferentParents() {
		assertEquals(false,panel.differentParents(graph.getComponents().get(0).getNodes().get(0),graph.getComponents().get(0).getNodes().get(1)));
		assertEquals(true,panel.differentParents(graph.getComponents().get(0).getNodes().get(0), graph.getComponents().get(1).getNodes().get(0)));
	}

}
