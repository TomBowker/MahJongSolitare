import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

//This creates Chinese characters

public class CharacterTile extends Tile {
	
	protected char symbol;
	
	//as the name states these are the codes for the Chinese characters
	protected char[] chineseCharacter = {
	        '\u4E00',   //0 = 1
	        '\u4E8C',   //1 = 2
	        '\u4E09',   //2 = 3
	        '\u56DB',   //3 = 4
	        '\u4E94',   //4 = 5
	        '\u516D',   //5 = 6
	        '\u4E03',   //6 = 7
	        '\u516B',   //7 = 8
	        '\u4E5D',   //8 = 9
	        '\u5317',   //9 = North
	        '\u6771',   //10 = East
	        '\u897F',   //11 = West
	        '\u5357',   //12 = South
	        '\u4E2D',   //13 = Red
	        '\u767C',   //14 = Green
	};
	
	public CharacterTile(char symbol) {
		this.symbol = symbol;
		setToolTipText(toString());
	}
	
	public CharacterTile copy() {
		return new CharacterTile(this.symbol);
	}
	
	@Override
	public boolean matches(Object other){
		if (!super.matches(other))
			return false;
		
		CharacterTile object = (CharacterTile)other;
		
		return symbol == object.symbol;
	}
	
	@Override
	public final String toString() {
		return "Character: " + Character.toString(symbol);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		//draw smaller symbol
		graphics2D.setColor(Color.DARK_GRAY);
		graphics2D.drawString(Character.toString(symbol), 78, 24);
		
		//draw larger Chinese character
		if(symbol < 58) {
			paintNumber(graphics2D);
		}
		else {
			paintOther(graphics2D);
		}
	}
	
	private void paintNumber(Graphics2D graphics2D) {
		//Font
		graphics2D.setFont(new Font("KaiTi", Font.PLAIN, 24));
		
		//Paint Numbers
		graphics2D.setColor(Color.WHITE);
		graphics2D.drawString(Character.toString(chineseCharacter[charToIndex(symbol)]), 44,35);
		graphics2D.setColor(Color.BLACK);
		graphics2D.drawString(Character.toString(chineseCharacter[charToIndex(symbol)]), 43, 34);
		
		//paint Wan
		graphics2D.setColor(Color.decode("#AB3617"));
		graphics2D.drawString(Character.toString(chineseCharacter[14]), 43, 60);
	}
	
	private void paintOther(Graphics2D graphics2D) {
		//Font
		graphics2D.setFont(new Font("KaiTi", Font.PLAIN, 40));
		
		//paint symbols
		graphics2D.setColor(symbol == 'F' || symbol == 'C' ? Color.BLACK : Color.WHITE);
		graphics2D.drawString(Character.toString(chineseCharacter[charToIndex(symbol)]), 36, 56);
		graphics2D.setColor(symbol == 'F' ? Config.GREEN : symbol == 'C' ? Config.RED : Color.BLACK);
		graphics2D.drawString(Character.toString(chineseCharacter[charToIndex(symbol)]), 35, 56);		
	}
	
	private int charToIndex(char character) {
		if(character == 'N')
			return 9;
		else if(character == 'E')
			return 10;
		else if(character == 'W')
			return 11;
		else if(character == 'S')
			return 12;
		else if(character == 'C')
			return 13;
		else if(character == 'F')
			return 14;
		
		return Integer.parseInt(Character.toString(character)) - 1;
	}
	
}
