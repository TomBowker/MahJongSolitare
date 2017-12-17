import java.awt.*;

//This makes the classic White dragon tile

public class WhiteDragonTile extends Tile {
	
	public WhiteDragonTile() {
		setToolTipText(toString());
	}
	
	public WhiteDragonTile copy() {
		return new WhiteDragonTile();
	}
	
	@Override
	public final String toString() {
		return "White Dragon";
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		//BasicStroke(width, end cap, join style, miter limit, dash pattern, dash phase
		Stroke stroke = new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {6.0f,15.0f}, 1.0f);
		
		//set stroke
		graphics2D.setStroke(stroke);
		
		//set paint
		graphics2D.setPaint(Config.BLUE);
		
		//create a square
		Rectangle rectangle = new Rectangle(40,18,30,45);
		graphics2D.draw(rectangle);
		
		//set new stroke
		graphics2D.setStroke(new BasicStroke());
		
		//create a larger square
		rectangle = new Rectangle(38,16,34,49);
		graphics2D.draw(rectangle);
		
		//create a smaller square
		rectangle = new Rectangle(42,20,26,41);
	}
}
