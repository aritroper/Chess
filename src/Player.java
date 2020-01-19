import java.util.ArrayList;

public class Player {
	
	private boolean color;
	private boolean turn;
	private boolean inCheck;
	private ArrayList<Piece> pieces;
	private Piece king;
	
	public Player(boolean color, boolean turn, ArrayList<Piece> pieces) {

		this.pieces = pieces;
		
		setColor(color);
		setTurn(turn);
		setInCheck(false);
		setKing();

	}
	
	public void setKing() {
		
		for(Piece p : pieces) {
			
			if(p.getClass() == King.class) {
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
	
	public boolean getInCheck() {
		
		return inCheck;
	}
	
	public ArrayList<Piece> getPieces() {
		
		return pieces;
	}
	
	public void removePiece(Piece p) {
		
		pieces.remove(p);
	}
	
	public ArrayList<Piece> getDeepCopyPieces (ArrayList<Piece> pieces) {
		ArrayList<Piece> deepCopy = new ArrayList<Piece>(pieces.size());
		
		for(Piece p : pieces) {
			
			deepCopy.add(p.clone());
		}
		
		return deepCopy;
	}
	
	public Piece getKing() {
		
		return king;
	}
	
	public String toString() {
		
		return color? "White" : "Black" ;
	}

}
