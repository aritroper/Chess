import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer extends Player {

	public Computer(boolean color, boolean turn, boolean canCastleLeft, boolean canCastleRight,
			ArrayList<Piece> pieces) {

		super(color, turn, canCastleLeft, canCastleRight, pieces);

	}
	
	public void getMove(Board b) {
		
		Random rand = new Random();
		
		int randPieceIndex = rand.nextInt(getPieces().size());
		Piece randomPiece = getPieces().get(randPieceIndex);
		
		if(b.getPlayableMoves(randomPiece).size() == 0) {
			
			getMove(b);
		} 
		
		else {
			
			List<Pair> playableMoves = b.getPlayableMoves(randomPiece);
			int randMoveIndex = rand.nextInt(playableMoves.size());
			Pair randMove = playableMoves.get(randMoveIndex);
			
			Square squareFrom = b.getSquare(randomPiece.getRow(), randomPiece.getCol());
			Square squareTo = b.getSquare(randMove.getRow(), randMove.getCol());
			
			b.makeMove(squareFrom, squareTo);
			
		}
		
	}

}
