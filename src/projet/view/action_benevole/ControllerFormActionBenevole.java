package projet.view.action_benevole;

import java.time.LocalTime;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javax.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.commun.exception.ExceptionValidation;
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
	private TextField textFieldHeureDebut;
	@FXML
	private TextField textFieldMinuteDebut;
	@FXML
	private TextField textFieldHeureFin;
	@FXML
	private TextField textFieldMinuteFin;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelActionBenevole modelAB;

	/*
	 * Pattern pattern = Pattern.compile(
	 * "(^([0-9]|[0-1][0-9]|[2][0-3]):([0-5][0-9])$)|(^([0-9]|[1][0-9]|[2][0-3])$)|(^[0-1][0-9]:[0-9]$)"
	 * );
	 * 
	 * UnaryOperator<TextFormatter.Change> filter = c -> { String heure =
	 * c.getControlNewText(); if (pattern.matcher(heure).matches()) { return c; }
	 * else { return null; } };
	 * 
	 * StringConverter<LocalTime> converter = new StringConverter<LocalTime>() {
	 * 
	 * @Override public LocalTime fromString(String s) { if (s == null) { return
	 * LocalTime.parse("00:00") ; } else { return LocalTime.parse(s); } }
	 * 
	 * 
	 * @Override public String toString(LocalTime t) { return t.toString(); } };
	 */

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
		textFieldHeureDebut.setText(courant.getHoraire_debut().getHour()+"");
		textFieldHeureFin.setText(courant.getHoraire_fin().getHour()+"");
		textFieldMinuteDebut.setText(courant.getHoraire_debut().getMinute()+"");
		textFieldMinuteFin.setText(courant.getHoraire_fin().getMinute()+"");

		courant.horaire_debutProperty().addListener(new ChangeListener<LocalTime>() {
			@Override
			public void changed(ObservableValue<? extends LocalTime> arg0, LocalTime arg1, LocalTime arg2) {
				textFieldHeureDebut.setText(courant.getHoraire_debut().getHour()+"");
				textFieldMinuteDebut.setText(courant.getHoraire_debut().getMinute()+"");
			}
		});
		
		courant.horaire_finProperty().addListener(new ChangeListener<LocalTime>() {
			@Override
			public void changed(ObservableValue<? extends LocalTime> arg0, LocalTime arg1, LocalTime arg2) {
				textFieldHeureFin.setText(courant.getHoraire_fin().getHour()+"");
				textFieldMinuteFin.setText(courant.getHoraire_fin().getMinute()+"");
			}
		});
	

		/*
		 * if (courant.getHoraire_debut() != null && courant.getHoraire_fin() != null) {
		 * textFieldDebut.textProperty().bindBidirectional(courant.horaire_debutProperty
		 * (), new LocalTimeStringConverter()); TextFormatter<LocalTime> tf = new
		 * TextFormatter<>(filter); textFieldDebut.setTextFormatter(tf);
		 * textFieldFin.textProperty().bindBidirectional(courant.horaire_finProperty(),
		 * new LocalTimeStringConverter()); TextFormatter<LocalTime> tf2 = new
		 * TextFormatter<>(filter); textFieldFin.setTextFormatter(tf2); } else {
		 * textFieldDebut.textProperty().bindBidirectional(courant.horaire_debutProperty
		 * (), new LocalTimeStringConverter()); TextFormatter<LocalTime> tf = new
		 * TextFormatter<>(converter,LocalTime.parse("00:00") ,filter);
		 * textFieldDebut.setTextFormatter(tf);
		 * textFieldFin.textProperty().bindBidirectional(courant.horaire_finProperty(),
		 * new LocalTimeStringConverter()); TextFormatter<LocalTime> tf2 = new
		 * TextFormatter<>(converter,LocalTime.parse("00:00") ,filter);
		 * textFieldFin.setTextFormatter(tf2); }
		 */
	}

	// Actions

	@FXML
	private void doAnnuler() {
		ActionBenevole courant = modelAB.getCourant();
		textFieldHeureDebut.setText(courant.getHoraire_debut().getHour()+"");
		textFieldHeureFin.setText(courant.getHoraire_fin().getHour()+"");
		textFieldMinuteDebut.setText(courant.getHoraire_debut().getMinute()+"");
		textFieldMinuteFin.setText(courant.getHoraire_fin().getMinute()+"");
		managerGui.showView(EnumView.ActionBenevoleListe);
	}

	@FXML
	private void doValider() {
		ActionBenevole courant = modelAB.getCourant();
		
		//pour l'heure du début
		try {
			int heure = Integer.parseInt(textFieldHeureDebut.getText());
			int minute = courant.getHoraire_debut().getMinute();
			if (heure >= 0 && heure <= 24) {
				courant.setHoraire_debut(LocalTime.of(heure, minute));
			}
		} catch (Exception exception) {
			throw new ExceptionValidation("Erreur dans l'heure de début");
		}
		
		//pour les minutes du début
		try {
			int heure = courant.getHoraire_debut().getHour();
			int minute = Integer.parseInt(textFieldMinuteDebut.getText());
			if(minute >= 0 && minute <= 59) {
				courant.setHoraire_debut(LocalTime.of(heure, minute));
			}
		} catch (Exception exception) {
			throw new ExceptionValidation("Erreur dans les minutes de début");
		}
		
		//pour l'heure de fin
		try {
			int heure = Integer.parseInt(textFieldHeureFin.getText());
			int minute = courant.getHoraire_fin().getMinute();
			if (heure >= 0 && heure <= 24)
				courant.setHoraire_debut(LocalTime.of(heure, minute));
		} catch (Exception exception) {
			throw new ExceptionValidation("Erreur dans l'heure de fin");
		}
		
		//pour les minutes de fin
		try {
			int heure = courant.getHoraire_fin().getHour();
			int minute = Integer.parseInt(textFieldMinuteFin.getText());
			if(minute >= 0 && minute <= 59)
				courant.setHoraire_debut(LocalTime.of(heure, minute));
		} catch (Exception exception) {
			throw new ExceptionValidation("Erreur dans les minutes de fin");
		}
		
		modelAB.validerMiseAJour();
		managerGui.showView(EnumView.ActionBenevoleListe);
	}

}
