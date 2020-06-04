package projet.view.systeme;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import jfox.commun.exception.ExceptionValidation;
import projet.dao.DaoCompte;
import projet.data.Compte;


public class ModelConnexion {
	
	// Logger
	public static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );
	
	
	// Données observables 
	
	// Vue connexion
	private final Compte			courant = new Compte();

	// Compte connecté
	private final Property<Compte>	compteActif = new SimpleObjectProperty<>();

	
	// Autres champs
	@Inject
	private DaoCompte	daoCompte;
	

	// Getters 
	
	public Compte getCourant() {
		return courant;
	}
	
	public Property<Compte> compteActifProperty() {
		return compteActif;
	}
	
	public Compte getCompteActif() {
		return compteActif.getValue();
	}
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {
		//ADMINISTRATEUR
		courant.setPseudo( "isidu25" );
		courant.setMotDePasse( "2525isirob" );

//		// RESPONSABLE PARTICIPANT
//		courant.setPseudo( "angietan65" );
//		courant.setMotDePasse( "angtanvapo45" );
////		
//		// RESPONSABLE BENEVOLE
//		courant.setPseudo( "victan65" );
//		courant.setMotDePasse( "victanvapo45" );
//		
//		// NON DESIGNE
//		courant.setPseudo( "daudaudu01" );
//		courant.setMotDePasse( "duciva54" );
		
	}
	
	
	// Actions


	public void ouvrirSessionUtilisateur() {

		Compte compte = daoCompte.validerAuthentification(
					courant.pseudoProperty().getValue(), courant.motDePasseProperty().getValue() );
		
		if( compte == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
//			Platform.runLater( () -> compteActif.setValue( compte ) );
			compteActif.setValue( compte );
		}
	}
	

	public void fermerSessionUtilisateur() {
		compteActif.setValue( null );
	}

}