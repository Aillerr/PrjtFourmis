
public class Fourmiliere extends Case{
	private int nbNourrStock;
	
	public Fourmiliere(int nbNourrStock, char val) {
		super(val);
		this.nbNourrStock = nbNourrStock;
	}
	
	public int getNourr() {
		return this.nbNourrStock;
	}
	
	public void setNourr(int nb) {
		nbNourrStock = nb;
	}
}
