public class Knight extends Piece {
	
	private char bPrintable = '♘';
	private char wPrintable = '♞';
	
	public Knight(boolean color, int x, int y) {
		
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
		
		if((diffRow == 1 && diffCol == 2) || (diffRow == 2 && diffCol == 1)) {
			
			if(to.getEmptiness()) {
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
	
	public Piece clone() {
		return new Knight(getColor(), getRow(), getCol());
	}

}
