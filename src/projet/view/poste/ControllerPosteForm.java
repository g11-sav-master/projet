package projet.view.poste;

import javax.inject.Inject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoRaid;
import projet.data.Poste;
import projet.data.Raid;
import projet.view.EnumView;

public class ControllerPosteForm {

	// Composants de la vue

	@FXML
	private TextField textFieldIdPoste;
	//@FXML
	//private TextField textFieldIdRaid;
	@FXML
	private TextField textFieldNbrBen;
	@FXML
	private ComboBox<Raid> CBNomRaid;
	@FXML
	private TextArea textAreaDescription;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelPoste modelposte;
	@Inject
	private DaoRaid daoRaid;



	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding

		Poste courant = modelposte.getCourant();
		textFieldIdPoste.textProperty().bindBidirectional(courant.id_posteProperty(), new IntegerStringConverter());
		//textFieldIdRaid.textProperty().bindBidirectional(courant.id_raidProperty(), new IntegerStringConverter());
		if(courant.getNbr_benev() == null) {
			courant.setNbr_benev(0);
		}
		textFieldNbrBen.setText(courant.getNbr_benev().toString());
		textFieldNbrBen.textProperty().bindBidirectional(courant.nbr_benevProperty(), new IntegerStringConverter());
		
		CBNomRaid.setItems(FXCollections.observableArrayList(daoRaid.listerTout()));
		if(courant.getId_raid() != null) {
			CBNomRaid.getSelectionModel().select(daoRaid.retrouver(courant.getId_raid()));
		}
		if(courant.descriptionProperty() != null) {
			textAreaDescription.textProperty().bindBidirectional(courant.descriptionProperty());
		}
		CBNomRaid.valueProperty().bindBidirectional(modelposte.raidProperty());
		
	}

	// Actions

	@FXML
	private void ActionPlus(ActionEvent evt) {
		modelposte.getCourant().setNbr_benev(modelposte.getCourant().getNbr_benev() + 1);
	}

	@FXML
	private void ActionMoins(ActionEvent evt) {
		if (modelposte.getCourant().getNbr_benev() > 0) {
			modelposte.getCourant().setNbr_benev(modelposte.getCourant().getNbr_benev() - 1);
		}
	}

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.PosteListe);
	}

	@FXML
	private void doValider() {
		modelposte.validerMiseAJour();
		managerGui.showView(EnumView.PosteListe);
	}

}
