import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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

    public Monde(int taille){ // monde vide
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

    /**
     * Génération d'un nouveau monde à partir d'un fichier
     * @param fichier
     * @throws IOException
     */
    public Monde(String fichier) throws IOException{
        BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
        String line =lecteur.readLine();
        String[] line_spl = line.split("");
        tab = new int [line_spl.length][line_spl.length];
        for(int j =0 ; j<line_spl.length; j++ ) {
            tab[0][j] = Integer.parseInt(line_spl[j]);
            if (tab[0][j] == 1) {
                nbNourriture++;
            } else if (tab[0][j] == 2) {
                nbFourmilliere++;
            }
            int i = 0;
            while ((line = lecteur.readLine()) != null) {
                System.out.println("line " + i + " : " + line);
                line_spl = line.split("");
                for (j = 0; j < line_spl.length; j++) {
                    tab[i][j] = Integer.parseInt(line_spl[j]);
                    if (tab[i][j] == 1) {
                        nbNourriture++;
                    } else if (tab[i][j] == 2) {
                        nbFourmilliere++;
                    }
                }
                i++;
            }
            taille = i;
        }
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



    public void download(String path,String filename)throws IOException {
        PrintWriter writer = new PrintWriter(path+filename, "UTF-8");
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                writer.print(tab[i][j]);
            }
            writer.println();
        }
        writer.close();
    }


}
