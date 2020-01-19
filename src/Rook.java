import java.util.Stack;

public class Rook extends Piece {
	
	private char bPrintable = '♖';
	private char wPrintable = '♜';
	
	public Rook(boolean color, int x, int y) {
		
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
		if ((getRow() == to.getRow() && getCol() != to.getCol()) || (getCol() == to.getCol() && getRow() != to.getRow())) {
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
		
		if (to.getCol() == getCol()) {
			int toRow = to.getRow();
			int fromRow = getRow();
			for (int i = Math.min(toRow, fromRow) + 1; i < Math.max(toRow, fromRow); i++) {
				Pair p = new Pair(i, getCol());
				s.push(p);
			}
		}
		
		else {
			int toCol = to.getCol();
			int fromCol = getCol();
			for (int i = Math.min(toCol, fromCol) + 1; i < Math.max(toCol, fromCol); i++) {
				Pair p = new Pair(getRow(), i);
				s.push(p);
			}
		}
		
		return s;
	}
	
	public Piece clone() {
		return new Rook(getColor(), getRow(), getCol());
	}
}
