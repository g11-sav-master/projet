package test.git;

public class Test_MN2 {
	
	
	public void bonjour() {
		System.out.println( "YOSHI" );
		System.out.println( "ça va ?");
	}
	
	
	private String[] adresses = {
			"14 Rue Mozart, Montreuil",
			"77 Rue Picasso, Toulouse", 
			"53 Rue des fleurs, Brive",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
