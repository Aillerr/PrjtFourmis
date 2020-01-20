public class Monde {
    private int taille, nbNourriture, nbFourmilliere, nbAction;
    Nourriture nourriture[];
    private String tab[][];

    public Monde(int taille, int nbNourriture, int nbFourmilliere,int nbAction) {
       this.taille = taille;
    	tab = new String [taille][taille];
    	nourriture = new Nourriture [nbNourriture];
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                tab[i][j]="R";
            }
        }
        this.nbFourmilliere=nbFourmilliere;
        this.nbNourriture=nbNourriture;
        this.nbAction = nbAction;
    }

    public Monde(int taille){
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                tab[i][j]="R";
            }
        }
    }

    public void generer(){

        while (!(nbNourriture == 0 && nbFourmilliere == 0)) {
        	int i = 0;
            int x=(int) (Math.random()*taille);
            int y=(int) (Math.random()*taille);
            int z = (int) (Math.random()*9);
            if (tab[x][y] == "R"){
                if (nbNourriture == 0){
                    tab[x][y] = "F";
                    nbFourmilliere--;
                }
                else {
                    tab[x][y] = "N";
                    nourriture[i] = new Nourriture (z);
                    nourriture[i].setPositionX(x);
                    nourriture[i].setPositionY(y);
                    nbNourriture--;
                    i++;
                }
            }
        }

    }

    public void afficher(){
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++) {
                System.out.print(tab[i][j]);
                System.out.print("   ");
            }
            System.out.println();

        }
    }
    
    public String getCase(int x,int y) {
    	return tab[x][y];
    }
    
    public int getNbNourriture () {
    	return nbNourriture;
    }
    
    public int getNbAction() {
    	return nbAction;
    }
    
    public void setAction (int nbAction) {
    	this.nbAction = nbAction;
    }

}
