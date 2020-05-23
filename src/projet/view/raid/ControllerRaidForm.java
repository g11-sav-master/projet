package projet.view.raid;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Raid;
import projet.view.EnumView;


public class ControllerRaidForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private DatePicker 		datePickerAnnee;
	@FXML
	private TextField		textFieldPrixI;
	@FXML
	private TextField		textFieldPrixR;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelRaid modelraid;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Raid courant = modelraid.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		UtilFX.bindBidirectional( datePickerAnnee.getEditor(), courant.anneeProperty(), new ConverterStringLocalDate() );
		textFieldPrixI.textProperty().bindBidirectional( courant.prix_inscProperty(), new DoubleStringConverter() );
		textFieldPrixR.textProperty().bindBidirectional( courant.prix_repasProperty(), new DoubleStringConverter() );
		
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.RaidView );
	}
	
	@FXML
	private void doValider() {
		modelraid.validerMiseAJour();
		managerGui.showView( EnumView.RaidView );
	}
	
}
