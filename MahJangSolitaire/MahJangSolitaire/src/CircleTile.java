import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

//you guessed this creates the circle tile

public class CircleTile extends RankTile {
	
	public double x = 55.0;
	public double y = 40.0;
	
	public CircleTile(int rank) {
		this.rank = rank;
		setToolTipText(toString());
	}
	
	public CircleTile copy() {
		return new CircleTile(this.rank);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;
		ArrayList<Circle> arrayList = new ArrayList();
		
		//each rank corresponds to a tile and the arraylist will return a class of a tile
		if(rank == 1)
			arrayList = circleOne();
		else if(rank == 2)
			arrayList = circleTwo();
		else if(rank == 3)
			arrayList = circleThree();
		else if(rank == 4)
			arrayList = circleFour();
		else if(rank == 5)
			arrayList = circleFive();
		else if(rank == 6)
			arrayList = circleSix();
		else if(rank == 7)
			arrayList = circleSeven();
		else if(rank == 8)
			arrayList = circleEight();
		else if(rank == 9)
			arrayList = circleNine();
		
		for(Circle c : arrayList) {
			c.drawCircle(graphics2D);
		}
	}
	
	public ArrayList<Circle> circleOne() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double r = 27.0;
		arrayList.add(new Circle(x, y, r,    Config.GREEN));
		arrayList.add(new Circle(x, y, 15.0, Config.BLUE));
		
		int count = 10;
		int r2 = 3;
		
		for (int i = 0; i < count; i++) {
			double temp = 2* Math.PI * i / count;
			int x2 = (int) Math.round(x+(r-8) * Math.cos(temp));
			int y2 = (int) Math.round(y+(r-8) * Math.sin(temp));
			
			arrayList.add(new Circle(x2, y2, r2, Config.WHITE));
		}
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleTwo() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 13.0;
		double origin = 12.0;
		
		arrayList.add(new Circle(x, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x, y+origin, radius, Config.RED));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleThree() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 10.0;
		double origin = 16.0;
		
		arrayList.add(new Circle(x-origin, y-origin, radius, Config.BLUE));
		arrayList.add(new Circle(x,        y,        radius, Config.RED));
		arrayList.add(new Circle(x+origin, y+origin, radius, Config.GREEN));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleFour() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 12.0;
		double origin = 13.0;
		
		arrayList.add(new Circle(x-origin, y-origin, radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x-origin, y+origin, radius, Config.GREEN));
		arrayList.add(new Circle(x+origin, y+origin, radius, Config.BLUE));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleFive() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 8.0;
		double origin = 15.0;
		
		arrayList.add(new Circle(x-origin, y-origin, radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x-origin, y+origin, radius, Config.GREEN));
		arrayList.add(new Circle(x+origin, y+origin, radius, Config.BLUE));
		arrayList.add(new Circle(x,        y,        radius, Config.RED));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleSix() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 8.0;
		double origin = 17.0;
		
		arrayList.add(new Circle(x-origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x-origin, y,        radius, Config.RED));
		arrayList.add(new Circle(x-origin, y+origin, radius, Config.RED));
		arrayList.add(new Circle(x+origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x+origin, y,        radius, Config.RED));
		arrayList.add(new Circle(x+origin, y+origin, radius, Config.RED));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleSeven() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 7.0;
		double origin = 15.0;
		
		arrayList.add(new Circle(x-origin-3, y-origin-2, radius, Config.GREEN));
		arrayList.add(new Circle(x-origin,   y,          radius, Config.RED));
		arrayList.add(new Circle(x-origin,   y+origin,   radius, Config.RED));
		arrayList.add(new Circle(x,          y-origin,   radius, Config.GREEN));
		arrayList.add(new Circle(x+origin+3, y-origin+2, radius, Config.GREEN));
		arrayList.add(new Circle(x+origin,   y,          radius, Config.RED));
		arrayList.add(new Circle(x+origin,   y+origin,   radius, Config.RED));
		
		return arrayList;
	}
	
	public ArrayList<Circle> circleEight() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 6.0;
		double origin = 12.0;
		
		arrayList.add(new Circle(x-origin, y-7,  radius, Config.BLUE));
		arrayList.add(new Circle(x-origin, y-20, radius, Config.BLUE));
		arrayList.add(new Circle(x-origin, y+6,  radius, Config.BLUE));
		arrayList.add(new Circle(x-origin, y+19, radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y-7,  radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y-20, radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y+6,  radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y+19, radius, Config.BLUE));
		                                           
		return arrayList;
	}
	public ArrayList<Circle> circleNine() {
		ArrayList<Circle> arrayList = new ArrayList();
		
		double radius = 9.0;
		double origin = 19.0;
		
		arrayList.add(new Circle(x-origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x-origin, y,        radius, Config.RED));
		arrayList.add(new Circle(x-origin, y+origin, radius, Config.BLUE));
		arrayList.add(new Circle(x,        y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x,        y,        radius, Config.RED));
		arrayList.add(new Circle(x,        y+origin, radius, Config.BLUE));
		arrayList.add(new Circle(x+origin, y-origin, radius, Config.GREEN));
		arrayList.add(new Circle(x+origin, y,        radius, Config.RED));
		arrayList.add(new Circle(x+origin, y+origin, radius, Config.BLUE));
		
		return arrayList;
	}
	
	
	@Override
	public String toString() {
		return "Circle: " + rank;
	}
}
