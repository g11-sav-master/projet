package projet.view.action_benevole;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerFormActionBenevole {

	// Composants de la vue

	@FXML
	private TextField textFieldId;
	@FXML
	private ComboBox<Benevole> comboBoxUtilisateur;
	@FXML
	private ComboBox<Poste> comboBoxPoste;
	@FXML
	private CheckBox checkBoxPanneau;
	@FXML
	private TextArea textAreaDesc;
	@FXML
	private CheckBox checkBoxSignal;
	@FXML
	private TextField textFieldDebut;
	@FXML
	private TextField textFieldFin;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelActionBenevole modelAB;

	Pattern pattern = Pattern.compile("(^([0-9]|[0-1][0-9]|[2][0-3]):([0-5][0-9])$)|(^([0-9]|[1][0-9]|[2][0-3])$)|(^[0-1][0-9]:[0-9]$)");

	UnaryOperator<TextFormatter.Change> filter = c -> {
		String heure = c.getControlNewText();
		if (pattern.matcher(heure).matches()) {
			return c;
		} else {
			return null;
		}
	};

	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding

		ActionBenevole courant = modelAB.getCourant();
		textFieldId.textProperty().bindBidirectional(courant.id_actionProperty(), new IntegerStringConverter());
		comboBoxUtilisateur.setItems(modelAB.getBenevoles());
		comboBoxUtilisateur.valueProperty().bindBidirectional(courant.benevoleProperty());
		comboBoxPoste.setItems(modelAB.getPostes());
		comboBoxPoste.valueProperty().bindBidirectional(courant.posteProperty());
		textAreaDesc.textProperty().bindBidirectional(courant.descr_actionProperty());
		checkBoxPanneau.selectedProperty().bindBidirectional(courant.panneau_prendreProperty());
		checkBoxSignal.selectedProperty().bindBidirectional(courant.signaleurProperty());
		textFieldDebut.textProperty().bindBidirectional(courant.horaire_debutProperty(), new LocalTimeStringConverter());
		TextFormatter<LocalTime> tf = new TextFormatter<>(filter);
		textFieldDebut.setTextFormatter(tf);
		textFieldFin.textProperty().bindBidirectional(courant.horaire_finProperty(), new LocalTimeStringConverter());
		TextFormatter<LocalTime> tf2 = new TextFormatter<>(filter);
		textFieldFin.setTextFormatter(tf2);
	}

	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.ActionBenevoleListe);
	}

	@FXML
	private void doValider() {
		modelAB.validerMiseAJour();
		managerGui.showView(EnumView.ActionBenevoleListe);
	}

}
