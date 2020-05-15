package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "systeme/ViewConnexion.fxml" ),
	BenevoleListe		( "benevole/ViewBenevoleListe.fxml" ),
	BenevoleForm		( "benevole/ViewBenevoleForm.fxml" ),
	CategorieListe		( "personne/ViewCategorieListe.fxml" ),
	CategorieForm		( "personne/ViewCategorieForm.fxml" ),
	TestDaoUtilisateur	( "test/ViewTestDaoUtilisateur.fxml" ),
	TestDaoBenevole		( "test/ViewTestDaoBenevole.fxml" ),
	TestDaoParticipant	( "test/ViewTestDaoParticipant.fxml" ),
	TestDaoRaid			( "test/ViewTestDaoRaid.fxml"),
	TestDaoRoleBenevole ("test/ViewTestDaoRoleBenevole.fxml"),
	TestDaoValidationMedicale ("test/ViewTestDaoValidation.fxml"),
	TestDaoCategorieRaid ( "test/ViewTestDaoCategorieRaid.fxml" ),
	TestDaoParticipantDuo ("test/ViewTestDaoParticipantDuo.fxml"),
	TestDaoFormeDuo		( "test/ViewTestDaoFormeDuo.fxml" ),
	TestDaoParticipeOrganisation ( "test/ViewTestDaoParticipeOrganisation.fxml" ),
	TestDaoPoste ("test/ViewTestDaoPoste.fxml"),
	TestDaoActionBenevole ("test/ViewTestDaoActionBenevole.fxml"),
	;

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
