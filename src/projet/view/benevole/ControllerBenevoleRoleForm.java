package projet.view.benevole;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.data.RoleBenevole;
import projet.view.EnumView;


public class ControllerBenevoleRoleForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelBenevole modelBenevole;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		RoleBenevole courant = modelBenevole.getRoleSelected();
		textFieldId.textProperty().bindBidirectional( courant.id_roleProperty(), new IntegerStringConverter()  );
		textFieldNom.textProperty().bindBidirectional( courant.nom_roleProperty() );
		
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.BenevoleListeRole );
	}
	
	@FXML
	private void doValider() {
		modelBenevole.validerMiseAJourRole();
		managerGui.showView( EnumView.BenevoleListeRole );
	}
}
