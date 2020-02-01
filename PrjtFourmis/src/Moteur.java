import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Moteur {
	private Monde m;
	private int generation;
	private int nbFourmi;
	private Fourmi tabFourmi[];
	private int taille;
	private int nbactions;
	private int nbfood;
	private int nbhome;
	private int time;
	private boolean afficher;

	public Moteur(int nbFourmi, int taille, int temps, int nb_a, int generation,int nb_n, int nb_h, boolean afficher) {
		this.nbactions=nb_a;
		this.time=temps;
		this.taille=taille;
		this.nbFourmi = nbFourmi;
		this.nbhome = nb_h;
		this.nbfood = nb_n;
		this.generation = generation;
		this.afficher=afficher;
		tabFourmi = new Fourmi[nbFourmi];
		this.m=new Monde(taille,nbfood,nbhome,nbactions);
		m.initialiser();
		m.generer();
	}

	public void boucleJeu() {	
		Fenetre f= new Fenetre(m);
		f.setFrameMap(taille);
		Fourmi tab[] = new Fourmi [nbFourmi];
		for (int i = 1; i <= generation; i++) {
			Monde m2 = new Monde(m);
			for (int j = 0; j < nbFourmi; j++) {
				this.tabFourmi[j] = new Fourmi(i);
				this.tabFourmi[j].getComport().correctComport();
				while (nbactions != 0) {
						int x = tabFourmi[j].getPositionX();
						int y =tabFourmi[j].getPositionY();
						m2.setCase(tabFourmi[j].getPositionX(), tabFourmi[j].getPositionY(), 'A');
						tabFourmi[j].ActionFourmi(m2);
						int x2 = tabFourmi[j].getPositionX();
						int y2 =tabFourmi[j].getPositionY();
					    f.update(x2,y2,x,y);				     
						m2.afficher(tabFourmi[j]);
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

}
