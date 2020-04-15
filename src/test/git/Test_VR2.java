package test.git;

public class Test_VR2 {
	
	
	public void bonjour() {
		System.out.println( "Bye Bye" );
		System.out.println( "Ca va ?" );
	}
	
	
	private String[] adresses = {
			"14 Rue Mozart, Montrouge",
			"77 Rue Picasso, Toulouse", 
			"53 Rue des fleurs, Brives-La-Gaillarde",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
