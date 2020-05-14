package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoActionBenevole;


public class ControllerTestDaoActionBenevole {
	
	
	// Composants visuales
	
	@FXML
	private TextArea		textArea;
	
	
	// Autres champs
	
	@Inject
	private DaoActionBenevole	dao;
	
	private final int		id = 10;	
	
	
	// Actions
	
	@FXML
	private void doLister() {
		textArea.clear();
		for (Object item : dao.listerTout() ) {
//			textArea.appendText( UtilFX.objectToString( item ) );
			textArea.appendText(item.toString()); 
			// autrement cela n'affiche que les champs nouveau dans bénévole et pas ceux d'utilisateur
			
			textArea.appendText( "\n"  );
		}
		textArea.appendText( "\n"  );
		textArea.appendText(  "Test n°1 OK \n");;
	}
	
	@FXML
	private void doRetrouver() {
		textArea.clear();
		textArea.appendText( UtilFX.objectToString( dao.retrouver( id ) ) );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°2 OK \n");;
	}
	
	@FXML
	private void doModifier() {
		textArea.clear();
		dao.modifier( dao.retrouver( id ) );
		textArea.appendText(  "Test n°3 OK \n");;
	}
	
	@FXML
	private void doInsererSupprimer() {
		textArea.clear();
		int idNouveau = dao.inserer( dao.retrouver( id ));
		textArea.appendText( UtilFX.objectToString( dao.retrouver( idNouveau ) ) );
		dao.supprimer( idNouveau );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°4 OK \n");;
	}
	
}
