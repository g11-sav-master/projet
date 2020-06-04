package projet.view.equipe;

import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.CategorieRaid;
import projet.data.Participant;
import projet.data.Raid;
import projet.view.EnumView;

public class ControllerFormCreerEquipe {
	
	// Composant de la vue
	
	@FXML
	private ComboBox<Raid> comboBoxRaids;
	@FXML
	private ComboBox<Participant> comboBoxParticipantCapitaine;
	@FXML
	private ComboBox<Participant> comboBoxParticipantEquipier;
	@FXML
	private ComboBox<CategorieRaid> comboBoxCategorieRaid;
	@FXML
	private TextField textFieldNombreRepas;
	@FXML
	private CheckBox checkBoxPaiementValidee;

	// Autres champs
	
	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelEquipe modelEquipe;
	
	@FXML
	private void initialize() {
		comboBoxRaids.setItems(modelEquipe.getRaids());
		comboBoxRaids.valueProperty().addListener(obs -> {
			comboBoxParticipantCapitaine.setItems(modelEquipe.getParticipantPourUnRaid(comboBoxRaids.getValue().getId()));
			comboBoxParticipantCapitaine.valueProperty().addListener(observable -> {
				ArrayList<Participant> participants = new ArrayList<Participant>();
				participants.addAll(modelEquipe.getParticipantPourUnRaid(comboBoxRaids.getValue().getId()));
				participants.remove(comboBoxParticipantCapitaine.getValue());
				comboBoxParticipantEquipier.setItems(FXCollections.observableArrayList(participants));
			});
		});
		comboBoxCategorieRaid.setItems(FXCollections.observableArrayList(modelEquipe.getCategorieRaids()));
	}
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.EquipeListe);
	}
	
	@FXML
	private void doCreer() {
		modelEquipe.creationEquipe(comboBoxParticipantCapitaine.getValue(), comboBoxParticipantEquipier.getValue(), comboBoxRaids.getValue(), comboBoxCategorieRaid.getValue(), textFieldNombreRepas.getText(), checkBoxPaiementValidee.isPressed());
		managerGui.showView(EnumView.EquipeListe);
	}
}
