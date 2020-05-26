package projet.view.validation_medicale;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;
import projet.data.ValidationMedicale;
import projet.view.EnumView;


public class ControllerValidationForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldIdValidation;
	@FXML
	private TextField		textFieldNomParticipant;
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
	public StringConverter<Participant> ParticipantConverter(){
		return new StringConverter<Participant>()
				{

					@Override
					public Participant fromString(String string) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String toString(Participant object) {
						// TODO Auto-generated method stub
						return object.toString();
					}
			
				};
	}

	@FXML
	private void initialize() {

		// Data binding
		
		ValidationMedicale courant = modelValidation.getCourant();
		textFieldIdValidation.textProperty().bindBidirectional(courant.id_validationProperty(), new IntegerStringConverter()); // textProperty.bindBidirectional(courant.getId_validation().toString());
		textFieldNomParticipant.textProperty().bindBidirectional(courant.participantProperty(), ParticipantConverter() );
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
