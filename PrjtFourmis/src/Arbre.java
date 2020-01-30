
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
	
	public Arbre(int rate) {
		
		int rnd = (int) (Math.random() * (Comportements.values().length-1));
		this.valeur = Comportements.values()[rnd+1];
		rnd = (int) (Math.random() * 100);
		if(this.valeur.isQuestion() && rnd<rate) {
			this.fg= new Arbre(rate);// /2
			this.fd= new Arbre(rate);// /2
		}
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
    	return (this.fd==null && this.fg==null);
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
    	if(comp.isQuestion() && !this.isFeuille()) { 

			if (this.sameKids() && this.fd.isFeuille() && this.fg.isFeuille()) {

				this.valeur = this.fg.valeur;
				this.fd = null;
				this.fg = null;
			} else {
				if (this.sameParentLeft()) {

					if (this.fg.isFeuille()) {
						this.fg.valeur = Comportements.NOTHING;
					} else {
						this.fg = this.fg.fg;
						this.fg.fg = null;
						this.fg.fd = null;
					}

				}
				if (this.sameParentRight()) {
					if (this.fd.isFeuille()) {
						this.fd.valeur = Comportements.NOTHING;
					} else {
						this.fd.valeur = this.fd.fd.valeur;
						this.fd.fg = null;
						this.fd.fd = null;
					}
				}
				if (this.sameKids()) {

					this.valeur = this.fg.valeur;
					this.fd = null;
					this.fg = null;
				}

				if (this.fd != null) {
					this.fd.correctComport();
				}

				if (this.fg != null) {
					this.fg.correctComport();
				}
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

    //Comparer
    
    public boolean compareTo(Arbre abr) {

    	if((this.fd == null && abr.fd != null)
    			|| (this.fd != null && abr.fd == null)
        	||(this.fg == null && abr.fg != null) 
        		|| (this.fg != null && abr.fg == null)
        	||(this.isFeuille() && !abr.isFeuille())
        	||(!this.isFeuille() && abr.isFeuille()))   	
    	{ 
    		return false;	
    	}
    	
    	//Font la même chose qu'au dessus
    	/*if(this.fd == null && abr.fd != null)
    		return false;
    	if(this.fd != null && abr.fd == null)
    		return false;
    	if(this.fg == null && abr.fg != null)
    		return false;
    	if(this.fg != null && abr.fg == null)
    		return false;
    	if(this.isFeuille() && !abr.isFeuille())
    		return false;
    	if(!this.isFeuille() && abr.isFeuille())
    		return false;
    	*/
    	
    	if(this.isFeuille() && abr.isFeuille()) {
    		return this.valeur.equals(abr.valeur);
    	}
    	
    	if(this.fg == null && abr.fg == null) {
    		return (this.valeur.equals(abr.valeur) && this.fd.compareTo(abr.fd));
    	}else if(this.fd == null && abr.fd == null){
    		return (this.valeur.equals(abr.valeur) && this.fg.compareTo(abr.fg));
    	}else {
    		return (this.valeur.equals(abr.valeur) && this.fd.compareTo(abr.fd) && this.fg.compareTo(abr.fg));
    	}

    }

}
