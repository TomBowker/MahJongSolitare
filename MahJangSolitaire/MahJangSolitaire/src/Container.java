//This is a base class for holding items as they are played
//This will keep track of the Z-Order
//The goal of this class is to reuse the code

public class Container {
	
	//use an instance of Tile to work with
	public Tile tile;
	
	//we need to keep track whether its the correct object
	private boolean validateObject;
	
	//Keep track of location and Z-Order 
	int x;
	int y;
	int z;
	int zOrder;
	
	//Construct Container
	public Container(Object tile, int x, int y, int z) {
		this.tile = (Tile)tile;
		this.x = x;
		this.y = y;
		this.z = z;
		validateObject = true;
	}
	
	//construct Container validating the object
	public Container(boolean bool) {
		validateObject = bool;
	}
	
	//set Z-Order
	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
	}
	
	//get x, y, and z for location, also get a instance of Tile
	public Tile getTile() {
		return tile;
	}
	
	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}
	
	public int getz() {
		return z;
	}
	
	//lastly get a validated object
	public boolean validateObject() {
		return validateObject;
	}
}
