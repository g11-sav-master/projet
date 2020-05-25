package projet.view.validation_medicale;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.ValidationMedicale;
import projet.view.EnumView;


public class ControllerValidationForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldIdValidation;
	@FXML
	private TextField		textFieldParticipant;
	@FXML
	private CheckBox		checkBoxValide;
	@FXML
	private DatePicker		datePickerExpiration;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelValidationMedical modelValidation;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		ValidationMedicale courant = modelValidation.getCourant();
		textFieldIdValidation.textProperty().bindBidirectional(courant.id_validationProperty(), new IntegerStringConverter()); // textProperty.bindBidirectional(courant.getId_validation().toString());
		textFieldParticipant.textProperty().bindBidirectional(courant.getParticipant().idUtilisateurProperty(), new IntegerStringConverter());
		checkBoxValide.selectedProperty().bindBidirectional(courant.est_valideProperty());
		UtilFX.bindBidirectional( datePickerExpiration.getEditor(), courant.date_expirationProperty(), new ConverterStringLocalDate() );
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.ValidationListe );
	}
	
	@FXML
	private void doValider() {
		modelValidation.validerMiseAJour();
		managerGui.showView( EnumView.ValidationListe );
	}
	
}
