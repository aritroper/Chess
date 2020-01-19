import java.util.Optional;

public class Square {
	
	private boolean isEmpty;
	private char printable;
	private Optional<Piece> piece;
	private int row;
	private int col;

	public Square (Optional<Piece> piece, int row, int col) {
		
		this.piece = piece;
		this.row = row;
		this.col = col;
		
		setEmptiness();
		setPrintable();
	}
	
	public int getRow() {
		
		return row;
	}
	
	public int getCol() {
		
		return col;
	}
	
	public boolean getEmptiness() {
		
		return isEmpty;
	}
	
	public String toString() {
		
		return Character.toString(printable);
	}
	
	public Piece getPiece() {
		
		return piece.get();
	}
	
	public void setEmptiness() {
		
		this.isEmpty = !this.piece.isPresent();
	}
	
	public void setPrintable() {
		
		this.printable = ' ';
		this.piece.ifPresent(p -> this.printable = p.getPrintable());
	}
	
	public void setPiece(Piece piece) {
		
		this.piece = Optional.of(piece);
		piece.setPosition(this.row, this.col);
		setEmptiness();
		setPrintable();
	}
	
	public void removePiece() {
		
		this.piece = Optional.empty();
		setEmptiness();
		setPrintable();
	}
}
