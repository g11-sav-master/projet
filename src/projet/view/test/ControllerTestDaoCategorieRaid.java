package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoCategorieRaid;


public class ControllerTestDaoCategorieRaid {
	
	
	// Composants visuales
	
	@FXML
	private TextArea		textArea;
	
	
	// Autres champs
	
	@Inject
	private DaoCategorieRaid	dao;
	
	private final int		id = 3;	
	
	
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
		textArea.appendText( UtilFX.objectToString( dao.retrouverCategorieRaid( id ) ) );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°2 OK \n");;
	}
	
	@FXML
	private void doModifier() {
		textArea.clear();
		dao.modifierCategorieRaid(( dao.retrouverCategorieRaid( id ) ));
		textArea.appendText(  "Test n°3 OK \n");;
	}
	
	@FXML
	private void doInsererSupprimer() {
		textArea.clear();
		int idNouveau = dao.insererCategorieRaid( dao.retrouverCategorieRaid( id ));
		textArea.appendText( UtilFX.objectToString( dao.retrouverCategorieRaid( idNouveau ) ) );
		dao.supprimerCategorieRaid( idNouveau );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°4 OK \n");;
	}
	
}
