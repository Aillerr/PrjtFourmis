import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Monde {
	private int taille, nbNourriture, nbFourmilliere, nbAction;
	private Case tab[][];

	public Monde(int taille, int nbNourriture, int nbFourmilliere, int nbAction) {
		this.taille = taille;
		tab = new Case[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[i][j] = new Case(i, j, 'R');
			}
		}
		this.nbFourmilliere = nbFourmilliere;
		this.nbNourriture = nbNourriture;
		this.nbAction = nbAction;
	}

	public Monde(Monde m) {
		this.taille = m.taille;
		this.nbFourmilliere = m.nbFourmilliere;
		this.nbNourriture = m.nbNourriture;
		this.nbAction = m.nbAction;
		this.tab = new Case[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				this.tab[i][j] = new Case(i, j, m.tab[i][j].getValue());
			}
		}
	}

	public Monde(int taille) { // monde vide
		tab = new Case[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[i][j] = new Case(i, j, 'R');
			}
		}
		this.nbFourmilliere = 0;
		this.nbNourriture = 0;
		this.nbAction = 0;
	}

	/**
	 * Génération d'un nouveau monde à partir d'un fichier
	 * 
	 * @param
	 * @throws IOException
	 */
	public Monde(String fichier) throws IOException {
		BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
		String line = lecteur.readLine();
		taille = line.length() / 8;
		Monde m = new Monde(taille);
		for (int y = 2; y <= taille * 2 + 1; y++) {
			line = lecteur.readLine();
			if (y % 2 == 0) {
				int row = y / 2 - 1;
				line = line.substring(4, line.length() - 4);
				String[] line_spl = line.split("\\s{3}\\|\\s{3}"); // Split (" | ")
				for (int col = 0; col < line_spl.length; col++) {
					if (line_spl[col].compareTo("F") == 0) {
						m.tab[row][col] = new Fourmiliere(0, 'F');
						nbFourmilliere++;
					} else if (line_spl[col].compareTo("F") != 0 && line_spl[col].compareTo(" ") != 0) {
						try {
							int nbNour = Integer.parseInt(line_spl[col]);
							m.tab[row][col] = new Nourriture(nbNour, 'N');
							nbNourriture++;
						} catch (java.lang.NumberFormatException e) {
							System.out.println(
									"Objet non reconnue ! x =" + row + "y =  " + y + ": !" + line_spl[col] + "! ");
						}
					}
				}
			}
		}
		this.tab = m.tab;
	}

	public void generer() {
		int nbCountNour = nbNourriture;
		int nbCountFour = nbFourmilliere;
		while (!(nbCountNour == 0 && nbCountFour == 0)) {
			int x = (int) (Math.random() * taille);
			int y = (int) (Math.random() * taille);
			int z = (int) (Math.random() * nbCountNour) + 1;

			if (tab[x][y].getValue() == 'R') {
				if (nbCountNour == 0) {
					tab[x][y] = new Fourmiliere(0, 'F');
					nbCountFour--;
				} else {
					tab[x][y] = new Nourriture(z, 'N');
					nbCountNour -= z;
				}
			}
		}

	}

	public void initialiser() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[i][j] = new Case(i, j, 'R');
			}
		}
	}

	public void afficher() {
		String ligne_sep = "";
		for (int i = 0; i < 8 * taille + 1; i++) {
			ligne_sep += "-";
		}
		System.out.println(ligne_sep);
		for (int row = 0; row < taille; row++) {
			System.out.print("|   ");
			for (int col = 0; col < taille; col++) {
				if (tab[row][col].getValue() != 'R')
					tab[row][col].afficherCase();
				else
					System.out.print(" ");
				if (col < taille - 1)
					System.out.print("   |   ");
			}
			System.out.println("   |");
			System.out.println(ligne_sep);
		}
	}

	public void afficher(Fourmi f) {
		String ligne_sep = "";
		for (int i = 0; i < 8 * taille + 1; i++) {
			ligne_sep += "-";
		}
		System.out.println(ligne_sep);
		for (int row = 0; row < taille; row++) {
			System.out.print("|  ");
			for (int col = 0; col < taille; col++) {
				if (tab[row][col].getValue() != 'R') {
					if (f.getPositionX() == row && f.getPositionY() == col) {
						System.out.print("A + ");
					}
					tab[row][col].afficherCase();
				} else if (f.getPositionX() == row && f.getPositionY() == col)
					System.out.print("A");
				System.out.print(" ");
				if (col < taille - 1)
					System.out.print("   |   ");
			}
			System.out.println("   |");
			System.out.println(ligne_sep);
		}
	}

	public void download(String path, String filename) throws IOException {
		PrintWriter writer = new PrintWriter(path + "\\" + filename + ".txt", "UTF-8");

		String ligne_sep = "";
		for (int i = 0; i < 8 * taille + 1; i++) {
			ligne_sep += "-";
		}
		writer.println(ligne_sep);
		for (int row = 0; row < taille; row++) {
			writer.print("|   ");
			for (int col = 0; col < taille; col++) {
				if (tab[row][col].getValue() != 'R') {
					if (tab[row][col].getValue() == 'F')
						writer.print('F');
					else if (tab[row][col].getValue() == 'N') {
						Nourriture nour_case = (Nourriture) tab[row][col]; // downcast pour récupérer le nombre de
																			// nourriture sur la case;
						writer.print(nour_case.getQuantite());
					}
				} else
					writer.print(" ");
				if (col < taille - 1)
					writer.print("   |   ");
			}
			writer.println("   |");
			writer.println(ligne_sep);
		}
		writer.close();
	}

	public int getNbNourriture() {
		return nbNourriture;
	}

	public int getNbFourmiliere() {
		return nbFourmilliere;
	}

	public int getNbAction() {
		return nbAction;
	}

	public int getTaille() {
		return taille;
	}

	public void setAction(int nbAction) {
		this.nbAction = nbAction;
	}

	public Case getTable(int i, int j) {
		return this.tab[i][j];
	}

	public boolean estNourriture(int x, int y) {
		if (tab[x][y] instanceof Nourriture) {
			return true;
		}
		return false;
	}

	public boolean estFourmiliere(int x, int y) {
		if (tab[x][y] instanceof Fourmiliere) {
			return true;
		}
		return false;
	}

	public int[][] recupFourmiliere(Fourmi f) {
		int tableau[][] = new int[nbFourmilliere][3];
		int ligne = 0;
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (estFourmiliere(i, j)) {
					tableau[ligne][0] = i;
					tableau[ligne][1] = j;
					tableau[ligne][2] = (Math.abs(i - f.getPositionX()) + Math.abs(j - f.getPositionY()));
					ligne++;
				}
			}
		}
		return tableau;
	}

	public void recupFourmiliereLaPlusProche(Fourmi f, Case c) {
		int temp = 0;
		int tabFourm[][] = recupFourmiliere(f);
		for (int i = 1; i < nbFourmilliere; i++) {
			if (tabFourm[temp][2] > tabFourm[i][2]) {
				temp = i;
			}
		}
		int x = tabFourm[temp][0];
		int y = tabFourm[temp][1];
		c.setCol(y);
		c.setRow(x);
		;

	}

}
