package test.git;

public class Test_MN2 {
	
	
	public void bonjour() {
		System.out.println( "YOSHI" );
		System.out.println( "ça va ?");
	}
	
	
	private String[] adresses = {

			"99 Rue Mozart, Paris",
			"77 Rue Picasso, Toulouse", 
			"111 Rue des fleurs, Limoges",
			};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
