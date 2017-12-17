import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

//This create the flower tiles

class FlowerTile extends PictureTile {
	
	Image image;
	String imageName;
	
	public FlowerTile(String name) {
		super.PictureTile(name);
		imageName = name;
		setToolTipText(toString());
		
		try {
			image = ImageIO.read(getClass().getResource("Images/"+ name + ".png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public FlowerTile copy() {
		return new FlowerTile(this.imageName);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		Image scaledImage = new ResizeImage().getImage(image, 50, 50);
		
		graphics2D.drawImage(scaledImage, 35, 15, null);
	}
}
