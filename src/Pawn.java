import java.util.Stack;

public class Pawn extends Piece {
	
	private char bPrintable = '♙';
	private char wPrintable = '♟';
	
	public Pawn(boolean color, int row, int col) {
		
		super(color, row, col);
		
		// Choose the display piece
		if (color) {
			super.setPrintable(wPrintable);
		}
		else {
			super.setPrintable(bPrintable);
		}
	}
	
	public boolean checkMoveValid(Square to) {
		
			int firstRow = (getRow() == 1 && !getColor()) || (getRow() == 6 && getColor())? 1 : 0;
			int diffRow = getRow() - to.getRow();
			
			if (getColor() && (diffRow <= (1 + firstRow) && (diffRow > 0)) && (to.getCol() == getCol())) {
				return to.getEmptiness();
			}
			
			else if (!getColor() && (diffRow >= (-1 - firstRow) && (diffRow < 0)) && (to.getCol() == getCol())) {
				return to.getEmptiness();
			}
			
			else if (getColor() && (to.getRow() == getRow() - 1) && 
				    (to.getCol() == getCol() + 1 || to.getCol() == getCol() - 1)) {
				if (to.getEmptiness()) {
					return false;
				}
				else {
					return (to.getPiece().getColor() != getColor());
				}
			}
			
			else if  (!getColor() && (to.getRow() == getRow() + 1) && 
				     (to.getCol() == getCol() + 1 || to.getCol() == getCol() - 1)) {
				if (to.getEmptiness()) {
					return false;
				}
				else {
					return (to.getPiece().getColor() != getColor());
				}
			}
			
			else {
				return false;
			}
	}
	
	public Stack<Pair> getMovePath(Square to) {
		
		Stack<Pair> s = new Stack<Pair>();
		
		int toRow = to.getRow();
		int fromRow = getRow();
		for (int i = Math.min(toRow, fromRow) + 1; i < Math.max(toRow, fromRow); i++) {
			Pair p = new Pair(i, getCol());
			s.push(p);
		}
		
		return s;
	}
	
	public Piece clone() {
		return new Pawn(getColor(), getRow(), getCol());
	}
}
