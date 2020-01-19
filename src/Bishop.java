import java.util.Stack;

public class Bishop extends Piece {
	
	private char bPrintable = '♗';
	private char wPrintable = '♝';
	
	public Bishop(boolean color, int x, int y) {
		
		super(color, x, y);
		
		// Choose the display piece
		if(color) {
			super.setPrintable(wPrintable);
		}
		else {
			super.setPrintable(bPrintable);
		}
	}
	
	public boolean checkMoveValid(Square to) {
		
		int diffRow = Math.abs(getRow() - to.getRow());
		int diffCol = Math.abs(getCol() - to.getCol());
		
		if (diffRow == diffCol) {
			if (to.getEmptiness()) {
				return true;
			}
			else {
				return (to.getPiece().getColor() != getColor());
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public Stack<Pair> getMovePath(Square to) {
		
		Stack<Pair> s = new Stack<Pair>();
		
		int col;
		int colConst;
		int toRow = to.getRow();
		int fromRow = getRow();
		
		if(Math.min(toRow, fromRow) == toRow) {
			colConst = (to.getCol() < getCol())? 1 : -1;
			col = to.getCol() + colConst;
		} else {
			colConst = (getCol() < to.getCol())? 1 : -1;
			col = getCol() + colConst;
		}
		
		for (int i = Math.min(toRow, fromRow) + 1; i < Math.max(toRow, fromRow); i++) {
			Pair p = new Pair(i, col);
			s.push(p);
			col += colConst;	
		}
		
		return s;
	}
	
	public Piece clone() {
		return new Bishop(getColor(), getRow(), getCol());
	}
}
