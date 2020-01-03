
public class Arbre { //Modifier l'affichage pour que ça soit friendly et qu'on voie bien les comportements
					//Et aussi implementer la valeur en tant que comportement. Les tests sur les comportements seront pendant un round d'évolution.
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

	
	public Arbre(Arbre A) {
		valeur=A.valeur;
		fg=A.fg;
		fd=A.fd;
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
    
    public void setSousArbreGauche(Arbre ng) {
    	this.fg=ng;
    }
    
    public void setSousArbreDroite(Arbre nd) {
    	this.fd=nd;
    }
    
    //Affichage de l'arbre
    
    public String toString() {
        return toString("\t");
    }

    public String toString(String s) {
	if (fg!=null) {
	if (fd!=null) 
	    return(s+valeur+"\n"+fg.toString(s+"\t")+fd.toString(s+"\t"));
	else
	    return(s+valeur+"\n"+fg.toString(s+"\t")+"\n");
        }
        else 
	if (fd!=null) 
	    return(s+valeur+"\n\n"+fd.toString(s+"\t"));
	else
	    return(s+valeur+"\n");
    }

   /**
     * Affiche l'arbre selon un parcours prefixe
     */
    public void ParcoursPrefixe() {
	System.out.println(getNoeud());
	if (getSousArbreGauche() != null)
	    getSousArbreGauche().ParcoursPrefixe();
	if (getSousArbreDroit() != null)
	    getSousArbreDroit().ParcoursPrefixe();
    }

   /**
     * Affiche l'arbre selon un parcours infixe
     */
    public void ParcoursInfixe() {
	if (getSousArbreGauche() != null)
	    getSousArbreGauche().ParcoursInfixe();
	System.out.println(getNoeud());
	if (getSousArbreDroit() != null)
	    getSousArbreDroit().ParcoursInfixe();
    }

   /**
     * Affiche l'arbre selon un parcours postfixe
     */
    public void ParcoursPostfixe() {
	if (getSousArbreGauche() != null)
	    getSousArbreGauche().ParcoursPostfixe();
	if (getSousArbreDroit() != null)
	    getSousArbreDroit().ParcoursPostfixe();
	System.out.println(getNoeud());
    }
    

    
    /**
     * @param a un arbre
     * @return la hauteur de l'arbre a
     */
    public static int hauteur(Arbre a) {
	if (a == null)
	    return 0;
	else
	    return (1 + Math.max(hauteur(a.getSousArbreGauche()), hauteur(a.getSousArbreDroit())));
    }

    /**
     * @param value la valeur a inserer dans l'arbre
     */
    public void insertion(String value) {
	
    }
}
