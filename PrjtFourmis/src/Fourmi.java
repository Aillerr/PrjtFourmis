
public class Fourmi {
    private int score; 
    private int numGeneration;
    private Arbre comport;
    private boolean isCarrying;
    
    //Constructeurs
    
    public Fourmi(int n) {
    	this.score=0;
    	this.numGeneration=n;
    	this.isCarrying=false;
  
    	this.comport = new Arbre(75);
    }
    
    public Fourmi(int n, Arbre A) {
    	this(n);
    	this.comport = A;
    }
    
    
    
    
    
    //Get
    
    public int getNumGeneration() {
    	return this.numGeneration;
    }
    
    public Arbre getComport() {
    	return this.comport;
    }
    
    //Action d'une fourmi
    
    public void ActionFourmi() {
    	boolean finAction = false;
    	Arbre A = comport;
    	while(!finAction) {
    		
    	Comportements c = comport.getNoeud();
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
    			isCarrying = true;
    		finAction=true;
    		break;
    	case DEPOSE :
    		//Si elle est sur la fourmilière
    			//Ajouter du score (A ramené de la nourriture)
    		isCarrying = false;
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
    
    //Croiser 2 fourmis
 
    public Fourmi Croisement (Fourmi f) {
        Arbre arb = f.getComport(),
        		source = this.comport; 

        int rnd = (int) (Math.random() * 4);
        //System.out.println(rnd);
        if (rnd >= 2) {							//Si 0 ou 1 on prend le comportement de la fourmi en paramètre, sinon l'autre
            arb = this.comport;
            source = f.getComport();
        }

        if (rnd%2 == 0)
            arb.setSousArbreGauche(source.getSousArbreGauche());	//Si 0 ou 2 on remplace le fils gauche par celui de l'autre fourmi
        else
            arb.setSousArbreDroite(source.getSousArbreDroit());	//Sinon le fils droit

        return (new Fourmi(f.getNumGeneration() + 1, arb));
    }
}
