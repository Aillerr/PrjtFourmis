
public class Nourriture {
	private int quantite,positionX,positionY;
	
	public Nourriture (int quantite) {
		this.quantite = quantite;
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public int getQuantite() {
		return quantite;
	}

}
