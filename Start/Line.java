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

public class Line extends GridItem{
	private Point endPoint;
	private CircleNode start,end;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3370633118359886282L;
	
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
	public void paint(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setColor(DEFAULT_COLOR);
		if(end!=null){
			g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
			return;
		}
		g.drawLine(start.getX(),start.getY(),(int)endPoint.getX(),(int)endPoint.getY());
		
	}
	
}
