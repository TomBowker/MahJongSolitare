import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

//AKA the Bird Tile

public class Bamboo1Tile extends PictureTile {
	
	private Image image;
	
	public Bamboo1Tile() {
		
		setToolTipText(toString());
		
		try {
			image = ImageIO.read(getClass().getResource("Images/Sparrow.png"));
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Bamboo 1";
	}
	
	public Bamboo1Tile copy() {
		return new Bamboo1Tile();
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		Image scaledImage = new ResizeImage().getImage(image, 50, 50);
		
		
		graphics2D.drawImage(scaledImage, 35, 15, null);
	}
}
