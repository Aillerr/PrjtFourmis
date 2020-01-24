
public class Fourmi {
    private int score; 
    private int numGeneration;
    private int positionX;
    private int positionY;
    private Arbre comport;
    private boolean isCarrying;
    private static final int nbActions = 60;
    
    //Constructeurs
    
    public Fourmi() {
    	this.score=0;
    	this.numGeneration=1;
    	this.isCarrying=false;
    	this.positionX=0;
    	this.positionY=0;
    	this.comport = new Arbre(75);
    }
    
    public Fourmi(int n) {
    	this();
    	
    	this.numGeneration=n;	
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
    
    public static int getNbActions() {
    	return nbActions;
    }
    
    public boolean getCarrying() {
    	return isCarrying;
    }
    
    public int getScore() {
    	return score;
    }
    
    public int getPositionX () {
    	return positionX;
    }
    
    public int getPositionY() {
    	return positionY;
    }
   

    
    //Set

    
    public void setScore(int i) {
    	this.score += i;
    }
    
    public void setIsCarrying(boolean b) {
    	isCarrying = b;
    }
    
    public void setPositionX(int x) {
    	positionX = x;
    }
    
    public void setPositionY(int y) {
    	positionY = y;
    }
    
    //Action d'une fourmi     
    public void ActionFourmi(Monde m) {
    	boolean finAction = false;
    	Arbre A = getComport();
    	int x = getPositionX();
    	int y = getPositionY();
        int z = (int) (Math.random()*4);
    	while(!finAction) {
    		
    	Comportements c = A.getNoeud();
    	switch(c) {
    	case GO : 
    		continue;
    	case GO_LEFT :
    		if (z == 0 || c.equals(Comportements.GO_LEFT)) {
    		setPositionY(y--);
    		finAction=true;
    		break;
    		}
    	case GO_RIGHT : 
    		if (z == 1 || c.equals(Comportements.GO_RIGHT)) {
        		setPositionY(y++);
        		finAction=true;
        		break;
        		}
    	case GO_UP : 
    		if (z == 2 || c.equals(Comportements.GO_UP)) {
        		setPositionY(x++);
        		finAction=true;
        		break;
        		}
    	case GO_DOWN : 
    		if (z == 0 || c.equals(Comportements.GO_DOWN)) {
        		setPositionY(x--);
        		finAction=true;
        		break;
        		}
    	case RECOLT :
    		if (m.estNourriture (positionX, positionY)) {
    			if (getCarrying() == false) {
    				setScore(10);
    	    		isCarrying=true;
    			}
    		}
    		finAction=true;
    		break;
    	case DEPOSE :
    		if (m.estFourmiliere (positionX, positionY)) {
    			if (getCarrying() == true) {
    				setScore(20);
    	    		isCarrying=false;
    			}
    		}
    		finAction=true;
    		break;
    	case GO_HOME :
    		//Se déplace dans la direction de la fourmilière (la plus proche)
    		finAction=true;
    		break;
    	case IS_FOOD :
    		if (m.estNourriture(positionX, positionY)) {
    			A = A.getSousArbreGauche();
    		}
    		else {
				A = A.getSousArbreDroit();
    		}
    		break;
    	case IS_HOME :
    		if (m.estFourmiliere(positionX, positionY)) {
    			A = A.getSousArbreGauche();
    		}
    		else {
				A = A.getSousArbreDroit();
    		}
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