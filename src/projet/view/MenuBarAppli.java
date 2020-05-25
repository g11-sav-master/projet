package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Compte;
import projet.view.systeme.ModelConnexion;


public class MenuBarAppli extends MenuBar {

	
	// Champs
	
	private Menu	menuDonnees;
	private Menu	menuTests;
	
	private MenuItem itemDeconnecter;

	private MenuItem itemBenevole;
	
	private MenuItem itemParticipant;
	
	private MenuItem itemPoste;
	
	private MenuItem itemCategorieRaid;
	
	private MenuItem itemRaid;
	
	private MenuItem itemActionBenevole;
	
	private MenuItem itemValidationMedicale;
	
	@Inject
	private IManagerGui 	managerGui;
	@Inject
	private ModelConnexion	modelConnexion;	
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {

		
		// Variables de travail
		Menu menu;
		MenuItem item;
		
		
		// Manu Système
		
		menu =  new Menu( "Système" );;
		this.getMenus().add(menu);
		
		item = new MenuItem( "Se déconnecter" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.Connexion )  );
		menu.getItems().add( item );
		itemDeconnecter = item;
		
		item = new MenuItem( "Quitter" );
		item.setOnAction(  (e) -> managerGui.exit()  );
		menu.getItems().add( item );

		
		// Manu Données
		
		menu =  new Menu( "Données" );;
		this.getMenus().add(menu);
		menuDonnees = menu;
		
		item = new MenuItem( "Bénévoles" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.BenevoleListe )  );
		menu.getItems().add( item );
		itemBenevole = item;
		
		item = new MenuItem( "Participant" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ParticipantListe )  );
		menu.getItems().add( item );
		itemParticipant = item;
		
		item = new MenuItem( "Poste" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.PosteListe )  );
		menu.getItems().add( item );
		itemPoste = item;
		
		item = new MenuItem( "Raid" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.RaidView )  );
		menu.getItems().add( item );
		itemRaid = item;
		
		item = new MenuItem( "CategorieRaid" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.CategorieRaidListe )  );
		menu.getItems().add( item );
		itemCategorieRaid = item;
		
		item = new MenuItem( "Actions Bénévole" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ActionBenevoleListe )  );
		menu.getItems().add( item );
		itemActionBenevole = item;
		
		item = new MenuItem( "Validation Médicale" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ValidationListe )  );
		menu.getItems().add( item );
		itemValidationMedicale = item;

		
		// Menu Tests
		
		menu =  new Menu( "Tests" );;
		this.getMenus().add(menu);
		menuTests = menu;
		
		item = new MenuItem( "DaoUtilisateur" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoUtilisateur )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoBenevole" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoBenevole )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoParticipant" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoParticipant )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoRaid" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoRaid )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoRoleBenevole" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoRoleBenevole )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoValidationMedicale" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoValidationMedicale )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoCategorieRaid" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoCategorieRaid )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoParticipantDuo" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoParticipantDuo )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoFormeDuo" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoFormeDuo )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoParticipeOrganisation" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoParticipeOrganisation )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoPoste" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoPoste )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoActionBenevole" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoActionBenevole )  );
		menu.getItems().add( item );


		// Configuration initiale du menu
		configurerMenu( modelConnexion.getCompteActif() );

		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteActifProperty().addListener( (obs) -> {
					Platform.runLater( () -> configurerMenu( modelConnexion.getCompteActif() ) );
				}
			); 
		
	}

	
	// Méthodes auxiliaires
	
	private void configurerMenu( Compte compteActif  ) {

		itemDeconnecter.setDisable(true);
		
		menuDonnees.setVisible(false);
		itemBenevole.setVisible(false);
		itemParticipant.setVisible(false);
		itemCategorieRaid.setVisible(false);
		itemPoste.setVisible(false);
		itemRaid.setVisible(false);
		itemActionBenevole.setVisible(false);
		menuTests.setVisible(false);
		
		
		if( compteActif != null ) {
			itemDeconnecter.setDisable(false);
			if( compteActif.isInRole( Roles.UTILISATEUR) ) {
				menuDonnees.setVisible(true);
			}
			if( compteActif.isInRole( Roles.ADMINISTRATEUR ) ) {
				menuDonnees.setVisible(true);
				itemBenevole.setVisible(true);
				itemParticipant.setVisible(true);
				itemPoste.setVisible(true);
				itemCategorieRaid.setVisible(true);
				itemRaid.setVisible(true);
				itemActionBenevole.setVisible(true);
				menuTests.setVisible(true);
			}
		}
	}
}