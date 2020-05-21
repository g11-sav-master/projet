package projet.view.action_benevole;

import javax.inject.Inject;

import org.apache.commons.beanutils.locale.converters.IntegerLocaleConverter;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerFormActionBenevole {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private ComboBox<Benevole>		comboBoxUtilisateur;
	@FXML
	private ComboBox<Poste>		comboBoxPoste;
	@FXML
	private CheckBox		checkBoxPanneau;
	@FXML
	private TextArea		textAreaDesc;
	@FXML
	private CheckBox		checkBoxSignal;
	@FXML
	private TextField		textFieldDebut;
	@FXML
	private TextField		textFieldFin;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelActionBenevole modelAB;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		ActionBenevole courant = modelAB.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.id_actionProperty(), new IntegerStringConverter()  );
		comboBoxUtilisateur.setItems(modelAB.getBenevoles() );
		comboBoxUtilisateur.valueProperty().bindBidirectional( courant.benevoleProperty());
		comboBoxPoste.setItems(modelAB.getPostes()) ;
		comboBoxPoste.valueProperty().bindBidirectional( courant.posteProperty());
		textAreaDesc.textProperty().bindBidirectional(courant.descr_actionProperty());
		checkBoxPanneau.selectedProperty().bindBidirectional( courant.panneau_prendreProperty() );
		checkBoxSignal.selectedProperty().bindBidirectional(courant.signaleurProperty());
		textFieldDebut.textProperty().bindBidirectional(courant.horaire_debutProperty(), new LocalTimeStringConverter());
		textFieldFin.textProperty().bindBidirectional(courant.horaire_finProperty(), new LocalTimeStringConverter());
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.ActionBenevoleListe );
	}
	
	@FXML
	private void doValider() {
		modelAB.validerMiseAJour();
		managerGui.showView( EnumView.ActionBenevoleListe );
	}
	
}
