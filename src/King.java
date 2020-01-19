public class King extends Piece {
	
	private char bPrintable = '♔';
	private char wPrintable = '♚';
	
	public King(boolean color, int x, int y) {
		
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
		int diffCol = Math.abs(to.getCol() - getCol());
		int diffRow = Math.abs(to.getRow() - getRow());
		
		if(diffCol <= 1 && diffRow <= 1) {
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
		return new King(getColor(), getRow(), getCol());
	}
}
