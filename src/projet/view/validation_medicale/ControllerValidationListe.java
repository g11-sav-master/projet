package projet.view.validation_medicale;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.ValidationMedicale;
import projet.view.EnumView;


public class ControllerValidationListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<ValidationMedicale>	listView;
	@FXML
	private Button				buttonModifier;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelValidationMedical		modelValidation;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelValidation.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory(item -> item.toString()+" "));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelValidation.actualiserListe();
		UtilFX.selectInListView( listView, modelValidation.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelValidation.preparerAjouter();
		managerGui.showView( EnumView.ValidationForm );
	}

	@FXML
	private void doModifier() {
		ValidationMedicale item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelValidation.preparerModifier(item);
			managerGui.showView( EnumView.ValidationForm );
		}
	}

	@FXML
	private void doSupprimer() {
		ValidationMedicale item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelValidation.supprimer( item );
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
					doModifier();
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
		}
	}

}
