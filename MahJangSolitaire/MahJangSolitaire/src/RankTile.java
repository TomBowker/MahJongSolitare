//The rank Tile class to determine the rank of a tile

public abstract class RankTile extends Tile {
	
	protected int rank;
	
	public void RankTile(int rank) {
		this.rank = rank;
	}
	
	public boolean matches(Object other) {
		if (!super.matches(other))
			return false;
		
		RankTile object = (RankTile)other;
		
		return rank == object.rank;
	}

}
