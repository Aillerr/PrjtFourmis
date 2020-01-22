
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
		
	    public void ActionFourmi(Fourmi f) {
	    	boolean finAction = false;
	    	Arbre A = f.getComport();
	    	while(!finAction) {
	    	int rnd = (int) (Math.random() * 4);	
	    	Comportements c = A.getNoeud();
	    	switch(c) {
	    	case GO : 
	            continue;
	    	case GO_LEFT : 
	    		if(c.equals(Comportements.GO_LEFT) || (rnd == 0)) {
	    			//Va à gauche
	    		}
	    		finAction=true;
	    		break;
	    	case GO_RIGHT : 
	    		if(c.equals(Comportements.GO_RIGHT) || (rnd == 1)) {
	    			//Va à droite
	    		}
	    		finAction=true;
	    		break;
	    	case GO_UP : 
	    		if(c.equals(Comportements.GO_UP) || (rnd == 2)) {
	    			//Va en haut
	    		}
	    		finAction=true;
	    		break;
	    	case GO_DOWN :
	    		if(c.equals(Comportements.GO_DOWN) || (rnd == 3)) {
	    			//Va en bas
	    		}
	    		finAction=true;
	    		break;
	    	case RECOLT :
	    		//Si il y a de la nourriture
	    			//Prend la nourriture : éditer la map pour l'enlever
	    			//Ajouter du score (A pris de la nourriture)
	    		f.setIsCarrying(true);
	    		finAction=true;
	    		break;
	    	case DEPOSE :
	    		//Si elle est sur la fourmilière
	    			//Ajouter du score (A ramené de la nourriture)
	    		f.setIsCarrying(false);
	    		finAction=true;
	    		break;
	    	case GO_HOME :
	    		//Se déplace dans la direction de la fourmilière (la plus proche)
	    		finAction=true;
	    		break;
	    	case IS_FOOD :
	    		//Si y'a de la bouffe sur la case
	    			A = A.getSousArbreGauche();
				//Sinon
					A = A.getSousArbreDroit();
	    		break;
	    	case IS_HOME :
	    		//Si la case est une fourmilière
	    			A = A.getSousArbreGauche();
				//Sinon
	    			A = A.getSousArbreDroit();
	    		break;
	    	default : //Les cas où on aura modifié les arbres et y'aura du null, ça fait rien et ça termine l'action
	    		finAction=true;
	    		break;
	    	}
	    	}
	    }
		
		public void boucleJeu() {
			
			for (int i=1;i<= generation;i++) {
				m.generer();
				
				
				
				for(int j=1; j <= nbFourmi; j++) {
					this.tabFourmi[j] = new Fourmi(); 
					int nbactions = Fourmi.getNbActions();
					while (nbactions !=0) {
						ActionFourmi(tabFourmi[j]);
						nbactions--;
						
					}
				}
			}
		}
		
		
}
