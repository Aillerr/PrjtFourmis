import java.io.*;
import java.util.ArrayList;

public class Moteur {
	private Monde m;
	private static final int generation = 3;
	private int nbFourmi;
	private int nbFourmiGarde;
	private Fourmi tabFourmi[];
	private Fourmi tabGene[][];

	public Moteur(Monde m, int nbFourmi, int nb) {
		this.m = m;
		this.nbFourmi = nbFourmi;
		this.nbFourmiGarde = nb;
		tabFourmi = new Fourmi[nbFourmi];
		tabGene = new Fourmi[generation][nbFourmiGarde];
	}

	public void boucleJeu() {

		// Arbre a = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.RECOLT),
		// new Arbre(Comportements.IS_HOME, new Arbre(Comportements.DEPOSE), new
		// Arbre(Comportements.GO_HOME, new Arbre(Comportements.GO), new
		// Arbre(Comportements.GO))));
		Fourmi tab[] = new Fourmi[nbFourmi];
		int somme = 0;
		for (int i = 0; i < generation; i++) {
			m.initialiser();
			m.generer();
			Monde m2 = new Monde(m);
			m2.afficher();
			
			for (int j = 0;j < nbFourmi; j++) {
				if (i != 0) {
					for (int k=0; k<nbFourmiGarde;k++) {
						tabFourmi[k] = new Fourmi(tabGene [i-1][k]); 
					}
					somme = nbFourmiGarde;
				}
				// System.out.println("nombre de points d'actions : " + getNbActions());
				if (j + somme < nbFourmi) {
				this.tabFourmi[j + somme] = new Fourmi(i + 1);
				this.tabFourmi[j + somme].getComport().correctComport();
				int nbactions = tabFourmi[j + somme].getNbActions();
				while (nbactions != 0) {
					// System.out.println(tabFourmi[j].toString());
					// m2.afficher(tabFourmi[j]);
					tabFourmi[j].ActionFourmi(m2);
					// m2.afficher(tabFourmi[j]);
					nbactions--;
				}
				tab[j] = tabFourmi[j];
			}
			}
			triInsertion(tabFourmi);
			inverse(tabFourmi);
			int compteur = 0;
			for (int k = 0; k < nbFourmiGarde * 2; k = k + 2) {
				if (tabFourmi[k] != null && tabFourmi[k+1] != null) {
				tabGene[i][compteur] = new Fourmi(tabFourmi[k].Croisement(tabFourmi[k + 1]));
				compteur++;
				}
			}
			for (int k = 0; k < nbFourmiGarde; k++) {
				if (tabGene[i][k] != null) {
					System.out.println("Croisement de la generation : " + (i + 1) + " " + tabGene[i][k].toString());
				}
			}

		}
	}

	/**
	 * Charge toutes les fourmis sauvegarder dans un fichier texte, met à jour les paramètres du moteur.
	 * @param filepath Le chemin du fichier
	 */
	public void loadFourmis(String filepath){
		BufferedReader lecteur;
		try {
			lecteur = new BufferedReader(new FileReader(filepath));
			String line;
			nbFourmi = 0;
			ArrayList<Fourmi> listFourmi = new ArrayList<Fourmi>();
			while((line = lecteur.readLine()) != null){
				String fourmistr = line+"\n";
				while (!(line = lecteur.readLine()).equals("")) {
					fourmistr += line +"\n";
				}
				BufferedReader lecteurFourmi = new BufferedReader(new StringReader(fourmistr+"\n"));
				listFourmi.add(new Fourmi(lecteurFourmi));
				nbFourmi++;
			}
			tabFourmi = new Fourmi[nbFourmi];
			for (int i =0; i<nbFourmi; i++){
				tabFourmi[i] = listFourmi.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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

	public void inverse(Fourmi T[]) {
		for (int i = 0; i < T.length / 2; i++) {
			Fourmi temp = T[i];
			T[i] = T[T.length - 1 - i];
			T[T.length - 1 - i] = temp;
		}
	}

}
