
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
	    		
	    	Comportements c = A.getNoeud();
	    	switch(c) {
	    	case GO : 
	    		//Va dans une direction aléatoire (chiffre aléatoire entre 0 et3, chacun une direction
	    		finAction=true;
	    		break;
	    	case GO_LEFT : 
	    		//Va à gauche
	    		finAction=true;
	    		break;
	    	case GO_RIGHT : 
	    		//Va à droite
	    		finAction=true;
	    		break;
	    	case GO_UP : 
	    		//Va en haut
	    		finAction=true;
	    		break;
	    	case GO_DOWN : 
	    		//Va en bas
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
					
					while (Fourmi.getNbActions() !=0) {
						ActionFourmi(tabFourmi[j]);
					}
				}
			}
		}
		
		
}
