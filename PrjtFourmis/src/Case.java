
public class Case {
	protected int row;
	protected int col;
	private char value;
	
	public Case(int x, int y) {
		row = x;
		col = y;
		value = 'R';
	}
	
	public Case(int x, int y, char val) {
		this(x,y);
		value = val;
	}
	
	public Case(char val) {
		value = val;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public char getValue() {
		return this.value;
	}
	
	
	
	public void setRow(int x) {
		this.row = x;
	}
	
	public void setCol(int y) {
		this.col = y;
	}
	
	public void setVal(char val) {
		this.value = val;
	}
	
	public void setCase(Case c) {
		this.col=c.getCol();
		this.row=c.getRow();
		this.value=c.getValue();
	}
	
	public void afficherCase() {
		System.out.print(this.value);
	}
	
	
}
