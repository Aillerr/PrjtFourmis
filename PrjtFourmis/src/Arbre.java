
public class Arbre { //Modifier l'affichage pour que ça soit friendly et qu'on voie bien les comportements
					//Et aussi implementer la valeur en tant que comportement. Les tests sur les comportements seront pendant un round d'évolution.
	private int valeur;
	private Arbre fg, fd;
	
	//Constructeurs
	
	public Arbre(int val) {
		this.valeur=val;
	}
	
	public Arbre(int x, Arbre g, Arbre d) {
        valeur = x;
        fg = g;
        fd = d;
    }
	
	//Get
	
	public int getValeur() {
        return(valeur);
    }

    public Arbre getSousArbreGauche() {
        return(fg);
    }   

    public Arbre getSousArbreDroit() {
        return(fd);
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
	System.out.println(getValeur());
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
	System.out.println(getValeur());
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
	System.out.println(getValeur());
    }
    
    /**
     * Teste si deux arbres sont egaux, meme valeurs et meme disposition
     * @param a l'arbre a comparer a b
     * @param b l'arbre a comparer a a
     * @return un boolean indiquant si les arbres sont egaux
     */
    public static boolean arbresEgaux(Arbre a, Arbre b) {
	if ((a == null) && (b == null))
	    return true;
	if ((a == null) && (b != null))
	    return false;
	if ((a != null) && (b == null))
	    return false;

	// A ce point, a et b != null, on peut acceder a leurs champs
	if (a.getValeur() != b.getValeur())
	    return false;
	return (arbresEgaux(a.getSousArbreGauche(), b.getSousArbreGauche()) 
		&& arbresEgaux(a.getSousArbreDroit(), b.getSousArbreDroit()));
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
