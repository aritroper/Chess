import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Board {

	Square[][] board = new Square[8][8];
	Player white;
	Player black;

	public Board(Player white, Player black) {

		setWhitePlayer(white);
		setBlackPlayer(black);

		// Empty Squares
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Square(Optional.empty(), i, j);
			}
		}

		// Filled Squares
		for (Piece p : white.getPieces()) {
			board[p.getRow()][p.getCol()] = new Square(Optional.of(p), p.getRow(), p.getCol());
		}

		for (Piece p : black.getPieces()) {
			board[p.getRow()][p.getCol()] = new Square(Optional.of(p), p.getRow(), p.getCol());
		}

	}

	public void setWhitePlayer(Player white) {

		this.white = white;
	}

	public void setBlackPlayer(Player black) {

		this.black = black;
	}

	public String printBoard() {

		String boardPrint = "    A  B  C  D  E  F  G  H \n";

		for (int i = 0; i < 8; i++) {
			boardPrint += " " + (8 - i) + " ";

			for (int j = 0; j < 8; j++) {
				boardPrint += "[" + board[i][j].toString() + "]";
			}

			boardPrint += " " + (8 - i) + "\n";
		}

		return boardPrint += "    A  B  C  D  E  F  G  H";
	}

	public String printBoardBlack() {

		String boardPrint = "    H  G  F  E  D  C  B  A \n";

		for (int i = 0; i < 8; i++) {
			boardPrint += " " + (i + 1) + " ";

			for (int j = 0; j < 8; j++) {
				boardPrint += "[" + board[7 - i][7 - j].toString() + "]";
			}

			boardPrint += " " + (i + 1) + "\n";
		}

		return boardPrint += "    H  G  F  E  D  C  B  A";
	}

	public Player getWhitePlayer() {

		return white;
	}

	public Player getBlackPlayer() {

		return black;
	}

	public Player getPlayerIn() {
		return white.getTurn() ? white : black;
	}

	public Player getPlayerOut() {
		return white.getTurn() ? black : white;
	}

	public Square getSquare(int row, int col) {

		return board[row][col];
	}

	public void makeMove(Square from, Square to) {

		Piece p = from.getPiece();

		from.removePiece();

		if (!to.getEmptiness()) {
			if (to.getPiece() != getPlayerOut().getKing()) {

				getPlayerOut().removePiece(to.getPiece());

			}
		}

		// Castling

		if (p.getClass() == King.class) {

			if (to.getRow() == 0 || to.getRow() == 7) {

				int row = to.getRow();

				if (to.getCol() == 6) {

					Square rookSquare = getSquare(row, 7);
					getSquare(row, 5).setPiece(rookSquare.getPiece());
					rookSquare.removePiece();
				}

				else if (to.getCol() == 2) {

					Square rookSquare = getSquare(row, 0);
					getSquare(row, 3).setPiece(rookSquare.getPiece());
					rookSquare.removePiece();
				}
			}

			getPlayerIn().setCanCastleLeft(false);
			getPlayerIn().setCanCastleRight(false);
		}

		if (p.getClass() == Rook.class) {

			Rook r = (Rook) p;

			if (r.getIsLeftRook()) {
				getPlayerIn().setCanCastleLeft(false);
			} else {
				getPlayerIn().setCanCastleRight(false);
			}
		}

		to.setPiece(p);

		getPlayerOut().setInCheck(inCheck(getPlayerOut(), getPlayerIn()));
		getPlayerIn().setInCheck(inCheck(getPlayerIn(), getPlayerOut()));

		white.switchTurn();
		black.switchTurn();
	}

	public boolean checkSquareEmpty(Square square) {

		return square.getEmptiness();
	}

	public boolean checkPath(Stack<Pair> path) {

		if (path.isEmpty()) {
			return true;
		} else {
			Pair pair = path.pop();
			Square square = getSquare(pair.getRow(), pair.getCol());

			// Square is empty
			if (checkSquareEmpty(square)) {
				return checkPath(path);
			} else {
				return false;
			}
		}
	}

	public Stack<Pair> getPlayableMoves(Piece piece) {

		List<Pair> moves = piece.getPlayableMoves();
		Square current = getSquare(piece.getRow(), piece.getCol());
		moves.removeIf(p -> !checkValid(current, getSquare(p.getRow(), p.getCol())));
		return (Stack<Pair>) moves;
	}

	public boolean inMate(Player player) {

		ArrayList<Piece> pieces = player.getPieces();

		for (Piece p : pieces) {

			Stack<Pair> moves = getPlayableMoves(p);

			if (moves.size() != 0) {

				return false;
			}

		}

		return true;
	}

	public boolean inCheck(Player attacked, Player attacker) {

		Square kingSquare = getSquare(attacked.getKing().getRow(), attacked.getKing().getCol());

		for (Piece p : attacker.getPieces()) {

			Square pieceSquare = getSquare(p.getRow(), p.getCol());

			if (p.getClass() != King.class) {

				if (checkValid(pieceSquare, kingSquare)) {
					return true;
				}

			}
		}

		return false;
	}

	public boolean checkValid(Square from, Square to) {

		if (!checkSquareEmpty(from)) {
			Piece p = from.getPiece();

			boolean validMove = p.checkMoveValid(to);

			// check Castling
			if (p.getClass() == King.class) {

				if (to.getRow() == 7 && to.getCol() == 6) {

					validMove = getPlayerIn().getCanCastleRight();
				}

				if (to.getRow() == 7 && to.getCol() == 2) {

					validMove = getPlayerIn().getCanCastleLeft();
				}

			}

			if (validMove) {
				Stack<Pair> s = p.getMovePath(to);
				if (checkPath(s)) {
					Board clone = new Board(
							new Player(true, white.getTurn(), white.getCanCastleLeft(), white.getCanCastleRight(),
									white.getDeepCopyPieces(white.getPieces())),
							new Player(false, black.getTurn(), black.getCanCastleLeft(), black.getCanCastleRight(),
									black.getDeepCopyPieces(black.getPieces())));
					clone.makeMove(clone.getSquare(from.getRow(), from.getCol()),
							clone.getSquare(to.getRow(), to.getCol()));
					return !clone.getPlayerOut().getInCheck();
				}

				else {
					return false;
				}

			} else {
				return false;
			}
		}

		else {
			return false;
		}
	}

}
