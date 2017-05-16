package Start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Line extends JComponent{

	public Line(CircleNode start){
		this.start = start;
		end = null;
		endPoint = new Point(0,0);
	}
	public Line(CircleNode start,Point endPoint){
		this.start = start;
		end = null;
		this.endPoint = endPoint;
	}
	public void setEndPoint(Point endPoint){
		this.endPoint = endPoint;
	}
	public Point getEndPoint(){
		return endPoint;
	}
	public void setStart(CircleNode start){
		this.start = start;
	}
	public void setEnd(CircleNode end){
		this.end = end;
	}
	public CircleNode getStart(){
		return start;
	}
	public CircleNode getEnd(){
		return end;
	}
	public void paint(Graphics g2) {
		Color oldColor = ((Graphics2D)(g2)).getColor();
		g2.setColor(DEFAULT_COLOR);
		if(end!=null){
			g2.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
			return;
		}
		g2.drawLine(start.getX(),start.getY(),(int)endPoint.getX(),(int)endPoint.getY());
		
	}
	private int x,y;
	private double length;
	private Point endPoint;
	private CircleNode start,end;
	private Color DEFAULT_COLOR = Color.BLACK;
	private double DEFAULT_WIDTH = 1;
}
