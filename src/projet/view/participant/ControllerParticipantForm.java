package projet.view.participant;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;
import projet.view.EnumView;


public class ControllerParticipantForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textFieldPrenom;
	@FXML
	private TextField		textFieldE_Mail;
	@FXML
	private TextField		textFieldNum_Tel;
	@FXML
	private DatePicker 		datePickerNaissance;
	@FXML
	private TextField		textFieldLogin;
	@FXML
	private TextField		textFieldClub;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelParticipant modelparticipant;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Participant courant = modelparticipant.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.idUtilisateurProperty(), new IntegerStringConverter()  );
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );
		textFieldE_Mail.textProperty().bindBidirectional( courant.e_mailProperty() );
		textFieldNum_Tel.textProperty().bindBidirectional( courant.numTelProperty() );
		textFieldLogin.textProperty().bindBidirectional( courant.loginProperty() );
		textFieldClub.textProperty().bindBidirectional( courant.clubProperty());
		UtilFX.bindBidirectional( datePickerNaissance.getEditor(), courant.dateNaissanceProperty(), new ConverterStringLocalDate() );
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.ParticipantListe );
	}
	
	@FXML
	private void doValider() {
		modelparticipant.validerMiseAJour();
		managerGui.showView( EnumView.ParticipantListe );
	}
	
}
