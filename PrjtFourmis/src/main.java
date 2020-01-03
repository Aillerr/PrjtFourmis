public class main {
	public static void main (String []args)
	{
		//Monde m = new Monde (20,10,5);
		//m.generer();
		//m.afficher();
		
		
		//Qqs tests pour vérifier les constructeurs et la gestion
		 
		Arbre a = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.RECOLT), new Arbre(Comportements.DEPOSE, new Arbre(Comportements.IS_FOOD), new Arbre(Comportements.GO_UP)));
		Arbre b = new Arbre(Comportements.DEPOSE, new Arbre(Comportements.GO_HOME), new Arbre(Comportements.GO_DOWN, new Arbre(Comportements.RECOLT), new Arbre(Comportements.GO_LEFT)));
		Fourmi f = new Fourmi(1, a);
		f.getComport().ParcoursPrefixe();
		System.out.println();
		Fourmi f1 = new Fourmi(1,b);
		f1.getComport().ParcoursPrefixe();
		System.out.println();
		Fourmi f2;
		f2 = f.Croisement(f1);
		System.out.println(f2.getNumGeneration());
		f2.getComport().ParcoursPrefixe();
		
	}
	

}

