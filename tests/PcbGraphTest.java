package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import Start.*;

public class PcbGraphTest {
	PcbGraph graph;
	@Before
	public void setUp() throws Exception {
		graph = new PcbGraph();
		for(int i = 0; i < 500; i++){
			graph.add(new GridNode("test"+i), new Point(i,i));
		}
		graph.setShoppingList(new ShoppingList(graph));
	}

	@Test
	public void testAdd() {
		for(int i = 0; i < 500; i++){
			graph.add(new GridNode("test"+i), new Point(i,i));
		}
		assertEquals(1000, graph.getComponents().size());
		assertEquals(new Point(250,250),graph.getComponents().get(250).getLocation());
	}

	@Test
	public void testGetComponents() {
		int nulls = 0;
		assertEquals(0,nulls);
		assertEquals(500, graph.getComponents().size());
	}

	@Test
	public void testSaveComponents() throws ClassNotFoundException, IOException {
		ArrayList<GridItem> oldGraphComponents = graph.getComponents();
		graph.saveComponents("jUnitTest");
		graph.loadComponents("jUnitTest");
		ArrayList<GridItem> newGraphComponents = graph.getComponents();
		int correct = 0;
		for(int i = 0; i < oldGraphComponents.size(); i++){
			if(oldGraphComponents.get(i)!=null&&oldGraphComponents.get(i).toString().equals(newGraphComponents.get(i).toString())){
				correct++;
			}
		}
		assertEquals(500,correct);
	}
	@Test
	public void testRemoveComponent() {
		for(int i = 0; i < graph.getComponents().size();){
			graph.removeComponent(graph.getComponents().get(i));
		}
		assertEquals(0,graph.getComponents().size());
	}
}
