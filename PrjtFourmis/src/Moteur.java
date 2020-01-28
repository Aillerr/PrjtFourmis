
public class Moteur {
	private Monde m;
	private static final int generation = 4;
	private int nbFourmi;
	private Fourmi tabFourmi[];

	public Moteur(Monde m, int nbFourmi) {
		this.m = m;
		this.nbFourmi = nbFourmi;
		tabFourmi = new Fourmi[nbFourmi];
	}

	public void boucleJeu() {

		for (int i = 1; i <= generation; i++) {
			m.initialiser();
			m.generer();
			for (int j = 0; j < nbFourmi; j++) {
				//System.out.println("nombre de points d'actions : " + getNbActions());
				this.tabFourmi[j] = new Fourmi();
				this.tabFourmi[j].getComport().correctComport();
				int nbactions = tabFourmi[j].getNbActions();
				System.out.println("Nombre de point d'actions initiale :" + nbactions);
				while (nbactions != 0) {
					System.out.println(tabFourmi[j].toString());
					tabFourmi[j].ActionFourmi(m);
					nbactions--;
					m.afficher(tabFourmi[j]);
					System.out.println("Nombre de point d'actions :" + nbactions);

				}
			}
		}
	}

}
