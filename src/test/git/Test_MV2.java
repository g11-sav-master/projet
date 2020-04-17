package test.git;

public class Test_MV2 {
	
	
	public void bonjour() {
		System.out.println( "Bonjoour" );
		System.out.println( "Ca va ?" );
	}
	
	
	private String[] adresses = {
<<<<<<< HEAD
			"99 Rue Mozart, Paris",
=======
			"14 Rue Mozart, Montreuil",
>>>>>>> branch 'master' of https://github.com/g11-sav-master/projet.git
			"77 Rue Picasso, Toulouse", 
<<<<<<< HEAD
			"111 Rue des fleurs, Limoges",
=======
			"53 Rue des fleurs, Brive",
>>>>>>> branch 'master' of https://github.com/g11-sav-master/projet.git
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
