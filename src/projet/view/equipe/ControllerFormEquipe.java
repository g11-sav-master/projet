package projet.view.equipe;

import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoCategorieRaid;
import projet.dao.DaoFormeDuo;
import projet.dao.DaoParticipant;
import projet.dao.DaoRaid;
import projet.data.CategorieRaid;
import projet.data.Participant;
import projet.data.ParticipantDuo;
import projet.data.Poste;
import projet.data.Raid;
import projet.view.EnumView;

public class ControllerFormEquipe {

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
	@Inject
	private DaoFormeDuo daoFormeDuo;
	@Inject
	private DaoCategorieRaid daoCategorieRaid;
	@Inject
	private DaoParticipant daoParticipant;


	// Initialisation du Controller

	@FXML
	private void initialize() {
		ParticipantDuo courant = modelEquipe.getCourant();
		
		Participant capitaine = daoParticipant.retrouver(daoFormeDuo.retrouverId(courant.getId_part_duo()).getIdUtilisateur());
		comboBoxRaids.setItems(modelEquipe.getRaids());
		comboBoxRaids.valueProperty().bindBidirectional(courant.id_raidProperty());
		comboBoxCategorieRaid.setItems(FXCollections.observableArrayList(modelEquipe.getCategorieRaids()));
		comboBoxCategorieRaid.getSelectionModel().select(daoCategorieRaid.retrouverCategorieRaid(courant.getId_categorie()));
		comboBoxParticipantCapitaine.setItems(modelEquipe.getParticipantPourUnRaid(comboBoxRaids.getValue().getId()));
		comboBoxParticipantCapitaine.getSelectionModel().select(daoParticipant.retrouver(daoFormeDuo.retrouverId(courant.getId_part_duo()).getIdUtilisateur()));
		comboBoxParticipantEquipier.setItems(modelEquipe.getParticipantPourUnRaid(comboBoxRaids.getValue().getId()));
		comboBoxParticipantEquipier.getSelectionModel().select(daoParticipant.retrouver(daoFormeDuo.retrouverAutre(courant.getId_part_duo(),capitaine.getIdUtilisateur()).getIdUtilisateur()));
		textFieldNombreRepas.textProperty().bindBidirectional(courant.nbr_repasProperty(), new IntegerStringConverter());
		checkBoxPaiementValidee.selectedProperty().bindBidirectional(courant.paiement_valideProperty());
	}

	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.EquipeListe);
	}

	@FXML
	private void doValider() {
		ParticipantDuo courant = modelEquipe.getCourant();
		courant.setId_raid(comboBoxRaids.getSelectionModel().getSelectedItem());
		courant.setId_categorie(comboBoxCategorieRaid.getSelectionModel().getSelectedItem().getIdCategorieRaid());
		courant.setNbr_repas(Integer.parseInt(textFieldNombreRepas.getText()));
		courant.setPaiement_valide(checkBoxPaiementValidee.isDisabled());
		Participant cap = comboBoxParticipantCapitaine.getSelectionModel().getSelectedItem();
		Participant eq = comboBoxParticipantEquipier.getSelectionModel().getSelectedItem();
		modelEquipe.validerMiseAJour(cap, eq);
		managerGui.showView(EnumView.EquipeListe);
	}

}
