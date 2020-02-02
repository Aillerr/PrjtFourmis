import java.io.*;
import java.util.ArrayList;

public class Moteur {
	private Monde m;
	private static final int generation = 1;
	private int nbFourmi;
	private Fourmi tabFourmi[];

	public Moteur(Monde m, int nbFourmi) {
		this.m = m;
		this.nbFourmi = nbFourmi;
		tabFourmi = new Fourmi[nbFourmi];
	}

	public void boucleJeu() {
		m.initialiser();
		m.generer();
		//Arbre a = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.RECOLT), new Arbre(Comportements.IS_HOME, new Arbre(Comportements.DEPOSE), new Arbre(Comportements.GO_HOME, new Arbre(Comportements.GO), new Arbre(Comportements.GO))));
		Fourmi tab[] = new Fourmi [nbFourmi];
		for (int i = 1; i <= generation; i++) {
			Monde m2 = new Monde(m);
			m2.afficher();
			for (int j = 0; j < nbFourmi; j++) {
				// System.out.println("nombre de points d'actions : " + getNbActions());
				this.tabFourmi[j] = new Fourmi(i);
				this.tabFourmi[j].getComport().correctComport();
				int nbactions = tabFourmi[j].getNbActions();
				while (nbactions != 0) {
					//System.out.println(tabFourmi[j].toString());
					//m2.afficher(tabFourmi[j]);
					tabFourmi[j].ActionFourmi(m2);
					//m2.afficher(tabFourmi[j]);
					nbactions--;
				}
				tab[j] = tabFourmi[j];
			}

		}
		for (int j = 0; j < nbFourmi; j++) {
			if (tab[j].getScore() != 0) {
			System.out.println(tab[j]);
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

}
