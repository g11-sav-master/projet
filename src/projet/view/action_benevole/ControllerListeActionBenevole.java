package projet.view.action_benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.view.IManagerGui;
import projet.data.ActionBenevole;
import projet.view.EnumView;
import projet.view.action_benevole.ModelActionBenevole;
import projet.dao.DaoParticipeOrganisation;


public class ControllerListeActionBenevole {


	
	
	// Composants de la vue

		@FXML
		private ListView<ActionBenevole>	listView;
		@FXML
		private Button		boutonModifier;
		@FXML
		private Button		boutonSupprimer;
		


		// Autres champs
		@Inject
		private IManagerGui		managerGui;
		@Inject
		private ModelActionBenevole	modelAB;
		@Inject
		private DaoParticipeOrganisation	daoPO;
	
	
	
	// Init
	@FXML
	private void initialize() {
		listView.setItems( modelAB.getListe());
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelAB.actualiserListe();
		UtilFX.selectInListView( listView, modelAB.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelAB.preparerAjouter();
		managerGui.showView( EnumView.ActionBenevoleForm );
	}

	@FXML
	private void doModifier() {
		ActionBenevole item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelAB.preparerModifier(item);
			managerGui.showView( EnumView.ActionBenevoleForm );
			//System.out.println(item.getPoste());
		}
	}

	@FXML
	private void doSupprimer() {
		ActionBenevole item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelAB.supprimer( item );
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
				boutonModifier.setDisable(true);
				boutonSupprimer.setDisable(true);
			} else {
				boutonModifier.setDisable(false);
				boutonSupprimer.setDisable(false);
			}
		}
	
}
