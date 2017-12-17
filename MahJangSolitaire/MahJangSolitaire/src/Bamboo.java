import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

//This creates the shape of the bamboo shoots

public class Bamboo {
	
	//coordinates relative to the center of the tiles
	private int x;
	private int y;
	
	private Color color;
	
	public Bamboo(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void drawShape(Graphics2D graphics2D) {
		Polygon polygon = new Polygon();
        
        polygon.addPoint(x-3, y-9);
        polygon.addPoint(x+3, y-9);
        polygon.addPoint(x+4, y-8);
        polygon.addPoint(x+4, y-6);
        polygon.addPoint(x+3, y-6);
        polygon.addPoint(x+2, y-5);
        polygon.addPoint(x+2, y-2);
        polygon.addPoint(x+4, y+1);
        polygon.addPoint(x+4, y+3);
        polygon.addPoint(x+3, y+3);
        polygon.addPoint(x+2, y+4);
        polygon.addPoint(x+2, y+7);
        polygon.addPoint(x+4, y+9);
        polygon.addPoint(x+4, y+11);
        polygon.addPoint(x-4, y+11);
        polygon.addPoint(x-4, y+9);
        polygon.addPoint(x-2, y+7);
        polygon.addPoint(x-2, y+4);
        polygon.addPoint(x-3, y+3);
        polygon.addPoint(x-4, y+3);
        polygon.addPoint(x-4, y);
        polygon.addPoint(x-2, y-1);
        polygon.addPoint(x-2, y-6);
        polygon.addPoint(x-3, y-6);
        polygon.addPoint(x-4, y-7);
        polygon.addPoint(x-4, y-8);
        
        graphics2D.setColor(color);
        graphics2D.fillPolygon(polygon);
        graphics2D.setColor(Config.WHITE);
        graphics2D.drawLine(x, y-1, x, y-6);
        graphics2D.drawLine(x, y+3, x, y+9);        
	}
}
