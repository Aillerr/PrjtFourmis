import java.util.Random;

public class Monde {
    private int taille, nbNourriture, nbFourmilliere;
    private int tab[][];

    public Monde(int taille, int nbNourriture, int nbFourmilliere) {
        tab = new int [taille][taille];
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                tab[i][j]=0;
            }
        }
        this.nbFourmilliere=nbFourmilliere;
        this.nbNourriture=nbNourriture;
    }

    public Monde(int taille){
        for(int i=0;i<taille;i++){
            for(int j=0;i<taille;j++){
                tab[i][j]=0;
            }
        }
    }

    public void generer(){

        while (nbNourriture != 0 && nbFourmilliere !=0) {
            int x=(int) Math.random()*taille;
            int y=(int) Math.random()*taille;
            if (tab[x][y] == 0){
                if (nbNourriture == 0){
                    tab[x][y] = 2;
                    nbFourmilliere--;
                }
                else {
                    tab[x][y] = 1;
                    nbNourriture--;
                }
            }
        }

    }

    public void afficher(){
        for(int i=0;i<taille;i++){
            for(int j=0;i<taille;j++) {
                System.out.print(tab[i][j]);
            }
            System.out.println("a");
        }
    }


}
