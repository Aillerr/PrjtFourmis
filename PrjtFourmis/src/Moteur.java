
public class Moteur {
		private Monde m;
		private static final int generation = 4;
		private int nbFourmi;
		private Fourmi tabFourmi[];
		
		public Moteur (Monde m, int nbFourmi) {
			this.m = m;
			this.nbFourmi = nbFourmi;
			tabFourmi = new Fourmi[nbFourmi];
		}
		
	    
	    
		
		public void boucleJeu() {
			
			for (int i=1;i<= generation;i++) {
				m.generer();
				
				
				
				for(int j=1; j <= nbFourmi; j++) {
					this.tabFourmi[j] = new Fourmi(); 
					int nbactions = Fourmi.getNbActions();
					while (nbactions !=0) {
					
						nbactions--;
						
					}
				}
			}
		}
		
		
}
