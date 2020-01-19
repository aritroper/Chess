public class Pair {
	
	private int ROW;
	private int COL;
	
	public Pair (int ROW, int COL) {
		 setRow(ROW);
		 setCol(COL);
	}
	
	public void setRow (int row) {
		this.ROW = row;
	}
	
	public void setCol (int col) {
		this.COL = col;
	}
	
	public int getRow () {
		return ROW;
	}
	
	public int getCol () {
		return COL;
	}
	
	public boolean isValid () {
		
		return ! ((ROW > 7 || ROW < 0) || (COL > 7 || COL < 0));
		
	}
	
	public String toString() {
		return "ROW: " + ROW + ", COL: " + COL;
	}

}
