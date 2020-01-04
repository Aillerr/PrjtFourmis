public class main {
	public static void main (String []args)
	{
		//Monde m = new Monde (20,10,5);
		//m.generer();
		//m.afficher();
		
		// TESTS AXEL, COMMENTER POUR QUE CA SOIT PLUS SIMPLE POUR VOUS
		//Qqs tests pour vérifier les constructeurs et la gestion
		/*
		Arbre a = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.RECOLT), new Arbre(Comportements.DEPOSE, new Arbre(Comportements.IS_FOOD), new Arbre(Comportements.GO_UP)));
		Arbre b = new Arbre(Comportements.DEPOSE, new Arbre(Comportements.GO_HOME), new Arbre(Comportements.GO_DOWN, new Arbre(Comportements.RECOLT), new Arbre(Comportements.GO_LEFT)));
		Fourmi f = new Fourmi(1, a);
		System.out.println(a.compareTo(b));
		
		System.out.println();
		System.out.println(f.getComport().toString());
		System.out.println();
		
		Fourmi f1 = new Fourmi(1,b);
		
		System.out.println();
		System.out.println(f1.getComport().toString());
		System.out.println();
		
		Fourmi f2;
		f2 = f.Croisement(f1);
		
		//System.out.println(f2.getNumGeneration());
		
		System.out.println();
		System.out.println(f2.getComport().toString());
		System.out.println();
		
		//f.getComport().correctComport();
		System.out.println();
		
		Arbre c = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.GO_DOWN),  new Arbre(Comportements.GO_UP)), new Arbre(Comportements.DEPOSE));
		System.out.println(c.toString());
		System.out.println();
		
		//System.out.println(c.sameKids());
		c.correctComport();
		
		System.out.println(c.toString());
		System.out.println();
		
		System.out.println(Comportements.GO.ordinal());
		*/
		for(int i=0; i<100; i++) {
			System.out.println("============================");
			System.out.println("Arbre : " + i );
			Arbre abr = new Arbre(75);
			System.out.println(abr.toString());
			System.out.println();
			abr.correctComport();
			System.out.println();
			System.out.println(abr.toString());
		}
		
		
		/*
		System.out.println("============================");

		Arbre c = new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.IS_FOOD, new Arbre(Comportements.GO_DOWN),  new Arbre(Comportements.GO_UP)), new Arbre(Comportements.IS_FOOD));
		Arbre abr2 =c;
		System.out.println(c.toString());
		c.correctComport();
		
		System.out.println();
		System.out.println(c.toString());
		*/
		//FIN TESTS AXEL
		
		
		
	}

	

}

