import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//This is a general gameboard that can be reused for other games
//ideally. But it will be used for Mahjong

public class GameBoard extends JPanel {
	
	//Constant variable to control the board dimension and total tiles
	//The names for dimensions are X = Abscissa, Y = Ordinate, and Z = Applicate
	//now why the names? well I wanted to find the proper names for the x, y, and z axis
	//since using horizontal and vertical don't work here
	private final int ABSCISSA = 15;
	private final int ORDINATE = 8;
	private final int APPLICATE = 5;
	private final int TOTAL_TILES = 144;
	
	//These global variables will set up the board and give us access to some of the
	//games features such as redo, undo and others
	
	//conut the cleared tiles and intialized at 0, becuase we haven't clear tiles yet
	private int countTilesCleared = 0;
	
	//is the game being played in tournament mode
	private boolean tournamentMode;
	
	//a List to hold all the tiles
	private ArrayList tileList = new ArrayList();
	
	//using the Container class create an array to simulate the game board
	private Container[][][] theBoard = new Container[APPLICATE][ORDINATE][ABSCISSA];
	
	//This variable will hold the tiles when they have been selected using the Container
	private Container tileBin;
	
	//these two variables will hold the background image and a dragon image
	private Image backgroundImage;
	private Image theDragonImage; //cough** girl with the dragon tatoo
	
	//This List will keep track of the tiles that have been removed by using the Container class
	private ArrayList<Container> tilesRemoved = new ArrayList<Container>();
	
	//This List will keep track of the tiles that can be redone using Container class
	private ArrayList<Container> tilesRedo = new ArrayList<Container>();
	
	//This will connect this class to the game engine
	private Play playgame;
	
