import java.awt.Color;
import java.awt.GradientPaint;

//This class contains basic info on the colors, tile sizes,
//window sizes and debug flag
//Someone suggested this idea for to control the sizes and colors

public final class Config {
	
	//These will make sure that the colors do not get alter and is set in stone
	public static final Color BLUE  = Color.decode("#2753A5");
	public static final Color RED   = Color.decode("#E0491F");
	public static final Color GREEN = Color.decode("#467D36");
	public static final Color WHITE = Color.WHITE;

	//set the sizes 
	public static final int TILE_HEIGHT = 95;
	public static final int TILE_WIDTH = 100;
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 1200;
	
	//set the gradient shade #EEF4A6
	public static final GradientPaint defaultColor = new GradientPaint(20,85,Color.decode("#F8F6ED"),85,0,Color.decode("#987F1B"),true);
	public static final GradientPaint clickedColor = new GradientPaint(20,85,Color.decode("#E1F50A"),85,0,Color.decode("#CAA202"),true);
	
	//set boolean Debug to false
	public static final boolean DEBUG = false;
}
