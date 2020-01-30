
public class Fourmi {
	private int score;
	private int numGeneration;
	private int positionX;
	private int positionY;
	private Arbre comport;
	private boolean isCarrying;
	private static int nbActions = 10;

	// Constructeurs

	public Fourmi() {
		this.score = 0;
		this.numGeneration = 1;
		this.isCarrying = false;
		this.positionX = 0;
		this.positionY = 0;
		this.comport = new Arbre(100);
	}

	public Fourmi(int n) {
		this();

		this.numGeneration = n;
	}

	public Fourmi(int n, Arbre A) {
		this(n);
		this.comport = A;
	}

	// Get

	public int getNumGeneration() {
		return this.numGeneration;
	}

	public Arbre getComport() {
		return this.comport;
	}

	public static int getNbActions() {
		return nbActions;
	}

	public boolean getCarrying() {
		return isCarrying;
	}

	public int getScore() {
		return score;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	// Set

	public void setScore(int i) {
		this.score += i;
	}

	public void setIsCarrying(boolean b) {
		this.isCarrying = b;
	}

	public void setPositionX(int x) {
		this.positionX = x;
	}

	public void setPositionY(int y) {
		this.positionY = y;
	}

	public static void setNbActions(int nb) {
		nbActions = nb;
	}

	public void goHome(Monde m) {
		int nbAction = getNbActions();
		Case ca = new Case(0, 0);
		m.recupFourmiliereLaPlusProche(this, ca);
		int x = getPositionX();
		int y = getPositionY();
		int caseX = ca.getRow();
		int caseY = ca.getCol();
		if (caseY != y) {
			if (caseY > y) {
				y++;
				setPositionY(y);
			}
			else if (caseY < y){
				y--;
				setPositionY(y);
			}
		}
		else if (caseX != x) {
			if (caseX > x) {
				x++;
				setPositionX(x);
			}
			else if (caseX < x){
				x--;
				setPositionX(x);
			}
		}				
	}
					
					
					
					
	// Action d'une fourmi
	public void ActionFourmi(Monde m) {
		boolean finAction = false;
		Arbre A = getComport();
		int x = getPositionX();
		int y = getPositionY();
		int z = (int) (Math.random() * 3);
		while (!finAction) {

			Comportements c = A.getNoeud();
			switch (c) {
			case GO:
				finAction = true;
			case GO_LEFT:
				if (z == 0 || c.equals(Comportements.GO_LEFT)) {
					if (getPositionY() == 0) {
						setPositionY(m.getTaille() - 1);
					} else {
						y--;
						setPositionY(y);
					}
					finAction = true;
					break;
				}
			case GO_RIGHT:
				if (z == 1 || c.equals(Comportements.GO_RIGHT)) {
					if (getPositionY() == (m.getTaille() - 1)) {
						setPositionY(0);
					} else {
						y++;
						setPositionY(y);
					}
					finAction = true;
					break;
				}
			case GO_UP:
				if (z == 2 || c.equals(Comportements.GO_UP)) {
					if (getPositionX() == 0) {
						setPositionX(m.getTaille() - 1);
					} else {
						x--;
						setPositionX(x);
					}
					finAction = true;
					break;
				}
			case GO_DOWN:
				if (z == 3 || c.equals(Comportements.GO_DOWN)) {
					if (getPositionX() == (m.getTaille() - 1)) {
						setPositionX(0);
					} else {
						x++;
						setPositionX(x);
					}
					finAction = true;
					break;
				}
			case RECOLT:
				if (m.estNourriture(positionX, positionY)) {
					if (getCarrying() == false) {
						setScore(10);
						isCarrying = true;
					}
				}
				finAction = true;
				break;
			case DEPOSE:
				if (m.estFourmiliere(positionX, positionY)) {
					if (getCarrying() == true) {
						setScore(20);
						isCarrying = false;
					}
				}
				finAction = true;
				break;
			case GO_HOME:
				if (getCarrying() && !m.estFourmiliere(positionX, positionY)) {
					this.goHome(m);
					finAction = true;
				}
				else {
					A = A.getSousArbreGauche();
					continue;
				}
				break;
				
			case IS_FOOD:
				if (!A.isFeuille()) {
					if (m.estNourriture(positionX, positionY) && !getCarrying()) {
						A = A.getSousArbreGauche();
						continue;
					} else {
						A = A.getSousArbreDroit();
						continue;
					}
				}
				finAction = true;
				break;
			case IS_HOME:
				if (!A.isFeuille()) {
					if (m.estFourmiliere(positionX, positionY) && getCarrying()) {
						A = A.getSousArbreGauche();
						continue;
					} else {
						A = A.getSousArbreDroit();
						continue;
					}
				} else
					finAction = true;
				break;
			default: // Les cas où on aura modifié les arbres et y'aura du null, ça fait rien et ça
						// termine l'action
				finAction = true;
				break;
			}
		}
	}
	// Croiser 2 fourmis

	public Fourmi Croisement(Fourmi f) {
		Arbre arb = f.getComport(), source = this.comport;

		int rnd = (int) (Math.random() * 4);
		// System.out.println(rnd);
		if (rnd >= 2) { // Si 0 ou 1 on prend le comportement de la fourmi en paramètre, sinon l'autre
			arb = this.comport;
			source = f.getComport();
		}

		if (rnd % 2 == 0)
			arb.setSousArbreGauche(source.getSousArbreGauche()); // Si 0 ou 2 on remplace le fils gauche par celui de
																	// l'autre fourmi
		else
			arb.setSousArbreDroite(source.getSousArbreDroit()); // Sinon le fils droit

		return (new Fourmi(f.getNumGeneration() + 1, arb));
	}

	public String toString() {
		return ("Score : " + getScore() + " Arbre : " + comport.toString() + " " + "Position en X : " + getPositionX()
				+ " Position Y : " + getPositionY());
	}

}