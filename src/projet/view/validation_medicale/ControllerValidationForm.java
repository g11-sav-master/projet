package projet.view.validation_medicale;

import java.time.LocalDate;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
	private RadioButton		radioButtonValide;
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
		actualiserRadio();
		ValidationMedicale courant = modelValidation.getCourant();
		textFieldIdValidation.textProperty().bindBidirectional(courant.id_validationProperty(), new IntegerStringConverter()); // textProperty.bindBidirectional(courant.getId_validation().toString());
		textFieldNomParticipant.textProperty().bindBidirectional(courant.participantProperty(), ParticipantConverter() );
		//boolean valide=LocalDate.now().isBefore((ChronoLocalDate) courant.date_expirationProperty());
		radioButtonValide.selectedProperty().bindBidirectional(courant.valideProperty());//addListener( obs -> actualiserRadio());
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
	@FXML
	private void actualiserRadio() {
		Boolean valide = modelValidation.getCourant().getDate_expiration().isAfter(LocalDate.now());
		radioButtonValide.setSelected(valide);
	}
	
}
