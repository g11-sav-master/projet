package projet.commun;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class Roles {
	
	// Champs statiques
	
	public static final String ADMINISTRATEUR	= "Administrateur";
	public static final String RESPONSABLE_PARTICIPANT	= "Responsable participant";
	public static final String RESPONSABLE_BENEVOLE	= "Responsable Bénévole";
	
	
	private static final List<String>	roles = Collections.unmodifiableList( Arrays.asList( 
			ADMINISTRATEUR,			
			RESPONSABLE_PARTICIPANT, 
			RESPONSABLE_BENEVOLE
	) );

	private static final String[]	 	libellés = new String[] {
			"Administrateur",
			"Utilisateur",
	};
	
	
	// Constructeur privé qui empêche l'instanciation de la classe
	private Roles() {
	}
	
	
	// Actions

	public static List<String> getRoles() {
		return roles;
	}
	
	public static String getLibellé( String role ) {
		int index = roles.indexOf( role );
		if ( index == -1 ) {
			throw new IllegalArgumentException();
		} 
		return libellés[index];
	}

}
