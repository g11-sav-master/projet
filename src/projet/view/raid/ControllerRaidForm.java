package projet.view.raid;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
	private TextField textFieldId;
	@FXML
	private TextField textFieldNom;
	@FXML
	private DatePicker datePickerAnnee;
	@FXML
	private TextField textFieldPrixInscription;
	@FXML
	private TextField textFieldPrixRepas;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelRaid modelraid;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding

		Raid courant = modelraid.getCourant();
		textFieldId.textProperty().bindBidirectional(courant.idProperty(), new IntegerStringConverter());
		textFieldNom.textProperty().bindBidirectional(courant.nomProperty());
		UtilFX.bindBidirectional(datePickerAnnee.getEditor(), courant.anneeProperty(), new ConverterStringLocalDate());
		if (courant.getPrix_insc() == null) {
			courant.setPrix_insc(0.0);
		}
		if (courant.getPrix_repas() == null) {
			courant.setPrix_repas(0.0);
		}
		DecimalFormat dformat = new DecimalFormat("0.00");
		textFieldPrixInscription.setText(dformat.format(courant.getPrix_insc()));
		textFieldPrixRepas.setText(dformat.format(courant.getPrix_repas()));

		courant.prix_inscProperty().addListener(new ChangeListener<Double>() {

			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double oldValue, Double newValue) {
				if (newValue == null) {
					courant.setPrix_insc(0.0);
					newValue = 0.0;
				}
				DecimalFormat df1 = new DecimalFormat("0.00");
				df1.setRoundingMode(RoundingMode.DOWN);
				textFieldPrixInscription.setText(df1.format(newValue.doubleValue()));
			}
		});
		courant.prix_repasProperty().addListener(new ChangeListener<Double>() {

			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double oldValue, Double newValue) {
				if (newValue == null) {
					courant.setPrix_repas(0.0);
					newValue = 0.0;
				}
				DecimalFormat df1 = new DecimalFormat("0.00");
				df1.setRoundingMode(RoundingMode.DOWN);
				textFieldPrixRepas.setText(df1.format(newValue.doubleValue()));
			}
		});

	}

	// Actions

	@FXML
	private void doAnnuler() {
		Raid courant = modelraid.getCourant();
		textFieldPrixInscription.setText(courant.getPrix_insc().toString());
		textFieldPrixRepas.setText(courant.getPrix_repas().toString());
		managerGui.showView(EnumView.RaidView);
	}

	@FXML
	private void doValider() {
		Raid courant = modelraid.getCourant();
		try {
			String txt = textFieldPrixInscription.getText().replace(",", ".");
			double prix = Double.parseDouble(txt);
			courant.setPrix_insc(prix);
		} catch (Exception e) {
			throw new ExceptionValidation("Erreur dans le prix de l'inscription");

		}
		try {
			String txt = textFieldPrixRepas.getText().replace(",", ".");
			double prix = Double.parseDouble(txt);
			courant.setPrix_repas(prix);
		} catch (Exception e) {
			throw new ExceptionValidation("Erreur dans le prix du repas");
		}
		modelraid.validerMiseAJour();
		managerGui.showView(EnumView.RaidView);
	}
}
