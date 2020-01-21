
public class Fourmi {
    private int score; 
    private int numGeneration;
    private Arbre comport;
    private boolean isCarrying;
    private static final int nbActions = 60;
    
    //Constructeurs
    
    public Fourmi() {
    	this.score=0;
    	this.numGeneration=1;
    	this.isCarrying=false;
  
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

    
    //Set

    
    public void setScore(int i) {
    	this.score += i;
    }
    
    public void setIsCarrying(boolean b) {
    	isCarrying = b;
    }

    
    //Action d'une fourmi        
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