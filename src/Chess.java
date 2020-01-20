import java.util.ArrayList;
import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Welcome to Chess! Would you like to player PvP or PvC?");
			String firstInput = scanner.nextLine();
			Board board;

			if (firstInput.toUpperCase().equals("PVC")) {
				System.out.println("Would you like to play Black or White?");
				String secondInput = scanner.nextLine();
				boolean color = secondInput.toUpperCase().equals("WHITE");
				board = new Board(new Player(color, color, true, true, getPieces(color)),
						new Computer(!color, !color, true, true, getPieces(!color)));
			}

			else {
				board = new Board(new Player(true, true, true, true, getPieces(true)),
						new Player(false, false, true, true, getPieces(false)));
			}

			getMove(board);
		}
	}

	public static ArrayList<Piece> getPieces(boolean color) {

		int pawnRow = color ? 6 : 1;
		int miscRow = color ? 7 : 0;
		ArrayList<Piece> pieces = new ArrayList<Piece>();

		for (int i = 0; i < 8; i++) {
			Piece pawn = new Pawn(color, pawnRow, i);
			pieces.add(pawn);
		}

		Rook rLeft = new Rook(color, miscRow, 0);
		rLeft.setIsLeftRook(color);
		Rook rRight = new Rook(color, miscRow, 7);
		rRight.setIsLeftRook(!color);

		pieces.add(rLeft);
		pieces.add(new Knight(color, miscRow, 1));
		pieces.add(new Bishop(color, miscRow, 2));
		pieces.add(new Queen(color, miscRow, 3));
		pieces.add(new King(color, miscRow, 4));
		pieces.add(new Bishop(color, miscRow, 5));
		pieces.add(new Knight(color, miscRow, 6));
		pieces.add(rRight);

		return pieces;
	}

	public static void getMove(Board b) {

		if(b.getPlayerIn().getClass() != Computer.class) {
			
			System.out.println(b.printBoard());
			
		}
		
		if (b.getPlayerIn().getInCheck()) {
			System.out.println("\n" + b.getPlayerIn() + " is in CHECK!");

			if (b.inMate(b.getPlayerIn())) {
				System.out.println("\nGame Over. " + b.getPlayerOut().toString() + " WINS!");
				System.exit(0);
			}
		}

		b.getPlayerIn().getMove(b);
		getMove(b);
	}

	public static int letterToNum(char c) {
		switch (c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			return -1;
		}

	}

}
