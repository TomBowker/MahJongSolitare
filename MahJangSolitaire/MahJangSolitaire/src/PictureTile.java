//This is the pictureTile class 

public abstract class PictureTile extends Tile { 
	
	private String name;
	
//	public PictureTile(String name) {
//		this.name = name;
//	}
	public void PictureTile(String name) {
		this.name = name;
		
	}
	
	public String toString() {
		return name;
	}
}
