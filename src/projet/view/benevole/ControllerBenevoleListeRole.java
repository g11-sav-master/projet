package projet.view.benevole;

import javax.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.commun.IMapper;
import projet.data.RoleBenevole;
import projet.view.EnumView;


public class ControllerBenevoleListeRole {
	
	
	// Composants de la vue

	@FXML
	private ListView<RoleBenevole>		listView;
	@FXML
	private Button				buttonSelectionner;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelBenevole modelBenevole;
	@Inject
	private IMapper mapper;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		modelBenevole.actualiserListeRole();
		// Data binding
		listView.setItems( modelBenevole.getRoleBenevoleListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom_role() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
		listView.getSelectionModel().setSelectionMode((SelectionMode.SINGLE));

	}
	
	
	public void refresh() {
		modelBenevole.actualiserListeRole();
		UtilFX.selectInListView( listView, modelBenevole.getRoleSelected() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doFermer() {
		modelBenevole.getCourant().setId_role(listView.getSelectionModel().getSelectedItem().getId_role());
		mapper.update(modelBenevole.getCourantRole(), listView.getSelectionModel().getSelectedItem());
		managerGui.closeStage();
	}
	
	@FXML
	private void doAjouter() {
		modelBenevole.preparerAjouterRole();
		managerGui.showView( EnumView.BenevoleRoleForm );
	}

	@FXML
	private void doModifier() {
		RoleBenevole item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelBenevole.preparerModifierRole(item);
			managerGui.showView( EnumView.BenevoleRoleForm );
		}
	}

	@FXML
	private void doSupprimer() {
		RoleBenevole item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelBenevole.supprimerRole( item );
				refresh();
			}
		}
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doFermer();
				}
			}
		}
	}
	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
    		buttonSelectionner.setDisable(true);
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
    		buttonSelectionner.setDisable(false);
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}

}