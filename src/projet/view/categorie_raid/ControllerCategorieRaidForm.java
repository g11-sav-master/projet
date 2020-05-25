package projet.view.categorie_raid;

import javax.inject.Inject;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.view.IManagerGui;
import projet.data.CategorieRaid;
import projet.view.EnumView;


public class ControllerCategorieRaidForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldIdCategorieRaid;
	@FXML
	private TextField		textFieldNomCategorieRaid;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelCategorieRaid modelcategorieraid;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		CategorieRaid courant = modelcategorieraid.getCourant();
		textFieldIdCategorieRaid.textProperty().bindBidirectional(courant.idCategorieRaidProperty(), new IntegerStringConverter());
		textFieldNomCategorieRaid.textProperty().bindBidirectional(courant.nomCategorieProperty());
	
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.CategorieRaidListe );
	}
	
	@FXML
	private void doValider() {
		modelcategorieraid.validerMiseAJour();
		managerGui.showView( EnumView.CategorieRaidListe );
	}
	
}
