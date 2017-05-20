package Start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
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
		color = DEFAULT_COLOR;
		this.start = start;
		end = null;
		endPoint = new Point(0,0);
	}
	public Line(CircleNode start,Point endPoint){
		color = DEFAULT_COLOR;
		this.start = start;
		end = null;
		this.endPoint = endPoint;
	}
	public Line(CircleNode start, CircleNode end){
		color = DEFAULT_COLOR;
		this.start= start;
		this.end = end;
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
		g.setColor(color);
		if(end!=null){
			g.drawLine((int)start.getBounds().getCenterX(),(int)start.getBounds().getCenterY(),(int)end.produceBounds().getCenterX(),(int)end.produceBounds().getCenterY());
			return;
		}
		g.drawLine((int)start.getBounds().getCenterX(),(int)start.getBounds().getCenterY(),(int)endPoint.getX(),(int)endPoint.getY());
		g.setColor(oldColor);
	}
	public Line2D produceLineBounds(){
		Line2D line;
		if(end!=null){
			line = new Line2D.Double(start.getBounds().getCenterX(), start.getBounds().getCenterY(), end.produceBounds().getCenterX(), end.produceBounds().getCenterY());
			return line;
		}
		line = new Line2D.Double(start.getBounds().getCenterX(), start.getBounds().getCenterY(), endPoint.getX(),endPoint.getY());
		return line;
	}
	@Override
	public String toString() {
		return "Line";
	}
	
}
