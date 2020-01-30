
public class Nourriture extends Case{
	private int quantite;
	
	public Nourriture (int quantite, char val) {
		super(val);
		this.quantite = quantite;
	}
	
	

	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int i) {
		quantite = i;
	}

}
