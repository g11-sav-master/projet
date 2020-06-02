package projet.view.raid;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.view.IManagerGui;
import projet.data.Raid;
import projet.view.EnumView;
import projet.view.raid.ModelRaid;


public class ControllerListeRaid {


	
	
	// Composants de la vue

		@FXML
		private ListView<Raid>	listView;
		@FXML
		private Button		enregistrerRaid;
		@FXML
		private Button		supprimerRaid;
		


		// Autres champs
		@Inject
		private IManagerGui		managerGui;
		@Inject
		private ModelRaid	modelraid;
	
	
	
	// Init
	@FXML
	private void initialize() {
		listView.setItems( modelraid.getListe());
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom_raid() + " " + item.getAnnee().getYear() ));
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelraid.actualiserListe();
		UtilFX.selectInListView( listView, modelraid.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelraid.preparerAjouter();;
		managerGui.showView( EnumView.RaidForm );
	}

	@FXML
	private void doModifier() {
		Raid item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelraid.preparerModifier(item);
			managerGui.showView( EnumView.RaidForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Raid item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelraid.supprimer( item );
				refresh();
			}
		}
	}
	
	// Clic sur la liste
		@FXML
		private void gererClicSurListe( MouseEvent event ) {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				if (event.getClickCount() == 2) {
					if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
						managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
					} else {
						doModifier();
					}
				}
			}
		}
	
	// Méthodes auxiliaires
	
		private void configurerBoutons() {
			
	    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
				enregistrerRaid.setDisable(true);
				supprimerRaid.setDisable(true);
			} else {
				enregistrerRaid.setDisable(false);
				supprimerRaid.setDisable(false);
			}
		}
	
}
