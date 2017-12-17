import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

//This is the base class for all tiles

public abstract class Tile extends JPanel {
	
	//coordinates for the tile
	private int x;
	private int y;
	private int z;
	
	//shadow for the tiles
	private ArrayList<Polygon> shadow;
	
	//variable to represent if a tile is selected
	private boolean selected;
	
	//Set gradient paint to the default 
	private GradientPaint currentColor = Config.defaultColor;
	
	//create a copy method
	public abstract Tile copy();
	
	//set the coordinates
	public void setCoordinates(int [] coordinates) {
		this.x = coordinates[0];
		this.y = coordinates[1];
		this.z = coordinates[2];		
	}
	
	//get the coordinates
	public int[] getCoordinates() {
		int[] coordinates = {x, y, z};
		return coordinates;		
	}
	
	//set the selected variable
	public void setSelected(boolean selected) {
		
		this.selected = selected;
	}
	
	public void paintComponent(Graphics graphics) {
		
		//set tile size
		setSize(Config.TILE_WIDTH, Config.TILE_HEIGHT);
		
		//create a 2D graphics and cast 
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		//Construct the tile each side at a time
		//bottom layer
		//Left Side
		Polygon leftBottom = new Polygon();
		leftBottom.addPoint(20, 10);
		leftBottom.addPoint(10, 30);
		leftBottom.addPoint(10, 90);
		leftBottom.addPoint(20, 70);
		graphics2D.setPaint(Color.decode("#7F6B1A"));
		graphics2D.fillPolygon(leftBottom);
		
		//Bottom side
		Polygon bottom = new Polygon();
		bottom.addPoint(15, 80);
		bottom.addPoint(10, 90);
		bottom.addPoint(80, 90);
		bottom.addPoint(85, 80);
		Color shadows = Color.decode("#7F6B1A");
		Color edge = Color.decode("#151204");
		graphics2D.setPaint(new GradientPaint(0,0,shadows,75,0,edge,true));
		graphics2D.fillPolygon(bottom);
		
		//Middle layer		
		//Left Side
		Polygon middleLeft = new Polygon();
		middleLeft.addPoint(20, 10);
		middleLeft.addPoint(15, 20);
		middleLeft.addPoint(15, 80);
		middleLeft.addPoint(20, 70);
		graphics2D.setPaint(Color.decode("#E1E0B7"));
		graphics2D.fillPolygon(middleLeft);
		
		//Bottom side
		Polygon middleBottom = new Polygon();
		middleBottom.addPoint(20, 70);
		middleBottom.addPoint(15, 80);
		middleBottom.addPoint(85, 80);
		middleBottom.addPoint(90, 70);
		shadows = Color.decode("#E1E0B7");
		edge = Color.decode("#B5A74A");
		graphics2D.setPaint(new GradientPaint(0,0,shadows,85,0,edge,true));
		graphics2D.fillPolygon(middleBottom);
		
		//Top side	
		graphics2D.setPaint(selected ? Config.clickedColor : Config.defaultColor);
		Rectangle2D top = new Rectangle2D.Double(20,10,70,60);
		graphics2D.fill(top);
		
		//make the shadow
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
		graphics2D.setComposite(alphaComposite);
		graphics2D.setColor(Color.BLACK);
		
		for (Polygon p : shadow) {
			graphics2D.fillPolygon(p);
		}
		
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		graphics2D.setComposite(alphaComposite); //this is the current alpha
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}//end paintComponent
	
	@Override 
	public Dimension getMinimumSize() { 
		return new Dimension(Config.TILE_WIDTH,Config.TILE_HEIGHT);
	}

    @Override 
    public Dimension getPreferredSize() { 
    	return getMinimumSize(); 
    }
    
    public void setShadow(ArrayList shadow) {
    	this.shadow = shadow;
    }
	
	//check to see if the tiles are matching
	public boolean matches(Object other) {
		if (this == other)
			return true;
		
		if (other == null)
			return false;
		
		if(getClass() != other.getClass())
			return false;
		
		Tile object = (Tile)other;
		
		return other == object;
	}
}
