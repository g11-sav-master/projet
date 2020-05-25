package projet.view.validation_medicale;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerValidationForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldIdValidation;
	@FXML
	private ComboBox		comboBoxUtilisateur;
	@FXML
	private CheckBox		checkBoxValide;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelValidationMedical modelposte;


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
