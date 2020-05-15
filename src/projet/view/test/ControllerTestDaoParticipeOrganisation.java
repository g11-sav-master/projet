package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoParticipeOrganisation;
import projet.data.ParticipeOrganisation;


public class ControllerTestDaoParticipeOrganisation {
	
	
	// Composants visuales
	
	@FXML
	private TextArea		textArea;
	
	
	// Autres champs
	
	@Inject
	private DaoParticipeOrganisation	dao;
	
	private final int		idUtilisateur = 1, idRaid=1;
	
	
	// Actions
	
	@FXML
	private void doLister() {
		textArea.clear();
		for (Object item : dao.listerTout() ) {
			textArea.appendText( UtilFX.objectToString( item ) );
			textArea.appendText( "\n"  );
		}
		textArea.appendText( "\n"  );
		textArea.appendText(  "Test n°1 OK \n");;
	}
	
	@FXML
	private void doRetrouver() {
		textArea.clear();
		textArea.appendText( UtilFX.objectToString( dao.retrouver(idUtilisateur, idRaid) ) );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°2 OK \n");;
	}
	
	@FXML
	private void doModifier() {
		textArea.clear();
		dao.modifier(( dao.retrouver(idUtilisateur, idRaid) ));
		textArea.appendText(  "Test n°3 OK \n");;
	}
	
	@FXML
	private void doInsererSupprimer() {
		textArea.clear();
		
		int idUtilisateurTest=30, idRaidTest=1, nbreRepas=100;
		
		ParticipeOrganisation participeOrganisation = new ParticipeOrganisation();
		participeOrganisation.setIdRaid(idRaidTest);
		participeOrganisation.setIdUtilisateur(idUtilisateurTest);
		participeOrganisation.setNbreRepas(nbreRepas);
		
		dao.insererParticipeOrganisation( participeOrganisation );
		textArea.appendText( UtilFX.objectToString( dao.retrouver(idUtilisateurTest,idRaidTest) ) );
		dao.supprimerCategorieRaid( idUtilisateurTest,idRaidTest);
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°4 OK \n");;
	}
	
}
