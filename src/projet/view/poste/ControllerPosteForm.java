package projet.view.poste;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerPosteForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldIdPoste;
	@FXML
	private TextField		textFieldIdRaid;
	@FXML
	private TextField		textFieldNbrBen;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelPoste modelposte;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Poste courant = modelposte.getCourant();
		textFieldIdPoste.textProperty().bindBidirectional(courant.id_posteProperty(), new IntegerStringConverter());
		textFieldIdRaid.textProperty().bindBidirectional(courant.id_raidProperty(), new IntegerStringConverter());
		textFieldNbrBen.textProperty().bindBidirectional(courant.nbr_benevProperty(), new IntegerStringConverter());
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PosteListe );
	}
	
	@FXML
	private void doValider() {
		modelposte.validerMiseAJour();
		managerGui.showView( EnumView.PosteListe );
	}
	
}
