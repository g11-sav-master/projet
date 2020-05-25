package projet.view.poste;

import javax.inject.Inject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
		textFieldNbrBen.textProperty().bindBidirectional(courant.nbr_benevProperty(), new IntegerStringConverter());
		CBNomRaid.setItems(modelposte.getRaid());
		CBNomRaid.getSelectionModel().select(daoRaid.retrouver(courant.getId_raid()));

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
