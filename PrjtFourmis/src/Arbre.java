
public class Arbre { //Modifier l'affichage pour que ça soit friendly et qu'on voie bien les comportements
	private Comportements valeur;
	private Arbre fg, fd;
	
	//Constructeurs
	
	public Arbre(Comportements val) {
		this.valeur=val;
	}
	
	public Arbre(Comportements x, Arbre g, Arbre d) {
        valeur = x;
        fg = g;
        fd = d;
    }

	
	//Get
	
	public Comportements getNoeud() {
        return(valeur);
    }

    public Arbre getSousArbreGauche() {
        return(fg);
    }   

    public Arbre getSousArbreDroit() {
        return(fd);
    }
    
    //Set
    
    public void setNoeud(Comportements comp) {
    	this.valeur=comp;
    }
    
    public void setSousArbreGauche(Arbre ng) {
    	this.fg=ng;
    }
    
    public void setSousArbreDroite(Arbre nd) {
    	this.fd=nd;
    }
    
    //Tests
    
    public boolean isFeuille() {
    	return (this.fd.valeur==null && this.fg.valeur==null);
    }
    
    public boolean sameKids() {
    	return this.fd.valeur.equals(this.fg.valeur);
    }
    
    public boolean sameParentLeft() {
    	return this.valeur.equals(this.fg.valeur);
    }
    
    public boolean sameParentRight() {
    	return this.valeur.equals(this.fd.valeur);
    }
    
    
    //Corriger l'arbre 
    
      
    public void correctComport() {
    	Comportements comp = this.getNoeud();
    	if(comp.equals(Comportements.IS_FOOD) || comp.equals(Comportements.IS_HOME)) { //Rajouter les comportements IS_....
    		
    		if(this.sameKids()) {
    			this.valeur=this.fg.valeur;
    	    	this.fd=null;
    	    	this.fg=null;
    		}
    		
    		if(this.sameParentLeft()) {
    			this.fg.valeur=this.fg.fg.valeur;
    			this.fg.fg=null;
    			this.fg.fd=null;
    		}
    		
    		if(this.sameParentRight()) {
    			this.fd.valeur=this.fd.fd.valeur;
    			this.fd.fg=null;
    			this.fd.fd=null;
    		}
   		
    	}
    }
    
     
    //Affichage de l'arbre
    /*Affichage préfixe : - Racine
    						-fg
    							-fg
    							-fd
    						-fd
    							-fg
    							-fd
    */
    public String toString () {
        return this.toString("");
    }

    public String toString (String indent) {
        if (this.fg == null && this.fd == null) {
            return indent + this.valeur;
        }

        return indent + this.valeur
        + "\n" + indent + "-  " + this.fg.toString(indent + "  ")
        + "\n" + indent + "-  " + this.fd.toString(indent + "  ");
    }

    

}
