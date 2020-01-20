import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private boolean color;
	private boolean turn;
	private boolean inCheck;
	private boolean canCastleLeft;
	private boolean canCastleRight;
	private ArrayList<Piece> pieces;
	private Piece king;

	public Player(boolean color, boolean turn, boolean canCastleLeft, boolean canCastleRight, ArrayList<Piece> pieces) {

		this.pieces = pieces;

		setColor(color);
		setTurn(turn);
		setCanCastleLeft(canCastleLeft);
		setCanCastleRight(canCastleRight);
		setInCheck(false);
		setKing();

	}

	public void getMove(Board b) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nEnter a move (" + b.getPlayerIn() + ") :");
		System.out.println("____________________\n");
		String input = scanner.nextLine();
		String[] split = input.split("\\s+");

		if (split.length != 2) {
			System.out.println("\nInvalid move. Move must be of form a2 a3.");
			getMove(b);
		} else {
			int letterFrom = Chess.letterToNum(split[0].charAt(0));
			int numberFrom = 7 - (Integer.parseInt(Character.toString(split[0].charAt(1))) - 1);

			int letterTo = Chess.letterToNum(split[1].charAt(0));
			int numberTo = 7 - (Integer.parseInt(Character.toString(split[1].charAt(1))) - 1);

			Square squareFrom = b.getSquare(numberFrom, letterFrom);
			Square squareTo = b.getSquare(numberTo, letterTo);

			if (squareFrom.getPiece().getColor() != b.getPlayerIn().getColor()) {

				System.out.println("\nInvalid move. It is " + b.getPlayerIn() + "'s turn.");
				getMove(b);
			}

			else {

				if (b.checkValid(squareFrom, squareTo)) {
					b.makeMove(squareFrom, squareTo);
				}

				else {
					System.out.println("\nInvalid move.");
				}

			}
		}
	}

	public void setKing() {

		for (Piece p : pieces) {

			if (p.getClass() == King.class) {
				this.king = p;
			}
		}
	}

	public void setColor(boolean color) {

		this.color = color;
	}

	public void setTurn(boolean turn) {

		this.turn = turn;
	}

	public void setCanCastleLeft(boolean canCastleLeft) {

		this.canCastleLeft = canCastleLeft;
	}

	public void setCanCastleRight(boolean canCastleRight) {

		this.canCastleRight = canCastleRight;
	}

	public void setInCheck(boolean inCheck) {
		this.inCheck = inCheck;
	}

	public void switchTurn() {

		this.turn = !this.turn;
	}

	public boolean getColor() {

		return color;
	}

	public boolean getTurn() {

		return turn;
	}

	public boolean getCanCastleLeft() {

		return canCastleLeft;
	}

	public boolean getCanCastleRight() {

		return canCastleRight;
	}

	public boolean getInCheck() {

		return inCheck;
	}

	public ArrayList<Piece> getPieces() {

		return pieces;
	}

	public void removePiece(Piece p) {

		pieces.remove(p);
	}

	public ArrayList<Piece> getDeepCopyPieces(ArrayList<Piece> pieces) {
		ArrayList<Piece> deepCopy = new ArrayList<Piece>(pieces.size());

		for (Piece p : pieces) {

			deepCopy.add(p.clone());
		}

		return deepCopy;
	}

	public Piece getKing() {

		return king;
	}

	public String toString() {

		return color ? "White" : "Black";
	}

}
