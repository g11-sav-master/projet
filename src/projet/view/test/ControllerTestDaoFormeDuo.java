package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoFormeDuo;
import projet.data.FormeDuo;


public class ControllerTestDaoFormeDuo {
	
	
	// Composants visuales
	
	@FXML
	private TextArea		textArea;
	
	
	// Autres champs
	
	@Inject
	private DaoFormeDuo	dao;
	
	private final int		idPartDuo = 1, idUtilisateur = 31;
	
	
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
		textArea.appendText( UtilFX.objectToString( dao.retrouver(idPartDuo, idUtilisateur) ) );
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°2 OK \n");;
	}
	
	@FXML
	private void doModifier() {
		textArea.clear();
		dao.modifier(( dao.retrouver(idPartDuo, idUtilisateur) ));
		textArea.appendText(  "Test n°3 OK \n");;
	}
	
	@FXML
	private void doInsererSupprimer() {
		textArea.clear();
		
		int idPartDuoTest=1, idUtilisateurTest=32;
		
		FormeDuo formeDuoTest = new FormeDuo();
		formeDuoTest.setEstCapitaine(true);
		formeDuoTest.setIdPartDuo(idPartDuoTest);
		formeDuoTest.setIdUtilisateur(idUtilisateurTest);
		
		dao.inserer( formeDuoTest );
		textArea.appendText( UtilFX.objectToString( dao.retrouver(idPartDuoTest, idUtilisateurTest) ) );
		dao.supprimer( idPartDuoTest, idUtilisateurTest);
		textArea.appendText( "\n\n"  );
		textArea.appendText(  "Test n°4 OK \n");;
	}
	
}
