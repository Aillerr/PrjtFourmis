public class Monde {
    private int taille, nbNourriture, nbFourmilliere, nbAction;
    private Case tab[][];

    public Monde(int taille, int nbNourriture, int nbFourmilliere,int nbAction) {
       this.taille=taille;
    	tab = new Case [taille][taille];
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                tab[i][j]= new Case(i,j,'R');
            }
        }
        this.nbFourmilliere=nbFourmilliere;
        this.nbNourriture=nbNourriture;
        this.nbAction = nbAction;
    }
    
    public Monde(Monde m) {
    	this.taille = m.taille;
    	this.nbFourmilliere = m.nbFourmilliere;
    	this.nbNourriture = m.nbNourriture;
    	this.nbAction = m.nbAction;
    	this.tab = new Case [taille][taille];
    	for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){         	
                this.tab[i][j] = new Case(i, j, m.tab[i][j].getValue());
            }
        }
    }

    public Monde(int taille){
    	tab = new Case [taille][taille];
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
            	tab[i][j]= new Case(i,j,'R');
            }
        }
        this.nbFourmilliere= 0;
        this.nbNourriture= 0;
        this.nbAction = 0;
    }

    public void generer(){

        while (!(nbNourriture == 0 && nbFourmilliere == 0)) {
        	
            int x=(int) (Math.random()*taille);
            int y=(int) (Math.random()*taille);
            int z = (int) (Math.random()*9); 
          

            if (tab[x][y].getValue() == 'R'){
            	
                if (nbNourriture == 0){
                    tab[x][y] = new Fourmiliere(0,'F');
                    nbFourmilliere--;
                   
                }
                else {
                    tab[x][y] = new Nourriture(z+1, 'N');
                    nbNourriture--;

                }
            }
        }
       
    }

    public void afficher(){
        for(int row=0;row<taille;++row){      	
            for(int col=0;col<taille;++col) {
            	if(tab[row][col].getValue() != 'R') tab[row][col].afficherCase();
            	else System.out.print(" ");
                if(col<taille-1) System.out.print("   |   ");
            }
            System.out.println();
            if(row<taille-1) System.out.println("-------------------------------------");

        }
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
    
    public Case getTable(int i, int j) {
    	return this.tab[i][j];
    }
    
    

}
