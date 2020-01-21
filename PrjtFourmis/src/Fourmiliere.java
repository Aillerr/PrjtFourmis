
public class Fourmiliere extends Case{
	private int nbNourrStock;
	
	public Fourmiliere(int nb, char val) {
		super(val);
		nbNourrStock = nb;
	}
	
	public int getNourr() {
		return this.nbNourrStock;
	}
	
	public void setNourr(int nb) {
		nbNourrStock = nb;
	}
}
