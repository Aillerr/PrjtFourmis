import java.io.*;
import java.util.ArrayList;

public class Moteur {
	/**
	 * Le moteur fait tourner des fourmis dans un monde
	 * 
	 * @see Monde
	 */
	private Monde m;
	/**
	 * Le nombre de génération a faire simuler
	 */
	private int generation = 3;
	/**
	 * Le nombre de fourmi a faire simuler dans le monde
	 */
	private int nbFourmi;
	/**
	 * Le nombre de fourmi a gardé dans le monde
	 */
	private int nbFourmiGarde;
	/**
	 * Un tableau de toutes les fourmis ayant vécu dans le monde
	 */
	private Fourmi tabFourmi[];
	/**
	 * Un tableau de toutes les fourmis ayant vécu dans le monde, et étant les
	 * meilleures de ce monde
	 */
	private Fourmi tabGene[][];
	private int nbactions;
	private int time;
	private boolean afficher;
	private int x2,y2,x,y;
	private char c;
	private Fenetre f;
	/**
	 * Constructeur avec différents paramètres
	 * 
	 * @param m             un Monde
	 * @param nbFourmi      Le nombre de fourmi
	 * @param nbFourmiGarde Le nombre de fourmi à garder
	 */
	public Moteur(Monde m, int nbFourmi, int nbFourmiGarde,int temps, int nb_a, int generation, boolean afficher) {
		this.m = m;
		m.generer();
		m.initialiser();
		this.nbFourmi = nbFourmi;
		this.nbFourmiGarde = nbFourmiGarde;
		this.nbactions=nb_a;
		this.time=temps;
		this.nbFourmi = nbFourmi;
		this.generation = generation;
		this.afficher=afficher;
		tabFourmi = new Fourmi[nbFourmi];
		tabGene = new Fourmi[generation][nbFourmiGarde];
	}

	/**
	 * Boucle de jeu principal, faisant tourner la simulation
	 * @see Monde
	 * @see Fourmi
	 * 
	 */
	public void boucleJeu() {
		Fourmi tab[] = new Fourmi[nbFourmi];
		int somme = 0;
		Fenetre f= new Fenetre(m);
		f.setFrameMap(m.getTaille());
		this.f=f;
		char c=' ';
		for (int i = 0; i < generation; i++) {
			Monde m2 = new Monde(m);
			for (int j = 0; j < nbFourmi; j++) {
				if (i != 0) {
					for (int k = 0; k < nbFourmiGarde; k++) {
						tabFourmi[k] = new Fourmi(tabGene[i - 1][k]);
					}
					somme = nbFourmiGarde;
				}
				if (j + somme < nbFourmi) {
					this.tabFourmi[j] = new Fourmi(i + 1);
					this.tabFourmi[j].getComport().correctComport();
					int nbactions = tabFourmi[j].getNbActions();
					f.spawn();
					while (nbactions != 0) {
							Thread t= new Thread(new Refresh());
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						this.x = tabFourmi[j].getPositionX();
					    this.y = tabFourmi[j].getPositionY();
					    if((m2.getTable(x,y)).getValue()=='B') {
					    	c='N';
							m2.setCase(x, y, c);
					    }
					    else{
					    	if ((m2.getTable(x,y)).getValue()=='C') {
					    		c='F';
					    		m2.setCase(x, y, c);
					    	}
					    	else m2.setCase(x, y, 'R');
					    }
					    tabFourmi[j].ActionFourmi(m2);
					    this.x2 = tabFourmi[j].getPositionX();
					    this.y2 =tabFourmi[j].getPositionY();
					    if (m.estFourmiliere(x2, y2)) c='C';  
					    else{
					    	if (m.estNourriture(x2,y2)) c='B'; 
					    	else c='A';
					    } 				    
					    m2.setCase(tabFourmi[j].getPositionX(), tabFourmi[j].getPositionY(), c);
					    this.c=c;
					    if(afficher) t.start();
					    nbactions--;
						}
					}
					tab[j] = tabFourmi[j];
				}
			}
			triInsertion(tabFourmi);
			inverse(tabFourmi);
			int compteur = 0;
			String chemin = "generation" + (i + 1);
			for (int k = 0; k < nbFourmiGarde * 2; k = k + 2) {
					tabGene[i][compteur] = new Fourmi(tabFourmi[k].Croisement(tabFourmi[k + 1]));
					tabGene[i][compteur].download(chemin, "Fourmi" + (compteur + 1));
					compteur++;
			}
			for (int k = 0; k < nbFourmiGarde; k++) {
				if (tabGene[i][k] != null) {
					System.out.println("Croisement de la generation : " + (i + 1) + " " + tabGene[i][k].toString());
				}
			}

		}
	}

	/**
	 * Charge toutes les fourmis sauvegarder dans un fichier texte, met à jour les
	 * paramètres du moteur.
	 * 
	 * @param filepath Le chemin du fichier
	 */
	public void loadFourmis(String filepath) {
		BufferedReader lecteur;
		try {
			lecteur = new BufferedReader(new FileReader(filepath));
			String line;
			nbFourmi = 0;
			ArrayList<Fourmi> listFourmi = new ArrayList<Fourmi>();
			while ((line = lecteur.readLine()) != null) {
				String fourmistr = line + "\n";
				while (!(line = lecteur.readLine()).equals("")) {
					fourmistr += line + "\n";
				}
				BufferedReader lecteurFourmi = new BufferedReader(new StringReader(fourmistr + "\n"));
				listFourmi.add(new Fourmi(lecteurFourmi));
				nbFourmi++;
			}
			tabFourmi = new Fourmi[nbFourmi];
			for (int i = 0; i < nbFourmi; i++) {
				tabFourmi[i] = listFourmi.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tri permettant de mettre les meilleurs fourmis en dernier position
	 * @see Fourmi
	 * 
	 */
	public void triInsertion(Fourmi T[]) {
		int n = T.length - 1;
		for (int i = 2; i <= n; i++) {
			Fourmi v = new Fourmi(T[i]);
			int j = i;
			while (T[j - 1].getScore() > v.getScore()) {
				T[j] = new Fourmi(T[j - 1]);
				j = j - 1;
			}
			T[j] = v;
		}
	}

	/**
	 * Fonction permettant d'inverser le tableau
	 * @see Fourmi
	 * 
	 */
	public void inverse(Fourmi T[]) {
		for (int i = 0; i < T.length / 2; i++) {
			Fourmi temp = T[i];
			T[i] = T[T.length - 1 - i];
			T[T.length - 1 - i] = temp;
		}
	}
	
	class Refresh implements Runnable {
		public void run() {
			f.update(x2,y2,x,y,c);
		}
	}

}
