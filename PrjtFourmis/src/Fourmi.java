
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
    	Arbre A = new Arbre(comport);
    	while(!finAction) {
    		
    	Comportements c = comport.getNoeud();
    	switch(c) {
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
    		//Prend la nourriture : éditer la map pour l'enlever
    		//Ajouter du score (A pris de la nourriture)
    		isCarrying = true;
    		finAction=true;
    		break;
    	case DEPOSE :
    		//Ajouter du score (A ramené de la nourriture)
    		isCarrying = false;
    		finAction=true;
    		break;
    	case GO_HOME :
    		//Se déplace dans la direction de la fourmilière (la plus proche)
    		finAction=true;
    		break;
    	case IS_FOOD :
    		//if(nourriture sur la case) {
    			A = A.getSousArbreGauche();
    		//}
    		break;
    	case IS_HOME :
    		//if(fourmilière sur la case) {
			A = A.getSousArbreDroit();
			//}
    		break;
    	default :
    		break;
    	}
    	}
    }
    
    //Croiser 2 fourmis
 
    public Fourmi Croisement(Fourmi f) {
    	Arbre a =null;
    	int n = (int) (Math.random() * 4);	//Renvoie un entier aléatoire entre 0 et 3
    	System.out.println(n);
    	switch(n) {
    	case 0:	//On prend le comportement de la fourmi sur laquelle on appelle la fonction et on change son fils gauche par celui de l'autre fourmi
    		a=this.comport;
    		a.setSousArbreGauche(f.getComport().getSousArbreGauche());
    		break;
    	case 1: //On prend le comportement de la fourmi sur laquelle on appelle la fonction et on change son fils droit par celui de l'autre fourmi
    		a=this.comport;
    		a.setSousArbreDroite(f.getComport().getSousArbreDroit());
    		break;
    	case 2: //On prend le comportement de la fourmi en paramètre et on change son fils gauche par celui de l'autre fourmi
    		a=f.getComport();
    		a.setSousArbreGauche(this.getComport().getSousArbreGauche());
    		break;
    	case 3: //On prend le comportement de la fourmi en paramètre et on change son fils droit par celui de l'autre fourmi
    		a=f.getComport();
    		a.setSousArbreDroite(this.getComport().getSousArbreDroit());
    		break;
    	default:
    		break;
    	}
    	Fourmi nouvelle = new Fourmi(f.getNumGeneration()+1, a);
    	return nouvelle;
    }
}
