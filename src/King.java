import java.util.Stack;

public class King extends Piece {

	private char bPrintable = '♔';
	private char wPrintable = '♚';

	public King(boolean color, int x, int y) {

		super(color, x, y);

		// Choose the display piece
		if (color) {
			super.setPrintable(wPrintable);
		} else {
			super.setPrintable(bPrintable);
		}
	}

	public boolean checkMoveValid(Square to) {
		int diffCol = Math.abs(to.getCol() - getCol());
		int diffRow = Math.abs(to.getRow() - getRow());

		if (diffCol <= 1 && diffRow <= 1 || (to.getRow() == 7 * (getColor() ? 1 : 0) && to.getCol() == 6)
				|| (to.getRow() == 7 * (getColor() ? 1 : 0) && to.getCol() == 2)) {
			if (to.getEmptiness()) {
				return true;
			} else {
				return (to.getPiece().getColor() != getColor());
			}
		} else {
			return false;
		}
	}

	@Override
	public Stack<Pair> getMovePath(Square to) {

		Stack<Pair> s = new Stack<Pair>();

		if (to.getRow() == getRow()) {

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
		return new King(getColor(), getRow(), getCol());
	}
}
