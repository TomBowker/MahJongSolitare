import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

//Using the picture tile, it creates a Tile with a season

public class SeasonTile extends PictureTile {
	
	Image image;
	String imageName;
	
	
	public SeasonTile(String name) {
		super.PictureTile(name);
		this.imageName = name;
		setToolTipText(toString());
		
		try {
			image = ImageIO.read(getClass().getResource("Images/" + name + ".png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public SeasonTile copy() {
		return new SeasonTile(this.imageName);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		Image scaledImage = new ResizeImage().getImage(image, 50, 50);
		
		graphics2D.drawImage(scaledImage, 35, 15, null);
	}
}
