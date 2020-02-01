
public class Fourmi {
	
	/**
	 * Le score de la fourmi.
	 * Plus il est haut, plus la fourmi a perform�.
	 * Elle gagne 10 de score quand elle ramasse de la nourriture, 20 si elle en d�pose � la fourmili�re.
	 * 
	 * @see Fourmi#getScore()
	 * @see Fourmi#setScore(int)
	 */
	private int score;
	
	/**
	 * La g�n�ration � laquelle appartient la fourmi.
	 * 
	 * @see Fourmi#getNumGeneration()
	 */
	private int numGeneration;
	
	/**
	 * La position en X (ligne) de la fourmi sur le monde.
	 * 
	 * @see Fourmi#getPositionX()
	 * @see Fourmi#setPositionX(int)
	 */
	private int positionX;
	
	/**
	 * La position en Y (colonne) de la fourmi sur le monde.
	 * 
	 * @see Fourmi#getPositionY()
	 * @see Fourmi#setPositionY(int)
	 */
	private int positionY;
	
	/**
	 * Le comportement d'une fourmi. Il est stock� sous la forme d'un arbre binaire.
	 * 
	 * @see Fourmi#getComport()
	 */
	private Arbre comport;
	
	/**
	 * Le bool�en qui dit si la fourmi porte qqchose ou pas.
	 * 
	 * @see Fourmi#getCarrying()
	 * @see Fourmi#setIsCarrying(int)
	 */
	private boolean isCarrying;
	
	/**
	 * Le nombre d'action pour une fourmi
	 * 
	 * @see Fourmi#getNbActions()
	 * @see Fourmi#setNbActions(int)
	 */
	private static int nbActions = 10;

	// Constructeurs

	/**
	 * Constructeur sans param�tre
	 * <p>
	 * La fourmi est de 1�re g�n�ration, a 0 de score, ne porte rien.
	 * On la pose en (0,0) et son comportement est cr�� avec le constructeur d'Arbre
	 * </p>
	 * 
	 * @see Fourmi#comport
	 * @see Fourmi#isCarrying
	 * @see Fourmi#positionX
	 * @see Fourmi#positionY
	 * @see Fourmi#score
	 * @see Fourmi#numGeneration
	 * 
	 * @see Arbre
	 */
	public Fourmi() {
		
		
		this.score = 0;
		
		
		this.numGeneration = 1;
		
		
		this.isCarrying = false;
		
		
		this.positionX = 0;
		this.positionY = 0;
		this.comport = new Arbre(100);
	}

	/**
	 * Constructeur avec un param�tre pour choisir le num�ro de la g�n�ration
	 * 
	 * @param n
	 * Le num�ro de g�n�ration
	 * 
	 * @see Fourmi#Fourmi()
	 */
	public Fourmi(int n) {
		this();

		this.numGeneration = n;
	}

	/**
	 * Constructeur avec 2 param�tres pour choisir le num�ro de la g�n�ration et le comportement.
	 * 
	 * @param n
	 * Le num�ro de la g�n�ration
	 * 
	 * @param A
	 * L'arbre de comportements
	 */
	public Fourmi(int n, Arbre A) {
		this(n);
		this.comport = A;
	}

	// Get

	/**
	 * Retourne le num�ro de g�n�ration
	 * 
	 * @return Un entier repr�sentant le num�ro de la g�n�ration
	 */
	public int getNumGeneration() {
		return this.numGeneration;
	}

	/**
	 * Retourne l'arbre de comportements de la fourmi
	 * 
	 * @return Un arbre comportant les comportements
	 */
	public Arbre getComport() {
		return this.comport;
	}

	/**
	 * Retourne le nombre d'actions autoris��s pour la fourmi
	 * 
	 * @return Un entier repr�sentant le nombre d'actions max que la fourmi peut faire
	 */
	public static int getNbActions() {
		return nbActions;
	}

