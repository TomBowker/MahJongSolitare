import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

//This creates the number Bamboo tiles and uses the bamboo class
//So each tile will correspond with its given number and prints 
//Bamboo shoots according to the number that the rank is given

public class BambooTile extends RankTile {
	
	//center coordinates
	protected int x = 55;
	protected int y = 40;
	
	public BambooTile(int rank) {
		this.rank = rank;
		setToolTipText(toString());
	}
	
	public BambooTile copy() {
		return new BambooTile(this.rank);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		//each rank corresponds to a tile and the arraylist will return a class of a tile
		if(rank == 2)
			arrayList = tileTwo();
		else if(rank == 3)
			arrayList = tileThree();
		else if(rank == 4)
			arrayList = tileFour();
		else if(rank == 5)
			arrayList = tileFive();
		else if(rank == 6)
			arrayList = tileSix();
		else if(rank == 7)
			arrayList = tileSeven();
		else if(rank == 8)
			arrayList = tileEight();
		else if(rank == 9)
			arrayList = tileNine();
		
		for(Bamboo b : arrayList) {
			b.drawShape(graphics2D);
		}
	}
	
	//each method help create a bamboo tile with its value
	public ArrayList<Bamboo> tileTwo() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x, y-13, Config.BLUE));
		arrayList.add(new Bamboo(x, y+13, Config.GREEN));		
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileThree() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x,    y-13, Config.BLUE));
		arrayList.add(new Bamboo(x-10, y+13, Config.GREEN));
		arrayList.add(new Bamboo(x+10, y+13, Config.GREEN));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileFour() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x-10, y-13, Config.BLUE));
		arrayList.add(new Bamboo(x+10, y-13, Config.GREEN));
		arrayList.add(new Bamboo(x-10, y+13, Config.GREEN));
		arrayList.add(new Bamboo(x+10, y+13, Config.BLUE));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileFive() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x-15, y-13, Config.GREEN));
		arrayList.add(new Bamboo(x+15, y-13, Config.BLUE));
		arrayList.add(new Bamboo(x-15, y+13, Config.BLUE));
		arrayList.add(new Bamboo(x+15, y+13, Config.GREEN));
		arrayList.add(new Bamboo(x,    y,    Config.RED));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileSix() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x-10, y-13, Config.GREEN));
		arrayList.add(new Bamboo(x,    y-13, Config.GREEN));
		arrayList.add(new Bamboo(x+10, y-13, Config.GREEN));
		arrayList.add(new Bamboo(x-10, y+13, Config.BLUE));
		arrayList.add(new Bamboo(x,    y+13, Config.BLUE));
		arrayList.add(new Bamboo(x+10, y+13, Config.BLUE));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileSeven() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x,    y-17, Config.RED));
		arrayList.add(new Bamboo(x-15, y,    Config.GREEN));
		arrayList.add(new Bamboo(x,    y,    Config.BLUE));
		arrayList.add(new Bamboo(x+15, y,    Config.GREEN));
		arrayList.add(new Bamboo(x-15, y+17, Config.GREEN));
		arrayList.add(new Bamboo(x,    y+17, Config.BLUE));
		arrayList.add(new Bamboo(x+15, y+17, Config.GREEN));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileEight() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x-19, y-17, Config.GREEN));
		arrayList.add(new Bamboo(x,    y-17, Config.GREEN));
		arrayList.add(new Bamboo(x+19, y-17, Config.GREEN));
		arrayList.add(new Bamboo(x-10, y,    Config.RED));
		arrayList.add(new Bamboo(x+10, y,    Config.RED));
		arrayList.add(new Bamboo(x-19, y+17, Config.BLUE));
		arrayList.add(new Bamboo(x,    y+17, Config.BLUE));
		arrayList.add(new Bamboo(x+19, y+17, Config.BLUE));
		
		return arrayList;
	}
	
	public ArrayList<Bamboo> tileNine() {
		ArrayList<Bamboo> arrayList = new ArrayList();
		
		arrayList.add(new Bamboo(x-19, y-17, Config.RED));
		arrayList.add(new Bamboo(x,    y-17, Config.BLUE));
		arrayList.add(new Bamboo(x+19, y-17, Config.GREEN));
		arrayList.add(new Bamboo(x-19, y,    Config.RED));
		arrayList.add(new Bamboo(x,    y,    Config.BLUE));
		arrayList.add(new Bamboo(x+19, y,    Config.GREEN));
		arrayList.add(new Bamboo(x-19, y+17, Config.RED));
		arrayList.add(new Bamboo(x,    y+17, Config.BLUE));
		arrayList.add(new Bamboo(x+19, y+17, Config.GREEN));
		
		return arrayList;
	}
	
	@Override
	public String toString() {
		return "Bamboo " + rank;
	}
}
