import java.util.Stack;

public class Queen extends Piece {

	private char bPrintable = '♕';
	private char wPrintable = '♛';

	public Queen(boolean color, int x, int y) {

		super(color, x, y);

		// Choose the display piece
		if (color) {
			super.setPrintable(wPrintable);
		} else {
			super.setPrintable(bPrintable);
		}
	}

	public boolean checkMoveValid(Square to) {

		Rook r = new Rook(getColor(), getRow(), getCol());
		Bishop b = new Bishop(getColor(), getRow(), getCol());
		return r.checkMoveValid(to) || b.checkMoveValid(to);
	}

	@Override
	public Stack<Pair> getMovePath(Square to) {

		Rook r = new Rook(getColor(), getRow(), getCol());
		Bishop b = new Bishop(getColor(), getRow(), getCol());

		if (r.checkMoveValid(to)) {
			return r.getMovePath(to);
		} else {
			return b.getMovePath(to);
		}
	}

	public Piece clone() {
		return new Queen(getColor(), getRow(), getCol());
	}

}