	//If we use a three dimensional array to layout a map of the
	//board, we could use it to read and layout the tiles efficiently
	//a boolean array of 1's and 0's should do the trick
	//This array will represent the map at each layer
	//this was an idea I got online as well from a former assignment in my CS2130 class
	//While learning about binary Matrices, pretty fun stuff here. In any case it was a 
	//great suggestion
	//0 == base layer
	//1 == second layer
	//2 == third layer
	//3 == fourth layer
	//4 == top layer
	private int[][][] map = {  
			{   
				{0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
		        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
		        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
		        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		        {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
		        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
		        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
		        {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0}
	        },
		    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	        },
		    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
		        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
		        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
		        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	        },
		    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	        },
		    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	        },
	     };
	
	//Construct the game board
	public GameBoard(Play playgame, int seed, boolean tournamentMode) {
		this.playgame = playgame;
		this.tournamentMode = tournamentMode;
		
		//set the size using the dimensions of the config fill
		setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		setLayout(null);
		
		//declare the methods in Gameboard
		//this will build the mahjongdeck
		buildMahJongDeck();
		
		//this fills the game board
		fillGameBoard(seed);
		
		//this draws the tiles
		drawTiles();
		
		//add the shadow to tiles
		tileShadow();
		
		//create a new holding bin using the Container class and validate it
		tileBin = new Container(false);
		
		//have the game board and tiles appear
		setVisible(true);
	}
	
	//set paint component
	@Override 
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		//Get background images
		try {
			theDragonImage = ImageIO.read(getClass().getResource("Images/dragon_bg.png"));
            backgroundImage = ImageIO.read(getClass().getResource("Images/cherries.jpg"));
            //backgroundImage = ImageIO.read(getClass().getResource("Images/wood_bg.jpg"));
        }
		catch(IOException e){
            //e.printStackTrace();
        }
		
		//texture background
		Rectangle2D rec = new Rectangle2D.Double(0,0,350,337);
		graphics2D.setPaint(new TexturePaint((BufferedImage)backgroundImage, rec));
		
		//Paint background
		Rectangle2D background = new Rectangle2D.Double(0,0,Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		graphics2D.fill(background);
		
		//Set opacity
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		graphics2D.setComposite(composite);
	        //draw dragon
	        graphics2D.drawImage(theDragonImage, 200, 20, null);
	        //reset opacity for tile painting
	        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		graphics2D.setComposite(composite); // Set current alpha
	}
	
	//get removed tiles
	public ArrayList getTilesRemoved() {
		ArrayList copyRemovedTiles = new ArrayList();
		for(Container c : tilesRemoved) {
			copyRemovedTiles.add(c.tile.copy());
		}
		return copyRemovedTiles;
	}

	private void tileShadow() {
		//for every point on the board x,y,z add a shadow when applicable
		for(int z = 0; z < APPLICATE; z++) {
			for(int y = 0; y < ORDINATE; y++) {
				for(int x = 0; x < ABSCISSA; x++) {
					if(theBoard[z][y][x].validateObject()) {
						theBoard[z][y][x].tile.setShadow(getTileShadow(x,y,z));
					}//end if statement
				}//end x
			}//end y
		}//end z
	}//end tileShadow

	//these functions help me check for surrounding tiles are there, I found this online as well to help me out
    private int yUP(int y){ return (y == ORDINATE - 1) ? y : y + 1;}
    private int yDOWN(int y){ return (y == 0) ? 0 : y - 1;}
    private int xUP(int x){ return (x == ABSCISSA - 1) ? x : x + 1;}
    private int xDOWN(int x){ return (x == 0) ? 0 : x - 1;}
	private int zUP(int z){ return (z == APPLICATE-1) ? z : z +1; }

 	private boolean top(int x, int y, int z) { return (z == APPLICATE-1) ? true : ! theBoard[zUP(z)][y][x].validateObject(); }
    private boolean north(int x, int y, int z) { return ! theBoard[z][yDOWN(y)][x].validateObject(); }
    private boolean northeast(int x, int y, int z) { return ! theBoard[z][yDOWN(y)][xUP(x)].validateObject(); }
    private boolean northwest(int x, int y, int z) { return ! theBoard[z][yDOWN(y)][xDOWN(x)].validateObject(); }
    private boolean east(int x, int y, int z) { return ! theBoard[z][y][xUP(x)].validateObject(); }
    private boolean west(int x, int y, int z) { return ! theBoard[z][y][xDOWN(x)].validateObject(); }
    private boolean south(int x, int y, int z) { return ! theBoard[z][yUP(y)][x].validateObject(); }
    private boolean southwest(int x, int y, int z) { return ! theBoard[z][yUP(y)][xDOWN(x)].validateObject(); }
    private boolean southeast(int x, int y, int z) { return ! theBoard[z][yUP(y)][xUP(x)].validateObject(); }


	private ArrayList<Polygon> getTileShadow(int x, int y, int z) {
		//create a shadow for the tiles
		//reference the slide show from class for tile positions
		ArrayList<Polygon> tileShadow = new ArrayList<Polygon>();
		
		//position the shadow Northeast
		//north side
		if(north(x,y,z) && northeast(x,y,z) || (z == 0 && y == 0)) {
			Polygon polygon = new Polygon();
			
			polygon.addPoint(20, 10);
			polygon.addPoint(90, 10);
			polygon.addPoint(95, 00);
			polygon.addPoint(25, 00);
			
			tileShadow.add(polygon);
		}
		
		//East side
		if(east(x,y,z) && northeast(x,y,z) || (z ==0 && y == 3 && x == ABSCISSA - 1)) {
			Polygon polygon = new Polygon();
			
			polygon.addPoint(90, 10);
			polygon.addPoint(90, 70);
			polygon.addPoint(95, 60);
			polygon.addPoint(95, 00);
			
			tileShadow.add(polygon);
		}
		
		//north to the right
		if(north(x,y,z) && !northeast(x,y,z)) {
			Polygon polygon = new Polygon();
			
			polygon.addPoint(20, 10);
			polygon.addPoint(80, 10);
			polygon.addPoint(80, 00);
			polygon.addPoint(25, 00);
			
			tileShadow.add(polygon);
		}
		
		//east to the top
		if(east(x,y,z) && !northeast(x,y,z) && !(x == 12 && y == 4)) {
			Polygon polygon = new Polygon();
			
			polygon.addPoint(90, 30);
			polygon.addPoint(90, 70);
			polygon.addPoint(95, 60);
			polygon.addPoint(95, 30);
			
			tileShadow.add(polygon);
		}
		
		//east top half
		if(x == 12 && y == 3) {
			if(!east(x,y,z)) {
				Polygon polygon = new Polygon();
				
				polygon.addPoint(90, 10);
				polygon.addPoint(90, 35);
				polygon.addPoint(95, 29);
				polygon.addPoint(95, 00);
				
				tileShadow.add(polygon);
			}
		}
		
		//east bottom half
		if(x == 12 && y == 4) {
			if(!east(x,y,z)) {
				Polygon polygon = new Polygon();
				
				polygon.addPoint(70, 67);
				polygon.addPoint(70, 70);
				polygon.addPoint(80, 50);
				polygon.addPoint(80, 67);
				
				tileShadow.add(polygon);
			}
		}
		return tileShadow;
	}

	//this one I struggled with so I got some help and suggestion on this one
	private void drawTiles() {
		//using the z order to organize the tiles
		int zOrder = 0;
		
		for(int z = APPLICATE - 1; z >= 0; z--) {
			for(int y = ORDINATE - 1; y >= 0; y--) {
				for(int x = 0; x < ABSCISSA; x++) {
					if(theBoard[z][y][x].validateObject()) {
						final Tile tile = theBoard[z][y][x].getTile();
						
						//handle all the special cases
						if(x == 0 && y == 4 & z == 0) {
							//Left tile
							tile.setBounds(x * (Config.TILE_WIDTH - 30) + (z * 10) + 70, y * (Config.TILE_HEIGHT - 35) - (z * 20) - 30 + 20, Config.TILE_WIDTH, Config.TILE_HEIGHT);
						}
						else if(x == 14 && y == 3 & z == 0) {
							//Right tile
							tile.setBounds(x * (Config.TILE_WIDTH - 30) + (z * 10) + 70, y * (Config.TILE_HEIGHT - 35) - (z * 20) + 30 + 20, Config.TILE_WIDTH, Config.TILE_HEIGHT);
						}
						else if(x == 13 && y == 3 & z == 0) {
							//next right tile
							tile.setBounds(x * (Config.TILE_WIDTH - 30) + (z * 10) + 70, y * (Config.TILE_HEIGHT - 35) - (z * 20) + 30 + 20, Config.TILE_WIDTH, Config.TILE_HEIGHT);
						}
						else if(x == 6 && y == 3 & z == 4) {
							//top
							tile.setBounds(x * (Config.TILE_WIDTH - 30) + (z * 10) + (35) + 70, y * (Config.TILE_HEIGHT - 35) - (z * 20) + (40) + 20, Config.TILE_WIDTH, Config.TILE_HEIGHT);
						}
						else {
							tile.setBounds(x * (Config.TILE_WIDTH - 30) + (z * 10) + 70, y * (Config.TILE_HEIGHT - 35) - (z * 20) + 20, Config.TILE_WIDTH, Config.TILE_HEIGHT);
						}
						
						setComponentZOrder(tile, zOrder);
						theBoard[z][y][x].setZOrder(zOrder);
						zOrder++;
						
						final int[] coordinates = {x,y,z};
						tile.setCoordinates(coordinates);
						
						tile.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								if(isSelected(coordinates[0], coordinates[1], coordinates[2])) {
									
									//if this is the first tile save for future use
									if(!tileBin.validateObject()) {
										tileBin = theBoard[coordinates[2]][coordinates[1]][coordinates[0]];
										theBoard[coordinates[2]][coordinates[1]][coordinates[0]].tile.setSelected(true);
										repaint();
									} else {
										//Second tile
										//match tiles and remove or reset if not matching
										if((tileBin.tile.matches(theBoard[coordinates[2]][coordinates[1]][coordinates[0]].tile) && (tileBin != theBoard[coordinates[2]][coordinates[1]][coordinates[0]]))) {
											//if they match put tiles in removed tiles list
											tilesRemoved.add(tileBin);
											tilesRemoved.add(theBoard[coordinates[2]][coordinates[1]][coordinates[0]]);
											
											//remove tiles from map
											theBoard[coordinates[2]][coordinates[1]][coordinates[0]] = new Container(false);
											theBoard[tileBin.getz()][tileBin.gety()][tileBin.getx()] = new Container(false);
											
											//set to disappear
											tile.setVisible(false);
											tileBin.tile.setVisible(false);
											countTilesCleared += 2;
											tileShadow();
											
											//clear redo list
											for(Container c : tilesRedo) {
												tilesRedo.remove(c);
											}
											
											if(tournamentMode) {
												playgame.gameTitle("Mah Jong --- Tournament Mode --- Tiles Removed: " + countTilesCleared);
											}
											
											//winning the game
											if(countTilesCleared == TOTAL_TILES) {
												gameWon();
											}
										}
										
										tileBin.tile.setSelected(false);
										repaint();
										tileBin = new Container(false);
									}
								}
							}
						});
						add(tile);
					}
				}
			}
		}	
	}//end draw tiles

	private void fillGameBoard(int seed) {
		//use the random generator to randomly place tiles
		Random random = new Random(seed);
		
		//fill the 3D array
		for(int z = 0; z < APPLICATE; z++) {
			for(int y = 0; y < ORDINATE; y++) {
				for(int x = 0; x < ABSCISSA; x++) {
					//move along the array and place the tiles
					if(map[z][y][x] == 1) {
						theBoard[z][y][x] = new Container(
								tileList.remove(random.nextInt(tileList.size())), x, y, z
								);
					}
					else if(map[z][y][x] == 0)  {
						//place an empty tile
						theBoard[z][y][x] = new Container(false);
					}//end if statement
				}//end x
			}//end y
		}//end z	
	}

	private void buildMahJongDeck() {
		//each of the tiles are going to be build piece by piece
		
		//Character tiles
		for(int i = 1; i < 10; i++) {
			//four copies of each tile
			for(int j = 0; j < 4; j++) {
				tileList.add(new CharacterTile(Character.forDigit(i, 10)));
			}
		}
		
		//Circle Tiles
		for(int i = 1; i < 10; i++) {
			//four copies of each tile
			for(int j = 0; j < 4; j++) {
				tileList.add(new CircleTile(i));
			}
		}
		
		//Bamboo tiles
		for(int i = 2; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				tileList.add(new BambooTile(i));
			}
		}
		
		//Bamboo1 tiles
		for(int i = 0; i < 4; i++) {
			tileList.add(new Bamboo1Tile());
		}
		
		//Character N, E, W, S, C, F and WhiteDragon Tiles
		for(int i=0; i < 4; i++) {
            tileList.add(new CharacterTile('N'));
            tileList.add(new CharacterTile('E'));
            tileList.add(new CharacterTile('W'));
            tileList.add(new CharacterTile('S'));
            tileList.add(new CharacterTile('C'));
            tileList.add(new CharacterTile('F'));
            tileList.add(new WhiteDragonTile());
		}
		
		//Flower tiles
		tileList.add(new FlowerTile("Chrysanthemum"));
		tileList.add(new FlowerTile("Orchid"));
		tileList.add(new FlowerTile("Plum"));
		tileList.add(new FlowerTile("Bamboo"));
		
		//Season Tiles
		tileList.add(new SeasonTile("Spring"));
		tileList.add(new SeasonTile("Summer"));
		tileList.add(new SeasonTile("Fall"));
		tileList.add(new SeasonTile("Winter"));
	}	
	
	private boolean isSelected(int x, int y, int z){
        if(Config.DEBUG) { 
        	return true; 
        	}
        if(x==0 || x== ABSCISSA - 1) {
        	return true;
        	}  // special side cases
        return ((west(x,y,z) || east(x,y,z)) && top(x,y,z));
	}
	
	//undo
	public boolean getUndo() {
		return !tilesRemoved.isEmpty();
	}
	
	public void undo() {
		Container container1 = tilesRemoved.remove(tilesRemoved.size() - 1);
		Container container2 = tilesRemoved.remove(tilesRemoved.size() - 1);
		
		tilesRedo.add(container1);
		tilesRedo.add(container2);
		
		//place on board
		theBoard[container1.z][container1.y][container1.x] = container1;
		theBoard[container2.z][container2.y][container2.x] = container2;
		
		//set visible
		container1.tile.setVisible(true);
		container2.tile.setVisible(true);
		
		//update count
		countTilesCleared -= 2;
		
		//update shadows
		tileShadow();
	}
	
	//redo
	public boolean getRedo() {
		return !tilesRedo.isEmpty();
	}
	
	public void redo() {
		Container container1 = tilesRemoved.remove(tilesRemoved.size() - 1);
		Container container2 = tilesRemoved.remove(tilesRemoved.size() - 1);
		
		tilesRedo.add(container1);
		tilesRedo.add(container2);
		
		//place on board
		theBoard[container1.z][container1.y][container1.x] = new Container(false);
		theBoard[container2.z][container2.y][container2.x] = new Container(false);
		
		//set visible
		container1.tile.setVisible(false);
		container2.tile.setVisible(false);
		
		//update count
		countTilesCleared += 2;
		
		//update shadows
		tileShadow();
	}
	
	//game event when won
	public void gameWon() {
//		Fireworks f = new Fireworks(this);
//        f.setSound(playgame.sound);
//        f.setExplosions(10, 1000);
//        f.fire();
	}
}
