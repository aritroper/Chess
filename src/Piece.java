import java.util.List;
import java.util.Stack;

public abstract class Piece {
	
	private boolean color;
	private int row;
	private int col;
	private char printable;
	
	public Piece (boolean color, int row, int col) {
	
		setColor(color);
		setPosition(row, col);
	}
	
	public boolean getColor() {
		
		return color;
	}
	
	public int getRow() {
		
		return row;
	}
	
	public int getCol() {
		
		return col;
	}
	
	public char getPrintable() {
		
		return printable;
	}
	
	public void setColor(boolean color) {
		
		this.color = color;
	}
	
	public void setPosition(int row, int col) {
		
		this.row = row;
		this.col = col;
	}
	
	public void setPrintable(char printable) {
		
		this.printable = printable;
	}

	public Stack<Pair> getPlayableMoves() {
		
		List<Pair> moves = new Stack<Pair>();
			
		for (int row = 0; row <= 7; row ++) {
			for (int col = 0; col <= 7; col ++) {
				
				moves.add(new Pair(row, col));
				
			}
		}
		
		return (Stack<Pair>) moves;
		
	}
	
	public abstract boolean checkMoveValid(Square square);
	
	public abstract Piece clone();
	
	public Stack<Pair> getMovePath(Square square)
	{
		Stack<Pair> s = new Stack<Pair>();
		return s;
	}

}
