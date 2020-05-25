package projet.view.accueil;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;


public class ControllerAccueilListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<String>			listViewDossierAttentes;
	@FXML
	private ListView<String>			listViewNbrRepas;		
	@FXML
	private ListView<String>			listViewPoste;

	// Autres champs
	
	@Inject
	private ModelAccueil		modelAccueil;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listViewDossierAttentes.setItems( modelAccueil.listerDossierAttentes() );
		listViewDossierAttentes.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		listViewNbrRepas.setItems( modelAccueil.listerNbrRepas() );
		listViewNbrRepas.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		listViewPoste.setItems( modelAccueil.listerPosteNonRempli() );
		listViewPoste.setCellFactory( UtilFX.cellFactory( item -> item.toString() ));
		
	}
	
	public void refresh() {
		modelAccueil.actualiserListe();
		listViewDossierAttentes.requestFocus();
	}

}
