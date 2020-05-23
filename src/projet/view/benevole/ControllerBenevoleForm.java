package projet.view.benevole;

import javax.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;


public class ControllerBenevoleForm {

	
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
	private TextField		textFieldRole;
	@FXML
	private CheckBox		checkBoxPossedePermis;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelBenevole modelBenevole;
	


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Benevole courant = modelBenevole.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.idUtilisateurProperty(), new IntegerStringConverter()  );
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );
		textFieldE_Mail.textProperty().bindBidirectional( courant.e_mailProperty() );
		textFieldNum_Tel.textProperty().bindBidirectional( courant.numTelProperty() );
		textFieldLogin.textProperty().bindBidirectional( courant.loginProperty() );
		if(modelBenevole.getCourantRole() == null) {
			textFieldRole.setText("Non désigné");
		}else {
			textFieldRole.setText(modelBenevole.getCourantRole().nom_roleProperty().getValue());
		}
		courant.id_roleProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer oldValue, Integer newValue) {
				if(newValue == null) {
					textFieldRole.setText("Non désigné");
				}else {
					textFieldRole.setText(modelBenevole.getCourantRole().nom_roleProperty().getValue());
				}
			}
		} );
		UtilFX.bindBidirectional( datePickerNaissance.getEditor(), courant.dateNaissanceProperty(), new ConverterStringLocalDate() );
		checkBoxPossedePermis.selectedProperty().bindBidirectional( courant.possedePermisProperty() );
	}
	
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.BenevoleListe );
	}
	
	@FXML
	private void doValider() {
		modelBenevole.validerMiseAJour();
		managerGui.showView( EnumView.BenevoleListe );
	}
	
	@FXML
	private void doChoixRole() {
		managerGui.showDialog(EnumView.BenevoleListeRole);
	}
	
}
