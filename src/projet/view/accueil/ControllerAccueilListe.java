package projet.view.accueil;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;


public class ControllerAccueilListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Participant>		listViewDossierAttentes;
	@FXML
	private ListView<String>			listViewNbrRepas;			


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelAccueil		modelAccueil;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listViewDossierAttentes.setItems( modelAccueil.listerDossierAttentes() );
		listViewDossierAttentes.setCellFactory(  UtilFX.cellFactory( item -> item.getNom().toUpperCase()+" "+item.getPrenom() ));
		listViewNbrRepas.setItems( modelAccueil.listerNbrRepas() );
		listViewNbrRepas.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		
	}
	
	public void refresh() {
		modelAccueil.actualiserListe();
		UtilFX.selectInListView( listViewDossierAttentes, modelAccueil.getCourant() );
		listViewDossierAttentes.requestFocus();
	}

}
