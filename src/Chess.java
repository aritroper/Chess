import java.util.ArrayList;
import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board(new Player(true, true, getPieces(true)), new Player(false, false, getPieces(false)));
		System.out.println("\n" + board.printBoard());
		getMove(board);
	}
	
	public static ArrayList<Piece> getPieces(boolean color) {
		
		int pawnRow = color? 6 : 1;
		int miscRow = color? 7 : 0;
		ArrayList<Piece> pieces = new ArrayList<Piece>() ;
		
		for(int i = 0; i < 8; i++)
		{
			Piece pawn = new Pawn(color, pawnRow, i);
			pieces.add(pawn);
		}
		
		pieces.add(new Rook(color, miscRow, 0));
		pieces.add(new Knight(color, miscRow, 1));
		pieces.add(new Bishop(color, miscRow, 2));
		pieces.add(new Queen(color, miscRow, 3));
		pieces.add(new King(color, miscRow, 4));
		pieces.add(new Bishop(color, miscRow, 5));
		pieces.add(new Knight(color, miscRow, 6));
		pieces.add(new Rook(color, miscRow, 7));
		
		return pieces;
	}
	
	public static void getMove(Board b) {
		Scanner scanner = new Scanner(System.in);
		if(b.getPlayerIn().getInCheck()) {
			System.out.println("\n" + b.getPlayerIn() + " is in CHECK!");
			
			if (b.inMate(b.getPlayerIn())) {
				System.out.println("\nGame Over. " + b.getPlayerOut().toString() + " WINS!");
				System.exit(0);
			}
		}
		System.out.println("\nEnter a move (" + b.getPlayerIn() + ") :");
		System.out.println("____________________\n");
		String input = scanner.nextLine();
		String[] split = input.split("\\s+");
		
		if(split.length != 2) {
			System.out.println("\nInvalid move. Move must be of form a2 a3.");
			getMove(b);
		}
		else {
			int letterFrom = letterToNum(split[0].charAt(0));
			int numberFrom = 7 - (Integer.parseInt(Character.toString(split[0].charAt(1))) - 1);
			
			int letterTo = letterToNum(split[1].charAt(0));
			int numberTo = 7 - (Integer.parseInt(Character.toString(split[1].charAt(1))) - 1);
			
			Square squareFrom = b.getSquare(numberFrom, letterFrom);
			Square squareTo = b.getSquare(numberTo, letterTo);
			
			if(squareFrom.getPiece().getColor() != b.getPlayerIn().getColor()) {
				
				System.out.println("\nInvalid move. It is " + b.getPlayerIn() + "'s turn.");
				getMove(b);
			}
			
			else {
				
				if (b.checkValid(squareFrom, squareTo)) {
					b.makeMove(squareFrom, squareTo);
					System.out.println(b.printBoard());
					getMove(b);
				}
				
				else {
					System.out.println("\nInvalid move.");
					getMove(b);
				}
				
			}
			
			scanner.close();
		}
	}
	
	public static int letterToNum(char c) {
		switch(c) {
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