	/**
	 * Retourne le statut de la fourmi, porte t-elle qqchose ou pas ?
	 * 
	 * @return Un bool�en qui indique si la fourmi porte de la nourriture
	 */
	public boolean getCarrying() {
		return isCarrying;
	}

	/**
	 * Retourne le score de la fourmi
	 * 
	 * @return Un entier repr�sentant son score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Retourne la ligne sur laquelle est la fourmi.
	 * 
	 * @return Un entier qui repr�sente la ligne du monde (tableau de cases) sur laquelle est la fourmi.
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Retourne la colonne sur laquelle est la fourmi.
	 * 
	 * @return Un entier qui repr�sente la colonne du monde (tableau de cases) sur laquelle est la fourmi.
	 */
	public int getPositionY() {
		return positionY;
	}

	// Set

	/**
	 * Incr�mente la valeur du score de la fourmi.
	 * 
	 * @param i Le score � ajouter au score actuel.
	 */
	public void setScore(int i) {
		this.score += i;
	}

	/**
	 * Modifie le bool�en qui indique si la fourmi porte qqchose.
	 * 
	 * @param b Le bool�en qui remplacera le isCarrying actuel.
	 */
	public void setIsCarrying(boolean b) {
		this.isCarrying = b;
	}

	/**
	 * Modifie la ligne sur laquelle est la fourmi.
	 * 
	 * @param x La nouvelle ligne.
	 */
	public void setPositionX(int x) {
		this.positionX = x;
	}

	
	/**
	 * Modifie la colonne sur laquelle est la fourmi.
	 * 
	 * @param y La nouvelle colonne.
	 */
	public void setPositionY(int y) {
		this.positionY = y;
	}

	
	/**
	 * Modifie le nombre d'actions maximales que la fourmi peut faire.
	 * 
	 * @param nb Un entier qui est le nouveau nombre d'actions.
	 */
	public static void setNbActions(int nb) {
		nbActions = nb;
	}

	
	/**
	 * La fourmi se d�place d'une case vers la fourmili�re la plus proche du monde m.
	 * On modifie donc la positionX et la positionY de la fourmi.
	 * 
	 * @param m Le monde sur lequel la fourmi se d�place.
	 * 
	 * @see Monde
	 * @see Monde#recupFourmiliereLaPlusProche(Fourmi, Case)
	 * @see Case
	 */
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
	
	/**
	 * La m�thode Action d'une fourmi.
	 * Elle effectue l'action du noeud principal de son arbre, puis en fonction du type d'action, elle effectue la suivante en prenant le fils gauche ou droit de l'abre.
	 * 
	 * @param m Le monde sur lequel se d�place la fourmi.
	 * 
	 * @see Comportements
	 * @see Arbre
	 */
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
					if(!A.isFeuille()) {
					A = A.getSousArbreDroit();
					continue;
					}
					else finAction=true;
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
			default: // Les cas o� on aura modifi� les arbres et y'aura du null, �a fait rien et �a
						// termine l'action
				finAction = true;
				break;
			}
		}
	}
	// Croiser 2 fourmis

	/**
	 * On effectue le croisement entre 2 fourmis.
	 * 
	 * @param f L'autre fourmi avec laquelle en croise notre fourmi
	 * @return Une nouvelle fourmi qui comprend une partie de l'arbre de chaque parent.
	 * 
	 * @see Arbre
	 */
	public Fourmi Croisement(Fourmi f) {
		Arbre arb = f.getComport(), source = this.comport;

		int rnd = (int) (Math.random() * 4);

		if (rnd >= 2) { // Si 0 ou 1 on prend le comportement de la fourmi en param�tre, sinon l'autre
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

	/**
	 * La fonction toString renvoie le score et la position sur le monde de la fourmi.
	 * 
	 * @return Une chaine de caract�res que l'on pourra afficher avec le score, la positionX, et la positionY.
	 */
	public String toString() {
		return ("Score : " + getScore() + " Arbre : " + comport.toString() + " " + "Position en X : " + getPositionX()
				+ " Position Y : " + getPositionY());
	}

}