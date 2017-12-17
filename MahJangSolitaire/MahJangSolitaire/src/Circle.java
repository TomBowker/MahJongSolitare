import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

//This draws the circles for the circle tiles

public class Circle {
	//declare the parts for a circle r = radius, x = center of x, y = center of y
	private double r;
	private double x;
	private double y;
	
	private Color color;
	
	public Circle(double x, double y, double r, Color color) {
		this.r = r;
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void drawCircle(Graphics2D graphics2D) {
		filledCircle(x+.5, y+.5, r,       Config.WHITE, graphics2D);
		filledCircle(x,    y,    r,       color,        graphics2D);
		filledCircle(x,    y,    r *.666, Config.WHITE, graphics2D);
		filledCircle(x,    y,    r*.45,   color,        graphics2D);
	}
	
	private void filledCircle(double x, double y, double r, Color color, Graphics2D graphics2D) {
		Shape shape = new Ellipse2D.Double(x-r, y-r, r*2, r*2);
		graphics2D.setPaint(color);
		graphics2D.fill(shape);		
	}
}
