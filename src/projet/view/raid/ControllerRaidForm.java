package projet.view.raid;

import javax.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.commun.exception.ExceptionValidation;
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
	private TextField		textFieldPrixInscription;
	@FXML
	private TextField		textFieldPrixRepas;
	
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
		textFieldPrixInscription.setText(courant.getPrix_insc().toString());
		textFieldPrixRepas.setText(courant.getPrix_repas().toString());
		courant.prix_inscProperty().addListener(new ChangeListener<Double>() {

			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double oldValue, Double newValue) {
				textFieldPrixInscription.setText(courant.getPrix_insc().toString());
			}
		});
		courant.prix_repasProperty().addListener(new ChangeListener<Double>() {

			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double oldValue, Double newValue) {
				textFieldPrixRepas.setText(courant.getPrix_repas().toString());
			}
		});
		
		
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		Raid courant = modelraid.getCourant();
		textFieldPrixInscription.setText(courant.getPrix_insc().toString());
		textFieldPrixRepas.setText(courant.getPrix_repas().toString());
		managerGui.showView( EnumView.RaidView );
	}
	
	@FXML
	private void doValider() {
		Raid courant = modelraid.getCourant();
		try {
			double prix = Double.parseDouble(textFieldPrixInscription.getText());
			courant.setPrix_insc(prix);
		} catch (Exception e) {
			throw new ExceptionValidation("Erreur dans le prix de l'inscription");

		}
		try {
			double prix = Double.parseDouble(textFieldPrixRepas.getText());
			courant.setPrix_repas(prix);
		} catch (Exception e) {
			throw new ExceptionValidation("Erreur dans le prix du repas");
		}
		modelraid.validerMiseAJour();
		managerGui.showView( EnumView.RaidView );
	}
	
}
